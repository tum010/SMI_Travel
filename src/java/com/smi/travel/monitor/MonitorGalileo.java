/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.monitor;

/**
 *
 * @author wleenavo
 */
import com.smi.travel.datalayer.entity.BookingAirline;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.BookingPassenger;
import com.smi.travel.datalayer.entity.BookingPnr;
import com.smi.travel.datalayer.entity.MAirline;
import com.smi.travel.datalayer.entity.MGalileo;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.MAirticketService;
import com.smi.travel.datalayer.service.MGalileoService;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MonitorGalileo extends MonitorScheduler {

    private DirectoryWatch directoryWatch;
    private MGalileoService mGalileoService;
    private MAirticketService mAirticketService;
    private BookingAirticketService bookingAirticketService;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private List<MGalileo> listGalileo;
    private String monitorDirectory = null;
    private String archivedDirectory = null;
    private String errorDirectory = null;
    Map retrievedList = null;
    private final static String GDS = "GALILEO";
    private final static String SECTION_PATTERN = "^[A]\\d\\d.*";
    private MultiMap sectionData;
    private List<String> lineData;
    private Map<String, MGalileo> galileoMap;
    private Boolean initFlag = false;

    MonitorGalileo(String monitorDirectory) {
        this.monitorDirectory = monitorDirectory;
    }

    MonitorGalileo(String inDir, String archDir, String errDir) {
        this.monitorDirectory = inDir;
        this.archivedDirectory = archDir;
        this.errorDirectory = errDir;
    }

    public void run() {

        String fileList = null;
        int result = 0;

        if (!initFlag) {
            init();
            return;
        }
        try {
            System.out.println(MonitorGalileo.class.getName() + " - Directory (" + this.monitorDirectory + ")");
            fileList = directoryWatch.processEvents();
//            System.out.println("file found " + fileList);
            if (fileList != null) {
                buildMap();
                String list[] = fileList.split(",");
                for (String file : list) {
                    try {
                        result = processDataFile(file);
                        System.out.println("Completed processing file " + file + "...");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("Archivig file " + file + "...[" + result + "]");
                        archiveDataFile(file, result);
                    }
                }
            }
        } catch (UnregisterDirectoryException ue) {
            initFlag = false;
            Logger.getLogger(MonitorGalileo.class.getName()).log(Level.INFO, null, ue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        File input = new File(this.monitorDirectory);
        File archived = new File(this.archivedDirectory);
        File error = new File(this.errorDirectory);
        if (input.exists() && archived.exists() && error.exists()) {
            initFlag = true;
            System.out.println("Init " + MonitorGalileo.class.getName() + " , service directory is" + this.monitorDirectory);
            try {
                directoryWatch = new DirectoryWatch(this.monitorDirectory, "1");
            } catch (IOException ex) {
                Logger.getLogger(MonitorGalileo.class.getName()).log(Level.SEVERE, "Service directory must be gone.", ex);
            }
        } else {
            System.err.println(MonitorGalileo.class.getName() + " configuration folders doesnot exist!");
            initFlag = false;
        }
    }

    public DirectoryWatch getDirectoryWatch() {
        return directoryWatch;
    }

    public void setDirectoryWatch(DirectoryWatch directoryWatch) {
        this.directoryWatch = directoryWatch;
    }

    public MGalileoService getmGalileoService() {
        return mGalileoService;
    }

    public void setmGalileoService(MGalileoService mGalileoService) {
        this.mGalileoService = mGalileoService;
    }

    public MAirticketService getmAirticketService() {
        return mAirticketService;
    }

    public void setmAirticketService(MAirticketService mAirticketService) {
        this.mAirticketService = mAirticketService;
    }

    @Override
    void buildContentList(String file) {
        String galiFile = this.monitorDirectory + file;
        Path fFilePath;
        fFilePath = Paths.get(galiFile);

        lineData = new ArrayList<String>();
        lineData.add("");
        sectionData = new MultiValueMap();

        Scanner scanner = null;
        try {
            scanner = new Scanner(fFilePath, ENCODING.name());

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
//                System.out.println(line);

                if (line.matches(MonitorGalileo.SECTION_PATTERN)) {
                    String key = line.substring(0, 3);
//                    System.out.println("Key " + key + " ** Line " + line);
                    sectionData.put(key, line);

                } else {
                    lineData.add(line);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    @Override
    BookingPnr buildBookingPnr(String filename) {

        //Extract PNR data
        Iterator<MGalileo> iterator = listGalileo.listIterator();
        String pnr = null;
        String currency = null;

        //Get PNR Data
        MGalileo gali = galileoMap.get("pnr");
        int lineNo = Integer.parseInt(gali.getLine());
        String line = lineData.get(lineNo);
        pnr = line.substring(gali.getStartlength() - 1, gali.getStartlength() - 1 + gali.getLength());

        //A21 should have a row.
        gali = galileoMap.get("currency");
        ArrayList<String> lines = (ArrayList<String>) sectionData.get(gali.getSection());
        line = lines.get(0);
        currency = line.substring(gali.getStartlength() - 1, gali.getStartlength() - 1 + gali.getLength());

        BookingPnr bPnr = new BookingPnr(GDS, pnr, filename);

        bPnr.setCurrency(currency);

//        System.out.println("BookingPnr - " + bPnr.toString());
        return bPnr;
    }

    @Override
    BookingAirline buildBookingAirline() {

        String airlineCode = null;
        String ticketDateS = null;

        //Get PNR Data
        MGalileo gali = galileoMap.get("airline code");
        int lineNo = Integer.parseInt(gali.getLine());
        String line = lineData.get(lineNo);
        airlineCode = line.substring(gali.getStartlength() - 1, gali.getStartlength() - 1 + gali.getLength());

        MAirline mAir = new MAirline();
        mAir.setCode(airlineCode);
        List<MAirline> res = mAirticketService.searchAirline(mAir, 1);
        MAirline mAirReturn = res.get(0);
        String airlineName = mAirReturn.getName();

        gali = galileoMap.get("ticket date");
        lineNo = Integer.parseInt(gali.getLine());
        line = lineData.get(lineNo);
        ticketDateS = line.substring(gali.getStartlength() - 1, gali.getStartlength() - 1 + gali.getLength());
        Date ticketDate = convertStringToDate(ticketDateS);

        BookingAirline bAir = new BookingAirline(airlineCode, airlineName, ticketDate);

//        System.out.println("Build bookingAirline - " + bAir.toString());
        return bAir;
    }

    @Override
    void buildBookingFlight(BookingAirline bAir) {
        MGalileo flightNumber = galileoMap.get("flight number");
        String section = flightNumber.getSection();
        MGalileo source = galileoMap.get("source");
        MGalileo destination = galileoMap.get("destination");
        MGalileo departureDate = galileoMap.get("departure date");
        MGalileo arrivalDate = galileoMap.get("arrive date");

        MGalileo ticketDate = galileoMap.get("ticket date");
        MGalileo deptTime = galileoMap.get("departure time");
        MGalileo arrvTime = galileoMap.get("arrive time");

        int lineNo = Integer.parseInt(ticketDate.getLine());
        String lineT = lineData.get(lineNo);
        String ticketDateS = lineT.substring(ticketDate.getStartlength() - 1, ticketDate.getStartlength() - 1 + ticketDate.getLength());
        String year = ticketDateS.substring(5);

        //Check how many rows there is.
        ArrayList<String> lines = (ArrayList<String>) sectionData.get(section);

        ListIterator<String> iterator = lines.listIterator();
        BookingFlight bf = null;
        while (iterator.hasNext()) {
            String line = iterator.next();
            System.out.println("BookingFlingt input line[" + line + "]");
            String flightNo = bAir.getAirlineCode() + getField("flight number", line).trim();
//            String flightNo = line.substring(flightNumber.getStartlength() - 1, flightNumber.getStartlength() - 1 + flightNumber.getLength());
            String sourceCode = line.substring(source.getStartlength() - 1, source.getStartlength() - 1 + source.getLength());
            String desCode = line.substring(destination.getStartlength() - 1, destination.getStartlength() - 1 + destination.getLength());
            String deptDateS = line.substring(departureDate.getStartlength() - 1, departureDate.getStartlength() - 1 + departureDate.getLength());
            String arrivalDateS = line.substring(arrivalDate.getStartlength() - 1, arrivalDate.getStartlength() - 1 + arrivalDate.getLength());
//            String deptTimeS = line.substring(deptTime.getStartlength() - 1, deptTime.getStartlength() - 1 + deptTime.getLength());
            String deptTimeS = getField("departure time", line);
            String arrvTimeS = line.substring(arrvTime.getStartlength() - 1, arrvTime.getStartlength() - 1 + arrvTime.getLength());
            Date deptDate = convertStringToDate(deptDateS + year);
            Date arrvDate = convertStringToDate(deptDateS + year, Integer.parseInt(arrivalDateS));

            String flightClass = getField("flight class" , line);
            bf = new BookingFlight(flightNo, sourceCode, desCode, deptDate, arrvDate, flightClass);
            bf.setDepartTime(deptTimeS);
            bf.setArriveTime(arrvTimeS);
            bf.setAdCost(0);
            bf.setAdPrice(0);
            bf.setAdTax(0);
            bf.setChCost(0);
            bf.setChPrice(0);
            bf.setChTax(0);
            bf.setInCost(0);
            bf.setInPrice(0);
            bf.setInTax(0);
            bf.setOtCost(0);
            bf.setOtPrice(0);
            bf.setOtTax(0);
            bAir.getBookingFlights().add(bf);
            bf.setBookingAirline(bAir);

//            System.out.println("Build BookingFlight - " + bf.toString());
        }

        return;
    }

    @Override
    void buildBookingPassenger(BookingAirline bAir) {
        String passengerTypes = new String("");
        int costRefIndex = 0;
        String section = galileoMap.get("passenger name").getSection();

        MGalileo airNo = galileoMap.get("airline number");

        int lineNo = Integer.parseInt(airNo.getLine());
        String line = lineData.get(lineNo);
        String ticketNoS1 = line.substring(airNo.getStartlength() - 1, airNo.getStartlength() - 1 + airNo.getLength());

        MGalileo type = galileoMap.get("ticket type");
        lineNo = Integer.parseInt(type.getLine());
        line = lineData.get(lineNo);
        String ticketType = line.substring(type.getStartlength() - 1, type.getStartlength() - 1 + type.getLength());

        ArrayList<String> lines = (ArrayList<String>) sectionData.get(section);
//        System.out.println("Passenger " + lines.size());

        ListIterator<String> iterator = lines.listIterator();
        BookingPassenger bp = null;
        while (iterator.hasNext()) {
            line = iterator.next();
            System.out.println("line-> " + line);
            String passengerName = getField("passenger name", line).trim();
            String passengerType = getField("passenger type", line);
            if (StringUtils.isEmpty(passengerType)
                    || (!("ADT".equalsIgnoreCase(passengerType))
                    && !("CHD".equalsIgnoreCase(passengerType))
                    && !("INF".equalsIgnoreCase(passengerType)))) {
                passengerType = "NON";
            }
            String[] splitName = passengerName.split("/");
            String lastName = splitName[0];
            String firstName = splitName[1].substring(0, splitName[1].length() - 2);
            String initial = splitName[1].substring(splitName[1].length() - 2);

            String ticketSerial2 = getField("ticket serial2", line);
            String ticketSerial3 = getField("ticket serial3", line);

            String fareNo = getField("fare reference", line);
            String fareSection = galileoMap.get("ticket fare").getSection();
            ArrayList<String> fareLines = (ArrayList<String>) sectionData.get(fareSection);
            String fareLine = fareLines.get(Integer.parseInt(fareNo) - 1);
            String currency = getField("currency", fareLine);

            String ticketFare = getTicketFare(currency, fareLine);
            String ticketTotalS = getField("ticket total", fareLine);
            ticketTotalS = stripNumberDecimalString(ticketTotalS);
            ticketFare = stripNumberDecimalString(ticketFare);
            int tax = Integer.parseInt(ticketTotalS.trim()) - Integer.parseInt(ticketFare.trim());

            bp = new BookingPassenger();
            bp.setFirstName(firstName);
            bp.setLastName(lastName);
            bp.setInitialName(initial);
            bp.setPassengerType(passengerType);
            bp.setTicketType(ticketType);
            bp.setTicketnoS1(ticketNoS1);
            bp.setTicketnoS2(ticketSerial2);
            bp.setTicketnoS3(ticketSerial3);
            bp.setTicketFare(Integer.valueOf(ticketFare.trim()));
            bp.setTicketTax(Integer.valueOf(tax));
            bAir.getBookingPassengers().add(bp);
            bp.setBookingAirline(bAir);

            if (!passengerTypes.contains(passengerType)) {
                passengerTypes = passengerTypes + "," + passengerType;
                String costS = getField("cost", costRefIndex).trim();
                String priceS = getField("price", costRefIndex).trim();
                Integer cost = Integer.valueOf(costS);
                Integer price = Integer.valueOf(priceS);
                costRefIndex++;
                //Update cost,price,tax according to passengertype
                BookingFlight bf = this.getMostEarlyFlight(bAir.getBookingPnr());
                if ("ADT".equalsIgnoreCase(passengerType)) {
                    bf.setAdCost(cost);
                    bf.setAdPrice(price);
                    bf.setAdTax(tax);
                } else if ("CHD".equalsIgnoreCase(passengerType)) {
                    bf.setChCost(cost);
                    bf.setChPrice(price);
                    bf.setChTax(tax);
                } else if ("INF".equalsIgnoreCase(passengerType)) {
                    bf.setInCost(cost);
                    bf.setInPrice(price);
                    bf.setInTax(tax);
                } else {
                    bf.setAdCost(cost);
                    bf.setAdPrice(price);
                    bf.setAdTax(tax);
                }
//                }

            }
        }

        return;
    }

    private String getTicketFare(String currency, String line) {
        String ticketFare;
        if (!"THB".equalsIgnoreCase(currency)) {
            ticketFare = getField("ticket fare2", line).trim();
        } else {
            ticketFare = getField("ticket fare", line).trim();
        }
        return ticketFare;
    }

    public String getField(String fldName, int lineIndex) {

        MGalileo mGali = galileoMap.get(fldName);
        if (mGali == null) {
            System.err.println(fldName + " doesnot exist in map/database.");
            return null;
        }
        int lineNo = Integer.valueOf(mGali.getLine());
        String targetLine = null;
        if (lineNo == 0) {
            ArrayList<String> lines = (ArrayList<String>) sectionData.get(mGali.getSection());
            if (lines == null) {
                return null;
            }
            targetLine = lines.get(lineIndex);
        } else {
            targetLine = lineData.get(lineNo);
        }
        return getField(fldName, targetLine);
    }

    public String getField(String fldName) {

        MGalileo mGali = galileoMap.get(fldName);
        if (mGali == null) {
            System.err.println(fldName + " doesnot exist in map/database.");
            return null;
        }
        int lineNo = Integer.valueOf(mGali.getLine());
        String targetLine = null;
        if (lineNo == 0) {
            ArrayList<String> lines = (ArrayList<String>) sectionData.get(mGali.getSection());
            if (lines == null) {
                return null;
            }
            targetLine = lines.get(0);
        } else {
            targetLine = lineData.get(lineNo);
        }
        return getField(fldName, targetLine);
    }

    public String getField(String fldName, String targetLine) {
        MGalileo mGali = galileoMap.get(fldName);
        if (mGali == null) {
            System.err.println(fldName + " doesnot exist in map/database.");
            return null;
        }
        String value = targetLine.substring(mGali.getStartlength() - 1, mGali.getStartlength() - 1 + mGali.getLength());
        return value;
    }

    private void buildMap() {
        listGalileo = mGalileoService.getGalileoList();
        ListIterator<MGalileo> iterator = listGalileo.listIterator();
        galileoMap = new HashMap();
        while (iterator.hasNext()) {
            MGalileo gali = iterator.next();
            galileoMap.put(gali.getName(), gali);
        }
    }

    @Override
    protected int processDataFile(String file) {

        int result = 1;
//        System.out.println("*** Found " + file);
        try {

            buildContentList(file);
            BookingPnr bPnr = buildBookingPnr(file);
            BookingAirline bAir = buildBookingAirline();

            bPnr.getBookingAirlines().add(bAir);
            bAir.setBookingPnr(bPnr);

            buildBookingFlight(bAir);
            buildBookingPassenger(bAir);

            BookingPnr dbBookingPnr = bookingAirticketService.getBookingPnr(bPnr.getPnr());
            if (dbBookingPnr == null) {
                bookingAirticketService.insertBookingPnr(bPnr);
            } else {
                System.out.println("BookPnr[" + bPnr.getPnr() + "] is existed.");
                bookingAirticketService.updateBookingPnr(bPnr);
            }

        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
        }
        return result;

    }

    @Override
    int archiveDataFile(String file, int option) {
        try {
            Path sourceFile = Paths.get(this.monitorDirectory + file);
            Path destFile = Paths.get(this.archivedDirectory + file);
            Path errFile = Paths.get(this.errorDirectory + file);

            if (option == 1) {
                System.out.println("Archiving file " + destFile);
                Files.move(sourceFile, destFile, REPLACE_EXISTING);
            } else {
                System.out.println("Archiving to error folder for file " + errFile);
                Files.move(sourceFile, errFile, REPLACE_EXISTING);
            }
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(MonitorAmadeus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public BookingAirticketService getBookingAirticketService() {
        return bookingAirticketService;
    }

    public void setBookingAirticketService(BookingAirticketService bookingAirticketService) {
        this.bookingAirticketService = bookingAirticketService;
    }

}
