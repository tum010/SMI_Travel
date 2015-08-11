/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.monitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.attribute.*;
import org.apache.log4j.Logger;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author wleenavo
 */
public class DirectoryWatch {

    static Logger log = Logger.getLogger(DirectoryWatch.class.getName());
    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private final boolean recursive;
    private boolean trace = false;

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }

    /**
     * Register the given directory with the WatchService
     */
    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE);//, ENTRY_DELETE, ENTRY_MODIFY);
        if (trace) {
            Path prev = keys.get(key);
            if (prev == null) {
                log.info("register:" + dir + "\n");
            } else {
                if (!dir.equals(prev)) {
                    log.info("update: " + prev + " -> " + dir + "\n");
                }
            }
        }
        keys.put(key, dir);
    }

    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories  
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Creates a WatchService and registers the given directory
     *
     */
    public DirectoryWatch(String dirpath, String recursive, String swapDir) throws IOException {
        log.info("*** Inside DirectoryWatch Constructor");
        Path dir = Paths.get(dirpath);
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();
        if (recursive.equals("Y")) {
            this.recursive = true;
        } else {
            this.recursive = false;
        }
//        System.setOut(new PrintStream(new FileOutputStream("C:/dirparamlog.out", true)));
        if (this.recursive) {
            log.info("Scanning " + dir + " ...\n");
            registerAll(dir);
            log.info("Done.");
        } else {
            register(dir);
        }
        //Swap existing file for trigger Watchservice
        swapExisting(dir, swapDir);
        // enable trace after initial registration  
        this.trace = true;
//        processEvents();
    }

    /**
     * Process all events for keys queued to the watcher
     *
     * @throws FileNotFoundException
     */
    String processEvents() throws FileNotFoundException, UnregisterDirectoryException {
        StringBuffer fileFound = new StringBuffer();
//        log.info("DirWatch processEvents");
        // wait for key to be signalled  
        WatchKey key;
        try {
            key = watcher.poll(100, TimeUnit.MILLISECONDS);//take();
        } catch (InterruptedException x) {
            return null;
        }
        Path dir = keys.get(key);
        if (dir == null) {
//            log.info("No event key present and timeout!!!");
            return null;
//            System.err.println("WatchKey not recognized!!");
        }
        for (WatchEvent<?> event : key.pollEvents()) {
            WatchEvent.Kind kind = event.kind();
            System.out.println("kind : " + kind);
            // TBD - provide example of how OVERFLOW event is handled  
            if (kind == OVERFLOW) {
                System.out.println("--------------------------File Overflow:" + event.context());
                continue;
            }
            if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                System.out.println("--------------------------File Created:" + event.context());
            } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                // and modify it
                System.out.println("--------------------------File Modified:" + event.context());
            } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                System.out.println("--------------------------File deleted:" + event.context());
            }
            // Context for directory entry event is the file name of entry  
            WatchEvent<Path> ev = cast(event);
            Path name = ev.context();
            Path child = dir.resolve(name);
            // print out event  
            log.info(event.kind().name() + ":" + child + "\n");
         // if directory is created, and watching recursively, then  
            fileFound.append(child.getFileName().toString());
            fileFound.append(",");
        }
        // reset key and remove from set if directory no longer accessible  
        boolean valid = key.reset();
        if (!valid) {
            keys.remove(key);
            // all directories are inaccessible  
            if (keys.isEmpty()) {
                log.info("Keys is empty");
                throw new UnregisterDirectoryException("Directory No longer Accessible");
            }
        }
        return fileFound.toString();
    }

    private void swapExisting(Path dir, String swapDir) {
        
            try {
                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir);
                for (Path path : directoryStream) {
                    
                    String srcPath = path.toString();
                    Path swapDirPath = FileSystems.getDefault().getPath(swapDir);
                    srcPath = srcPath.replace(dir.toString(), swapDirPath.toString());
                    Path swapFile = FileSystems.getDefault().getPath(srcPath);
                    if(Files.notExists(swapFile.getParent())){
                        new File(swapFile.getParent().toString()).mkdir();
                    }
                    System.out.println("##############copying " + path.toString());
                    System.out.println("##############destination File=" + swapFile.toString());
                    Files.move(path, swapFile, StandardCopyOption.REPLACE_EXISTING);
                    Files.move(swapFile, path, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
    }
    }
}
