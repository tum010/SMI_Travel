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
import com.smi.travel.datalayer.entity.MAmadeus;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.MAirticketService;
import com.smi.travel.datalayer.service.MAmadeusService;
import com.smi.travel.util.UtilityFunction;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
public class MonitorAmadeus extends MonitorScheduler {

    private DirectoryWatch directoryWatch;
    private MAmadeusService mAmadeusService;
    private MAirticketService mAirticketService;
    private BookingAirticketService bookingAirticketService;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private List<MAmadeus> listAmadeus;
    private String monitorDirectory = null;
    private String archivedDirectory = null;
    private String errorDirectory = null;
    private String swapDirectory = null;
    Map retrievedList = null;
    private final static String GDS = "AMADEUS";
    private final static String NODE_SEPARATOR = ";";
    private MultiMap sectionData;
    private List<String> lineData;
    private Map<String, MAmadeus> amadeusMap;
    private Boolean initFlag = false;

    MonitorAmadeus(String monitorDirectory) {
        this.monitorDirectory = monitorDirectory;
        this.archivedDirectory = monitorDirectory + "../archived/";
        this.errorDirectory = monitorDirectory + "../error/";
        this.swapDirectory = monitorDirectory + "../swap/";
    }

    MonitorAmadeus(String inDir, String archDir, String errDir, String swapDir) {
        this.monitorDirectory = inDir;
        this.archivedDirectory = archDir;
        this.errorDirectory = errDir;
        this.swapDirectory = swapDir;
    }

