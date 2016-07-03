/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.util;

import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.model.nirvana.SsDataexch;
import com.smi.travel.model.nirvana.SsDataexchTr;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Surachai
 */
public class UtilityFunction {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String[] tensNames = {
        "",
        " ten",
        " twenty",
        " thirty",
        " forty",
        " fifty",
        " sixty",
        " seventy",
        " eighty",
        " ninety"
      };

    private static final String[] numNames = {
      "",
      " one",
      " two",
      " three",
      " four",
      " five",
      " six",
      " seven",
      " eight",
      " nine",
      " ten",
      " eleven",
      " twelve",
      " thirteen",
      " fourteen",
      " fifteen",
      " sixteen",
      " seventeen",
      " eighteen",
      " nineteen"
    };
    
    private static final String URL = ".smi";

    public Date convertStringToDate(String day) {
        Date resultDate = new Date();
        String format1 = "yyyy-MM-dd";
        String format2 = "dd-MM-yyyy";
        try {
            if ((day != null) && (!"".equalsIgnoreCase(day))) {
                if(isValidFormat(format1,day)){
                    SimpleDateFormat sdf = new SimpleDateFormat(format1, Locale.US);
                    resultDate = sdf.parse(day);
                }else if(isValidFormat(format2,day)){
                    SimpleDateFormat sdf = new SimpleDateFormat(format2, Locale.US);
                    resultDate = sdf.parse(day);
                }
                
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return resultDate;
    }
    //another condition string to date
    public Date convertStringToDateS(String day) {
        Date resultDate = new Date();
        try {
            if ((day != null) && (!"".equalsIgnoreCase(day))) {
                resultDate.setDate(Integer.parseInt(day.split("-")[0]));
                resultDate.setMonth(Integer.parseInt(day.split("-")[1]) - 1);
                resultDate.setYear(Integer.parseInt(day.split("-")[2]) - 1900);
                
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
        
        return resultDate;
    }
    
    public Date convertStringToDateTime(String day) {
        Date resultDate = new Date();
        try {
            if ((day != null) && (!"".equalsIgnoreCase(day))) {
                day = day.replaceAll(" ", "-");
                day = day.replaceAll(":", "-");
//                day = day.replaceAll(".", "-");
                resultDate.setYear(Integer.parseInt(day.split("-")[0]) - 1900);
                resultDate.setMonth(Integer.parseInt(day.split("-")[1]) - 1);
                resultDate.setDate(Integer.parseInt(day.split("-")[2]));
                resultDate.setHours(Integer.parseInt(day.split("-")[3]));
                resultDate.setMinutes(Integer.parseInt(day.split("-")[4]));
//                resultDate.setSeconds(Integer.parseInt(day.split("-")[5]));
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }

        return resultDate;
    }

    public String StringUtilReplaceChar(String input) {
        if (input == null) {
            return null;
        } else {
            return input.replaceAll("[',]", "");
        }
    }

    public String StringUtilReplaceDouble(String input) throws UnsupportedEncodingException {
        String result = "";
        byte[] quoteutf8 = {(byte) 0xE2, (byte) 0x80, (byte) 0x9D};
        String decodedPlatformDefault = new String(quoteutf8, "Windows-1252");
        byte[] encodedPlatformDefault = decodedPlatformDefault.getBytes("Windows-1252");

        for (byte i : encodedPlatformDefault) {
            System.out.print(String.format("%02x ", i));

        }
        result = new String(encodedPlatformDefault, "UTF-8");
        return result;
    }

    public String convertDateToString(Date date) {
        if (date == null) {
            return "";
        }
        String month = String.valueOf(date.getMonth() + 1);
        String day = String.valueOf(date.getDate());
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        return String.valueOf(date.getYear() + 1900) + "-" + month + "-" + day;
    }

    public Date convertStringToTime(String input) {
        Date date = new Date();
        try {
            if (input != null) {
                date.setHours(Integer.parseInt(input.split(":")[0]));
                date.setMinutes(Integer.parseInt(input.split(":")[1]));
                date.setSeconds(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }

        return date;
    }

    public String convertTimeToString(Date date) {
        if (date == null) {
            return "";
        }
        String Hours = String.valueOf(date.getHours());
        if (Hours.length() == 1) {
            Hours = "0" + Hours;
        }
        String Minutes = String.valueOf(date.getMinutes());
        if (Minutes.length() == 1) {
            Minutes = "0" + Minutes;
        }

        return Hours + ":" + Minutes;
    }

    public Integer convertStringToInteger(String input) {
        if (("".equalsIgnoreCase(input)) || (input == null)) {
            return 0;
        } else {
            if(input.indexOf(".") != -1){
                input = input.substring(0, input.indexOf("."));
            }
            input = input.replaceAll(",", "");
            return Integer.parseInt(input);
        }
    }

    public Integer convertObjectToInteger(Object input) {
        if ((input == null)) {
            return 0;
        } else {

            return Integer.parseInt(input.toString());
        }
    }

    public long convertStringTolong(String input) {
        if (("".equalsIgnoreCase(input)) || (input == null)) {
            return 0;
        } else {
            input = input.replaceAll(",", "");
            return Long.parseLong(input);
        }
    }
    
    public BigDecimal convertStringToBigDecimal(String input) {
        if (("".equalsIgnoreCase(input)) || (input == null)) {
            return new BigDecimal(BigInteger.ZERO);
        } else {
            input = input.replaceAll(",", "");
            return new BigDecimal(input);
        }
    }

    public String inputString(Object input) {
        if (input == null) {
            return null;
        } else {
            return input.toString();
        }
    }
    
    public String setFormatMoney(Object data){
        if(data == null)return "";
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format(data);
    }

    public String ConvertString(Object input) {
        if (input == null) {
            return "";
        }
        if ("null".equalsIgnoreCase(input.toString())) {
            return "";
        } else {
            return input.toString();
        }
    }

    public int ConvertInt(Object input) {
        if (input == null) {
            return 0;
        }
        if ("null".equalsIgnoreCase(input.toString()) || ("".equalsIgnoreCase(input.toString()))) {
            return 0;
        } else {
            return Integer.parseInt(input.toString());
        }
    }

    public String CheckNullString(String input) {
        if (input == null) {
            return "";
        }
        if ("null".equalsIgnoreCase(input)) {
            return "";
        } else {
            return input.toString();
        }
    }
    
    public String SetFormatDate(Date paramdate,String format){
        //"dd MMM yyyy hh:mm:ss"
        if(paramdate == null)return null;
        return new SimpleDateFormat(format, new Locale("us", "us")).format(paramdate);
    }

    public static void main(String[] args) throws ParseException {
        UtilityFunction util = new UtilityFunction();
        String data = "2016-06-01";
        System.out.println(util.convertStringToDate(data));
        String data2 = "05-06-2018";
        System.out.println(util.convertStringToDate(data2));
//        System.out.println(data.replaceAll("'", "\'"));
//        Date date = new Date();
//        System.out.println("No format :" + date);
//        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat();
//        System.out.println("No pattern format :" + df.format(date));
//        df.applyPattern("yyyyMM");
//        System.out.println("yyyyMM format :" + df.format(date));
//        df.applyPattern("dd/mm/yyyy HH:mm:ss");
//        System.out.println("dd/mm/yyyy HH:mm:ss format :" + df.format(date));
    }

    public int getDateDiff(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public String ReplaceEnterKey(String input) {
        String data = input;
        if (input == null) {
            return "";
        }
        data = data.replaceAll("\n", "");
        data = data.replaceAll(System.getProperty("line.separator"), "");
        data = data.replaceAll("\\r|\\n", "");
        return data;
    }
   
    private static String convertLessThanOneThousand(int number) {
        String soFar;
        int thousand = number;
        int hundred = 0;
        thousand -= (thousand/1000)*1000;
        hundred = (thousand/100 == 0 ? 0 : 1);
        String and = "";
        if((number%100 != 0) && (hundred == 1)){              
            and = " and";
        }
        if (number % 100 < 20){
          soFar = numNames[number % 100];
          number /= 100;
        }
        else {
          soFar = numNames[number % 10];
          number /= 10;

          soFar = tensNames[number % 10] + soFar;
          number /= 10;
        }

        if (number == 0) return soFar;
        return numNames[number] + " hundred" + and + soFar;
    }


    public static String convert(long number) {
      // 0 to 999 999 999 999
      if (number == 0) { return "ZERO"; }

      String snumber = Long.toString(number);

      // pad with "0"
      String mask = "000000000000";
      DecimalFormat df = new DecimalFormat(mask);
      snumber = df.format(number);

      // XXXnnnnnnnnn
      int billions = Integer.parseInt(snumber.substring(0,3));
      // nnnXXXnnnnnn
      int millions  = Integer.parseInt(snumber.substring(3,6));
      // nnnnnnXXXnnn
      int hundredThousands = Integer.parseInt(snumber.substring(6,9));
      // nnnnnnnnnXXX
      int thousands = Integer.parseInt(snumber.substring(9,12));

      String tradBillions;
      switch (billions) {
      case 0:
        tradBillions = "";
        break;
      case 1 :
        tradBillions = convertLessThanOneThousand(billions)
        + " billion ";
        break;
      default :
        tradBillions = convertLessThanOneThousand(billions)
        + " billion ";
      }
      String result =  tradBillions;

      String tradMillions;
      switch (millions) {
      case 0:
        tradMillions = "";
        break;
      case 1 :
        tradMillions = convertLessThanOneThousand(millions)
           + " million ";
        break;
      default :
        tradMillions = convertLessThanOneThousand(millions)
           + " million ";
      }
      result =  result + tradMillions;

      String tradHundredThousands;
      switch (hundredThousands) {
      case 0:
        tradHundredThousands = "";
        break;
      case 1 :
        tradHundredThousands = "one thousand ";
        break;
      default :
        tradHundredThousands = convertLessThanOneThousand(hundredThousands)
           + " thousand ";
      }
      result =  result + tradHundredThousands;

      String tradThousand;
      tradThousand = convertLessThanOneThousand(thousands);
      result =  result + tradThousand;

      // remove extra spaces!
      return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ").toUpperCase();
    }
     public static String changPoint(String point){
        String text = " point ";
        String one = point.substring(0,1);
        String two = point.substring(1,2);
        System.out.println("Point SubString : " + one + " : " + two );
        if("00".equalsIgnoreCase(point)){
            return "";
        }
        String array[] = { one, two };
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i] != "") {
                System.out.println("Array : "+array[i]+":");
                if ("0".equals(array[i]) && i != 1) {
                        text += "zero ";
                } else if ("1".equals(array[i])) {
                        text += "one ";
                } else if ("2".equals(array[i])) {
                        text += "two ";
                } else if ("3".equals(array[i])) {
                        text += "three ";
                } else if ("4".equals(array[i])) {
                        text += "four ";
                } else if ("5".equals(array[i])) {
                        text += "five ";
                } else if ("6".equals(array[i])) {
                        text += "six ";
                } else if ("7".equals(array[i])) {
                        text += "seven ";
                } else if ("8".equals(array[i])) {
                        text += "eight ";
                } else if ("9".equals(array[i])) {
                        text += "nine ";
                }
            }
        }
	System.out.println("Amount :  " + text);
	return text.toUpperCase();
    }
    public String GetRounting( List<AirticketFlight> FlightList){
        String rounting = "";
        for(int i =0;i<FlightList.size();i++){
            if(FlightList.get(i).getMItemstatus().getId().equalsIgnoreCase("1")){
                System.out.println(FlightList.get(i).getSourceCode()+"-"+FlightList.get(i).getDesCode());
                String source = FlightList.get(i).getSourceCode();
                String des = FlightList.get(i).getDesCode();
                if(i == 0){
                rounting += source + "-" + des;
                }else{
                if (!rounting.substring(rounting.lastIndexOf("-") + 1).equalsIgnoreCase(source)) {
                    rounting += "," + source + "-" + des;
                } else {
                    rounting += "-" + des;
                }
                }
            }
            
            
        }
        return rounting;
    }

    public String getAddressUrl(String requestUrl){
        return requestUrl.substring(requestUrl.lastIndexOf("/")+1).replaceAll(URL, "");
    }
    
    public String getCustomerName(Customer cus){
        String name ="";
        if(cus == null){
            return "";
        }
        String MInitial= "";
        if(cus.getMInitialname() != null){
            MInitial = cus.getMInitialname().getName();
        }
        name = MInitial +" "+cus.getLastName() +" "+cus.getFirstName();
        return name;
    }
    
    public static String getObjectString(Object object){
        return object == null ? "" : object.toString();
    }
    
    public  String[] getTagPDescription(String source ){
        String[] output = {"",""};
        String data[] = source.split("\n");
        for(int i =0;i<data.length;i++){
            //step 2 find tag p
            int indexTagP = data[i].indexOf("<P>");
            //step 3 cut data to new field
            if(indexTagP != -1){
                String tagdescription = data[i].substring(indexTagP+3 ,data[i].indexOf("</P>"));
                String datatemp[] = data[i].split("<P>");
                output[0] += datatemp[0]+"\n";
//                int dl = data[i].length() / 70 ;
//                for(int x = 0 ; x < dl ; x++){
//                    output[1] += "\n";
//                }
                output[1] += tagdescription.trim();
            }else{
                if(i < data.length-1){
                    output[0] += data[i]+"\n";
                }else{
                    output[0] += data[i];
                }
            }
//            int dl = data[i].length() / 70 ;
//            for(int x = 0 ; x < dl ; x++){
//                output[1] += "\n";
//            }
            output[1] += "\n";
        }
        return output;
    }
    
    public String convertStringToDateFormat(String date){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        String dateformatstring = "";
        if((!"null".equalsIgnoreCase(String.valueOf(date)) && date != null) && !"".equalsIgnoreCase(String.valueOf(date))){
            String datetemp = "";
            String Date[] = String.valueOf(date).split("\r\n");
            if(Date.length > 1 ){
               for(int x= 0; x <Date.length;x++){
                   if(Date[x] != null && !"".equalsIgnoreCase(Date[x].trim())){
                        datetemp += ConvertString(dateformat.format(convertStringToDate(String.valueOf(Date[x]).trim())))+"\n";
                        dateformatstring = datetemp ;
                   }
               }
            }else{
              dateformatstring = ConvertString(dateformat.format(convertStringToDate(String.valueOf(date).trim())));
            }
        }
        return dateformatstring;
    }
    
    public String convertStringToDecimalFormat(String data){
        DecimalFormat df = new DecimalFormat("#,##0.00"); 
        String decimalformatstring = "0.00";
        if((!"null".equalsIgnoreCase(String.valueOf(data)) && data != null) && !"".equalsIgnoreCase(String.valueOf(data))){
            String datatemp = "";
            String dataarr[] = String.valueOf(data).split("\r\n");
            if(dataarr.length > 1 ){
               for(int x= 0; x <dataarr.length;x++){
                   if(dataarr[x] != null && !"".equalsIgnoreCase(dataarr[x].trim())){
                        datatemp += ConvertString(df.format(new BigDecimal(String.valueOf(dataarr[x]).trim())))+"\n";
                        decimalformatstring = datatemp ;
                   }
               }
            }else{
              decimalformatstring = ConvertString(df.format(new BigDecimal(ConvertString(data).trim())));
            }
        }
        return decimalformatstring;
    }
    
    public String generateDataAreaNirvana(String data, int lengthFix) {
        String dataArea = "";
        dataArea += data;
        for(int i=0; i<lengthFix-data.length(); i++){
            dataArea += " ";
        }
        return dataArea;
    }
    
    public void logsNirvana(SsDataexch data , String rowid){
        logger.info("===================  Header  ========================");
        logger.info(" data_cd : " +  data.getDataCd());
        logger.info(" data_no : " +  data.getDataNo());
        logger.info(" ent_sys_cd : " +  data.getEntSysCd());
        logger.info(" ent_sys_date : " +  data.getEntSysDate());
        logger.info(" ent_date_no : " +  data.getEntDataNo());
        logger.info(" ent_comment : " +  data.getEntComment());
        logger.info(" rcv_sys_cd : " +  data.getRcvSysCd());
        logger.info(" rcv_sta_cd : " +  data.getRcvStaCd());
        logger.info(" rcv_sys_date : " +  data.getRcvSysDate());
        logger.info(" rcv_comment : " +  data.getRcvComment());
        logger.info(" tra_nes_cd : " +  data.getTraNesCd());
        logger.info(" tra_sta_cd : " +  data.getTraStaCd());
        logger.info(" tra_sys_date : " +  data.getTraSysDate());
        logger.info("===============  Header Data Area  ==================");
        logger.info(" rowid : " +  rowid);
        logger.info(" data_area : " +  data.getDataArea());
        logger.info("===================  Detail  ========================");
        List<SsDataexchTr> ssDataexchTrList = data.getSsDataexchTrList();
        for(int i=0; i<ssDataexchTrList.size(); i++){
            SsDataexchTr detail = ssDataexchTrList.get(i);
            logger.info(" data_seq : " +  detail.getDataSeq());
            logger.info(" data_cd : " +  detail.getDataCd());
            logger.info(" data_no : " +  detail.getDataNo());
            logger.info(" data_seq : " +  detail.getDataSeq());
            logger.info(" ent_sys_cd : " +  detail.getEntSysCd());
            logger.info(" ent_sys_date : " +  detail.getEntSysDate());
            logger.info(" ent_date_no : " +  detail.getEntDataNo());
            logger.info(" ent_comment : " +  detail.getEntComment());
            logger.info(" rcv_sys_cd : " +  detail.getRcvSysCd());
            logger.info(" rcv_sta_cd : " +  detail.getRcvStaCd());
            logger.info(" cv_sys_date : " +  detail.getCvSysDate());
            logger.info(" rcv_comment : " +  detail.getRcvComment());
            logger.info(" tra_nes_cd : " +  detail.getTraNesCd());
            logger.info(" tra_sta_cd : " +  detail.getTraStaCd());
            logger.info(" tra_sys_date : " +  detail.getTraSysDate());
            logger.info("===============  Detail Data Area "+ detail.getDataSeq() +" ==================");
            logger.info(" data_area : " +  detail.getDataArea());
        }
        
    }
    
    public void logsGalileo(String data , int option){
        if(option == 1){
            logger.info("Cannot search airline code " + data);
        
        }else if(option == 2){
            logger.info("Cannot import file " + data);
            
        }
        
    }
    

    public boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }
    
    public String covertStringDateToFormatYMD(String date){
        String dateformat = "";
        if(!"".equalsIgnoreCase(date) && date != null ) {
            dateformat = new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(convertStringToDate(date));
        }
        return dateformat;
    }
    
    public String convertRefNo(String refNo){
        if(refNo.indexOf("-") >= 0){
            refNo = (refNo.trim()).replaceAll("-", "");
        }
        return refNo;
    } 

    public String getTaxBranch(String invTo, String taxBranch) {
        String result = "";
        if(taxBranch != null && !"".equalsIgnoreCase(taxBranch) && "Head Office".equalsIgnoreCase(taxBranch)){
            String[] characterList = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
            boolean isEng = false;
            String invToFirstChar = invTo.substring(0, 1);

            charactorLoop:
            for(String character : characterList){
                if(character.equalsIgnoreCase(invToFirstChar)){
                    isEng = true;
                    break charactorLoop;
                }
            }

            if(isEng){
                result = "Head Office";

            } else {
                result = "สำนักงานใหญ่";

            }
        
        } else { 
            result = taxBranch;
        }
            
        return result;
    }
    
    public String generateSpecialCharacter(String name) {
        String specialchar1[] = {"\\+","-","&","<",">","\"","#","@","[","]"};
        String specialchar2[] = {"%2B","%2D","%26","%3C","%3E","%22","%23","%40","%5B","%5D"};
        for(int i = 0; i < specialchar1.length ; i++){
            while(name.indexOf(specialchar1[i]) != -1){
                name = name.replaceAll(specialchar1[i],specialchar2[i]);
            }
        }
        return name;
    }
    
    public String generateSpecialCharacterEncode(String name) {
        String specialchar2[] = {"+","-","&","<",">","\"","#","@","[","]"};
        String specialchar1[] = {"%2B","%2D","%26","%3C","%3E","%22","%23","%40","%5B","%5D"};
        for(int i = 0; i < specialchar1.length ; i++){
            while(name.indexOf(specialchar1[i]) != -1){
                name = name.replaceAll(specialchar1[i],specialchar2[i]);
            }
        }
        return name;
    }

    public BigDecimal calculateRoundUp(BigDecimal price) {
        BigDecimal result = new BigDecimal(BigInteger.ZERO);
        
        //Remove Dot       
        String[] priceArray = (String.valueOf(price)).split("\\.");
        System.out.println("Price Array[0] : " + priceArray[0]);
//        System.out.println("Price Array[1] : " + priceArray[1]);
        
        //Convert to String
        String priceStr = String.valueOf(priceArray[0].substring(0, priceArray[0].length()- 1));
        System.out.println("Price String : " + priceStr);
        
        //Cut last number for check with numberList
        int lastNumber = Integer.parseInt(priceArray[0].substring(priceArray[0].length() - 1, priceArray[0].length()));
        System.out.println("Last Number : " + lastNumber);
          
        if(lastNumber == 1 || lastNumber == 2 || lastNumber == 3 || lastNumber == 4) {
            priceStr += "5";
            System.out.println("Round up to five : "+priceStr);
            result = new BigDecimal(priceStr);

        } else if(lastNumber == 6 || lastNumber == 7 || lastNumber == 8 || lastNumber == 9) {
            int countNine = 0;           
            
            loopCountNine:           
            for(int i = 1; i < priceStr.length(); i++){
                String priceTemp = priceStr.substring(priceStr.length() - i, priceStr.length() - (i - 1));
                System.out.println("Price Temp ["+i+"] : " + priceTemp);
                lastNumber = Integer.parseInt(priceTemp);
                System.out.println("Last number ["+i+"] : " + lastNumber);
                
                if(lastNumber == 9){
                    countNine += 1;
                
                } else {
                    break loopCountNine;
                }
            }

            priceStr = String.valueOf(Integer.parseInt(priceStr.substring(0,priceStr.length() - countNine)) + 1);
            
            for(int i = 0; i <= countNine; i++){
                priceStr += "0";
            }
            
            result = new BigDecimal(priceStr);

        } else {
            result = new BigDecimal(priceArray[0]);
            
        } 
        
        System.out.println("Result : " + result);
        
        return result;
    }

}
