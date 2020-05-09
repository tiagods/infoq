/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.utils;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author tiagods
 */
public class DateUtil {
    
    public enum Pattern {
        DDMMYYY("dd/MM/yyyy"), 
        DDMMYYHHSS("dd/MM/yyyy HH:mm:ss");
        private String pattern;
        Pattern(String pattern){
            this.pattern = pattern;
        }
        public String getPattern() {
            return this.pattern;
        }
    }
    public static String formatarData(Date data, String pattern){
        return new SimpleDateFormat(pattern).format(data);
    }
    public static String formatarData(Date data){
        return formatarData(data, "dd/MM/yyyy");
    }
    
    public static String formatarData(Date date, int field){
        return DateFormat.getDateInstance(field).format(date);
    }
}
