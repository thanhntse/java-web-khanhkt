/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author thinkpad
 */
public class Tools {

    //Automatically generating an increasing code
    //Ex: P0000123 -> prefix: P, length=7, curNumber=123
    public static String generateCode(String prefix, int length, int curNumber) {
        String formatStr = "%0" + length + "d"; //Ex: %07d
        return prefix + String.format(formatStr, curNumber);
    }

    public static int getNumberInCode(String code, String prefix) {
        return Integer.parseInt(code.substring(prefix.length()));
    }

    public static int getNumberInCode(String code, int prefixLen) {
        return Integer.parseInt(code.substring(prefixLen));
    }

    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String dateString = currentDate.format(formatter);
        return dateString;
    }
}