    public void run() {

        if (!initFlag) {
            init();
            return;
        }
        String fileList = null;
        int result = 0;
        try {
            System.out.println(MonitorAmadeus.class.getName() + " - Directory(" + this.monitorDirectory + ")");
            fileList = directoryWatch.processEvents();
            System.out.println("------------------------file found " + fileList);
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
            Logger.getLogger(MonitorAmadeus.class.getName()).log(Level.INFO, "Service directory must be gone", ue);
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
            System.out.println(MonitorAmadeus.class
                    .getName() + " started  " + this.monitorDirectory);

            try {
                directoryWatch = new DirectoryWatch(this.monitorDirectory, "1", this.swapDirectory);
            } catch (IOException ex) {
                Logger.getLogger(MonitorAmadeus.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println(MonitorAmadeus.class
                    .getName() + " configuration folders doesnot exist!");
            initFlag = false;
        }
    }

    public DirectoryWatch getDirectoryWatch() {
        return directoryWatch;
    }

    public void setDirectoryWatch(DirectoryWatch directoryWatch) {
        this.directoryWatch = directoryWatch;
    }

    public MAmadeusService getmAmadeusService() {
        return mAmadeusService;
    }

    public void setmAmadeusService(MAmadeusService mAmadeusService) {
        this.mAmadeusService = mAmadeusService;
    }

    public MAirticketService getmAirticketService() {
        return mAirticketService;
    }

    public void setmAirticketService(MAirticketService mAirticketService) {
        this.mAirticketService = mAirticketService;
    }

    @Override
    void buildContentList(String file) {
        String pathFile = this.monitorDirectory + file;
        Path fFilePath;
        fFilePath = Paths.get(pathFile);
        boolean pullSectionPassenger = false;
        StringBuffer passengerData = new StringBuffer();
        String key = null;
        sectionData = new MultiValueMap();

        Scanner scanner = null;
        try {
            scanner = new Scanner(fFilePath, ENCODING.name());

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
//                System.out.println("Read** " + line);
//                if (line.equalsIgnoreCase("END")) {
                if (line.startsWith("END")) {
//                    System.out.println("Found ENDX and goto Save data");
                    sectionData.put("I-", passengerData.toString());
                    break;
                }

                //check whether in SectionPassenger
                if (pullSectionPassenger && (line.charAt(0) == 'I' && line.charAt(1) == '-')) {
                    // found new passenger. Save old one
                    sectionData.put("I-", passengerData.toString());
                    passengerData = new StringBuffer();
                } else if (pullSectionPassenger) {
                    if (line.charAt(1) == '-') {
                        key = line.substring(0, 2);
                        sectionData.put(key, line);
                    }
                    passengerData.append(line);
                    passengerData.append("\n");
                    continue;
                }

//                System.out.println(line);
                if (line.charAt(0) == 'I' && line.charAt(1) == '-') {
                    //pull all passenger data.
                    pullSectionPassenger = true;
                    passengerData.append(line);
                    passengerData.append("\n");
                    continue;
                } else if (line.charAt(1) == '-') {
                    key = line.substring(0, 2);
                } else {
                    key = line.substring(0, 3);
                }
                sectionData.put(key, line);
//                System.out.println("Key " + key + " ,[" + line);
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

        String pnr = getField("pnr");
        String currency = getField("currency");

        BookingPnr bPnr = new BookingPnr(GDS, pnr, filename);
        bPnr.setCurrency(currency);

        bPnr.toString();
        return bPnr;
    }

    @Override
    BookingAirline buildBookingAirline() {

        String airlineCode = null;
        String ticketDateS = null;

        //Get PNR Data
        airlineCode = getField("airline code");

        System.out.println("airlineCode :" + airlineCode);
        MAirline mAir = new MAirline();
        mAir.setCode(airlineCode);
        List<MAirline> res = mAirticketService.searchAirline(mAir, 1);
        String airlineName = "";
        if (res != null) {
            MAirline mAirReturn = res.get(0);
            airlineName = mAirReturn.getName();
        }
        ticketDateS = getField("ticket date");
        String year = "20" + ticketDateS.substring(0, 2);
        String month = ticketDateS.substring(2, 4);
        String date = ticketDateS.substring(4);
        System.out.println("ticketdate" + date + month + year);

        Calendar calendar = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(date));
        Date ticketDate = calendar.getTime();

        BookingAirline bAir = new BookingAirline(airlineCode, airlineName, ticketDate);

        System.out.println(bAir.toString());
        return bAir;
    }

    @Override
    void buildBookingFlight(BookingAirline bAir) {
        MAmadeus flightNumber = amadeusMap.get("flight number");

        String ticketDateS = getField("ticket date");
        String flightOrder = getField("flight order");
        String year = "20" + ticketDateS.substring(0, 2);

        //Check how many rows there is.
        ArrayList<String> lines = (ArrayList<String>) sectionData.get(flightNumber.getSection());
//        System.out.println("Flight " + lines.size());

        ListIterator<String> iterator = lines.listIterator();
        BookingFlight bf = null;
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (isVoidFlight(line)) {
                System.out.println("Skipping - " + line);
                continue;
            }
//            System.out.println("Line " + line);
            String flightNo = getField("flight number", line);
            String flightPrefix = getField("flight prefix", line);
//            flightNo = bAir.getAirlineCode() + flightNo.replaceAll("\\s+", "");
            flightNo = flightPrefix.trim() + flightNo.replaceAll("\\s+", "");
            System.out.println("===== Flight No ===== : "+flightNo);
            String sourceCode = getField("source", line);
            String desCode = getField("destination", line);
            String deptDateS = getField("departure date", line);
            String arrivalDateS = getField("arrive date", line);

            Date deptDate = null;
            if (!deptDateS.isEmpty()) {
                deptDate = convertStringToDate(deptDateS + year);
            }
            Date arrvDate = null;
            if (!arrivalDateS.isEmpty()) {
                arrvDate = convertStringToDate(arrivalDateS + year);
            }

            String deptTime = getField("departure time", line);
            String arrvTime = getField("arrive time", line);
            String flightClass = getField("flight class" , line);
            bf = new BookingFlight(flightNo, sourceCode, desCode, deptDate, arrvDate, flightClass);
            bf.setDepartTime(deptTime);
            bf.setArriveTime(arrvTime);
            bf.setAdCost(new BigDecimal(BigInteger.ZERO));
            bf.setAdPrice(new BigDecimal(BigInteger.ZERO));
            bf.setAdTax(new BigDecimal(BigInteger.ZERO));
            bf.setChCost(new BigDecimal(BigInteger.ZERO));
            bf.setChPrice(new BigDecimal(BigInteger.ZERO));
            bf.setChTax(new BigDecimal(BigInteger.ZERO));
            bf.setInCost(new BigDecimal(BigInteger.ZERO));
            bf.setInPrice(new BigDecimal(BigInteger.ZERO));
            bf.setInTax(new BigDecimal(BigInteger.ZERO));
            bf.setOtCost(new BigDecimal(BigInteger.ZERO));
            bf.setOtPrice(new BigDecimal(BigInteger.ZERO));
            bf.setOtTax(new BigDecimal(BigInteger.ZERO));
            bf.setFlightOrder(new Integer(flightOrder));
            bAir.getBookingFlights().add(bf);
            bf.setBookingAirline(bAir);
        }

    }

    @Override
    void buildBookingPassenger(BookingAirline bAir) {
        UtilityFunction util = new UtilityFunction();
        String passengerTypes = new String("");
        int costRefIndex = 0;
        MAmadeus pName = amadeusMap.get("passenger name");
        ArrayList<String> lines = (ArrayList<String>) sectionData.get(pName.getSection());
//        System.out.println("Passenger " + lines.size());
        String ticketType = getField("ticket type");
        ListIterator<String> iterator = lines.listIterator();
        String line = null;
        BookingPassenger bp = null;
        while (iterator.hasNext()) {
            line = iterator.next();
//            System.out.println("line-> " + line);
            String[] mline = line.split("\\r?\\n");

            String passengerName = getField("passenger name", mline[0]).trim();
//            String passengerType = getField("passenger type", mline[0]);
//            if (StringUtils.isEmpty(passengerType)
//                    || !("ADT".equalsIgnoreCase(passengerType))
//                    || !("CHD".equalsIgnoreCase(passengerType))
//                    || !("INF".equalsIgnoreCase(passengerType))) {
//                passengerType = "NON";
//            }

            MAmadeus ticketNo1Ama = amadeusMap.get("ticket serial1");
            String section = ticketNo1Ama.getSection();
            String ticketLine = null;
            for (int i = 1; i < mline.length; i++) {
                if (mline[i].startsWith(section)) {
                    ticketLine = mline[i];
                    break;
                }
            }
//            System.out.println("Ticket ->" + ticketLine);
            String ticketNo1 = getField("ticket serial1", ticketLine);
            String ticketNo2 = getField("ticket serial2", ticketLine);
            String ticketNo3 = getField("ticket serial3", ticketLine);

//            System.out.println("passengerName " + passengerName);
            String[] splitName = passengerName.split("/");
            String lastName = splitName[0];
            String[] splitName2 = splitName[1].split(" ");
            // Using nameSeparatorIndex to handle case of blank space in first name.
            int nameSeparatorIndex = splitName[1].lastIndexOf(" ");
            String firstName = "";
            if(nameSeparatorIndex > 0){
                firstName = splitName[1].substring(0, nameSeparatorIndex);
            }else{
                firstName = splitName[1];
            }
//            String firstName = splitName[1].substring(0, splitName[1].length() - 2);
//            String initial = splitName[1].substring(splitName[1].length() - 2);
            String initial = "";
            String passengerType = "ADT";
            if(splitName2.length > 1){
                if (!splitName2[1].contains("(")) {
                    // plus 1 to exclude blankspace index.
                    initial = splitName[1].substring(nameSeparatorIndex+1);
                    passengerType = "ADT";
                } else {
                    int indexLess = splitName2[1].indexOf("(");
                    int indexMore = splitName2[1].indexOf(")");
                    initial = splitName2[1].substring(0, indexLess);
                    passengerType = splitName2[1].substring(indexLess + 1, indexMore);
                }
            }
            
            MAmadeus fareComAma = amadeusMap.get("fare commission");
            String fareSection = fareComAma.getSection();
            String fareLine = null;
            for (int i = 1; i < mline.length; i++) {
                if (mline[i].startsWith(fareSection)) {
                    fareLine = mline[i];
                    break;
                }
            }
            String fareCommission;
            if (fareLine == null) {
                fareCommission = "0";
            } else {
                fareCommission = getField("fare commission", fareLine).trim();
                fareCommission = fareCommission.replace("A", "");
            }
            String currency = getField("currency").trim();

            String ticket_fare = getTicketFare(currency);
//            String ticket_fare = getField("ticket fare").trim();
            ticket_fare = stripNumberDecimalString(ticket_fare);
            String total_amount = getField("ticket total").trim();
            BigDecimal tax = (util.convertStringToBigDecimal(total_amount)).subtract(util.convertStringToBigDecimal(ticket_fare));
//            if (tax < 0) {
            if (tax.compareTo(BigDecimal.ZERO) < 0) {
                tax = tax.multiply(new BigDecimal(-1));
            }

//            System.out.println("lastname [" + lastName + "] ,firstname[" + firstName + "] ,initial[" + initial + "] passengerType[" + passengerType + "]");
            bp = new BookingPassenger();
            bp.setFirstName(firstName);
            bp.setLastName(lastName);
            bp.setInitialName(initial);
            bp.setPassengerType(passengerType);
            bp.setTicketType(ticketType);
            bp.setTicketnoS1(ticketNo1);//ticketNoS1);
            bp.setTicketnoS2(ticketNo2);
            bp.setTicketnoS3(ticketNo3);
            bp.setTicketFare(util.convertStringToBigDecimal(ticket_fare));
            bp.setTicketTax(tax);
            bAir.getBookingPassengers().add(bp);
            bp.setBookingAirline(bAir);
            if (!passengerTypes.contains(passengerType)) {
                passengerTypes = passengerTypes + "," + passengerType;
                BigDecimal cost = new BigDecimal(BigInteger.ZERO);
                BigDecimal price = new BigDecimal(BigInteger.ZERO);
                if (isInternationalTicket(ticketType)) {
                    String costS = getField("cost").trim();
                    String costS2 = getField("cost2").trim();
                    System.out.println("Internal");
                    System.out.println("CostS : "+costS);
                    System.out.println("CostS2 : "+costS2);
                    // No cost line. Set to ticket_fare.
                    if (("0".equalsIgnoreCase(costS))||("0.00".equalsIgnoreCase(costS))) {
                        if((!"0".equalsIgnoreCase(costS2))&&(!"0.00".equalsIgnoreCase(costS2))){
                            costS = costS2;
                            System.out.println("case 1");
                        }else{
                            System.out.println("case 2");
                            costS = ticket_fare;
                        }
                    }
                    cost = util.convertStringToBigDecimal(costS);
                    System.out.println("cost [" + cost +"]");
                    price = cost.add(util.convertStringToBigDecimal(fareCommission));
                } else {
                    price = util.convertStringToBigDecimal(ticket_fare);
                    cost = (price.multiply((new BigDecimal(100)).subtract(new BigDecimal(fareCommission)))).divide(new BigDecimal(100));
                }
                costRefIndex++;
                //Update cost,price,tax according to passengertype
                // Only first flight
                BookingFlight bf = (BookingFlight) bAir.getBookingFlights().iterator().next();
//                BookingFlight bf = this.getMostEarlyFlight(bAir.getBookingPnr());
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
    }
    
    private String getTicketFare(String currency) {
        String ticketFare;
        if (!"THB".equalsIgnoreCase(currency)) {
            ticketFare = getField("ticket fare2").trim();
        } else {
            ticketFare = getField("ticket fare").trim();
        }
        return ticketFare;
    }
    
    private boolean isInternationalTicket(String ticketType) {
        return "X".equalsIgnoreCase(ticketType);
    }

    // Build Amadeus Map for parsing file data.
    private void buildMap() {
        listAmadeus = mAmadeusService.getAmadeusList();
        ListIterator<MAmadeus> iterator = listAmadeus.listIterator();
        amadeusMap = new HashMap();
        while (iterator.hasNext()) {
            MAmadeus ama = iterator.next();
//            System.out.println("Listing " + ama.getName());

            amadeusMap.put(ama.getName(), ama);
        }
    }

    @Override
    protected int processDataFile(String file) {

        int flag = 1;//success
        try {
//        System.out.println("*** Found " + file);
            buildContentList(file);
            BookingPnr bPnr = buildBookingPnr(file);
            BookingAirline bAir = buildBookingAirline();

            bPnr.getBookingAirlines().add(bAir);
            bAir.setBookingPnr(bPnr);
            buildBookingFlight(bAir);
            buildBookingPassenger(bAir);

            BookingPnr dbBookingPnr = bookingAirticketService.getBookingPnr(bPnr.getPnr());
            if (dbBookingPnr == null) {
                flag = bookingAirticketService.insertBookingPnr(bPnr);
            } else {
                System.out.println("BookPnr[" + bPnr.getPnr() + "] is existed.");
                flag = bookingAirticketService.updateBookingPnr(bPnr);
            }

        } catch (Exception e) {
            flag = 0;//failed
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    protected int archiveDataFile(String file, int option) {
        try {
            Path sourceFile = Paths.get(this.monitorDirectory + file);
            Path destFile = Paths.get(this.archivedDirectory + file);
            Path errFile = Paths.get(this.errorDirectory + file);

            if (option == 1) {
                Files.move(sourceFile, destFile, REPLACE_EXISTING);
            } else {
                Files.move(sourceFile, errFile, REPLACE_EXISTING);
            }
            return 1;

        } catch (IOException ex) {
            Logger.getLogger(MonitorAmadeus.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    protected String getField(String name) {
       System.out.println("get Field name : "+ name);
        String val = null;
        try {
            MAmadeus ama = amadeusMap.get(name);
            String line = null;
            if (StringUtils.isEmpty(ama.getSection())) {
                return null;
            }
            if (ama.getNodlm().isEmpty()) {
                ArrayList<String> lines = (ArrayList<String>) sectionData.get(ama.getSection());
//            System.out.println("lines size(" + lines.size() + ")");
//            System.out.println("Found class -> " + obj.getClass().getName());
                String foundLine = lines.get(0);
                line = foundLine.substring(ama.getSection().length());
                val = line.substring(ama.getStartlength() - 1, ama.getStartlength() - 1 + ama.getLength());
            } else {
                int node = Integer.parseInt(ama.getNodlm());
                ArrayList<String> sectionLine = (ArrayList<String>) sectionData.get(ama.getSection());
                String foundLine = sectionLine.get(0);
                line = foundLine.substring(ama.getSection().length());
                String[] lines = line.split(NODE_SEPARATOR);
                String foundNode = lines[node - 1];
                
                if(foundNode.length() != 0){
                    val = foundNode.substring(ama.getStartlength() - 1, ama.getStartlength() - 1 + ama.getLength());
                }else{
                    val= "";
                }
                
            }
            System.out.println("Key [" + name + "], Value [" + val + "]");
        } catch (NullPointerException ne) {
            System.out.println("NullPointerException on field [" + name + "]");
            ne.printStackTrace();
            System.out.println("Set value [" + name + "] to 0");
            val = new String("0");
        }
        return val.trim();
    }

    protected String getField(String name, String targetLine) {
        String val = null;
        MAmadeus ama = amadeusMap.get(name);
        if (StringUtils.isEmpty(ama.getSection())) {
            return null;
        }
        try {
            int node = Integer.parseInt(ama.getNodlm());
            targetLine = targetLine.substring(ama.getSection().length());
            String[] lines = targetLine.split(NODE_SEPARATOR);
            String line = lines[node - 1];

            if (ama.getLength() > 0) {
                val = line.substring(ama.getStartlength() - 1, ama.getStartlength() - 1 + ama.getLength());
            } else {
                val = line.substring(ama.getStartlength() - 1);
            }
            System.out.println("Key [" + name + "], Value [" + val + "]");
        } catch (StringIndexOutOfBoundsException se) {
            System.err.println("Cannot parse key[" + ama.toString() + "] ,line[" + targetLine + "]");
            val = "";
            se.printStackTrace();
        }
        return val;
    }

    public BookingAirticketService getBookingAirticketService() {
        return bookingAirticketService;
    }

    public void setBookingAirticketService(BookingAirticketService bookingAirticketService) {
        this.bookingAirticketService = bookingAirticketService;
    }

    private boolean isVoidFlight(String line) {
        String node[] = line.split(";");
        if (node.length < 5) {
            return true;
        }
        if ("VOID".equalsIgnoreCase(node[5])) {
            return true;
        }
        return false;
    }
}
