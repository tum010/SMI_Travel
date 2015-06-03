/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Surachai
 */
public class UtilityFunction {

    public Date convertStringToDate(String day) {
        Date resultDate = new Date();
        try {
            if ((day != null) && (!"".equalsIgnoreCase(day))) {
                resultDate.setYear(Integer.parseInt(day.split("-")[0]) - 1900);
                resultDate.setMonth(Integer.parseInt(day.split("-")[1]) - 1);
                resultDate.setDate(Integer.parseInt(day.split("-")[2]));
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }

        return resultDate;
    }
    //another condition string to date
    public Date convertStringToDateS(String day) {
        Date resultDate = new Date();
        try {
            if ((day != null) && (!"".equalsIgnoreCase(day))) {
                resultDate.setYear(Integer.parseInt(day.split("-")[2]) - 1900);
                resultDate.setMonth(Integer.parseInt(day.split("-")[1]) - 1);
                resultDate.setDate(Integer.parseInt(day.split("-")[0]));
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

    public String inputString(Object input) {
        if (input == null) {
            return null;
        } else {
            return input.toString();
        }
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

    public static void main(String[] args) throws ParseException {
        UtilityFunction util = new UtilityFunction();
        String data = "'te'";
        System.out.println(data.replaceAll("'", "\'"));
        Date date = new Date();
        System.out.println("No format :" + date);
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat();
        System.out.println("No pattern format :" + df.format(date));
        df.applyPattern("yyyyMM");
        System.out.println("yyyyMM format :" + df.format(date));
        df.applyPattern("dd/mm/yyyy HH:mm:ss");
        System.out.println("dd/mm/yyyy HH:mm:ss format :" + df.format(date));
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
}
