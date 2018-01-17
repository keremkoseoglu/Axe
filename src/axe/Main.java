/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package axe;

/**
 *
 * @author Kerem
 */

import java.text.*;
import java.util.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static axe.config.Configuration config;
    public static axe.sap.Sap sap;
    public static axe.axata.Axata axata;
    private static Calendar cal;
    private static SimpleDateFormat sdf;    
    
    public static void main(String[] args) {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        
        // Config'i yarat
        try
        {
            config = new axe.config.Configuration();
            appendLog("Konfigürasyon dosyası okundu");
        }
        catch(Exception ex)
        {
            appendLog("Konfigürasyon dosyasında hata: " + ex.toString());
            return;
        }
        
        // SAP bağlantı testi
        try
        {
            sap = new axe.sap.Sap();
            sap.connect();
            sap.disconnect();
            appendLog("SAP bağlantı testi başarılı");
        }
        catch(Exception ex)
        {
            appendLog("SAP bağlantı testi hatası: " + ex.toString());
            return;
        }
        
        // Axata bağlantı testi
        try
        {
            axata = new axe.axata.Axata();
            axata.connect();
            axata.disconnect();
            appendLog("Axata bağlantı testi başarılı");
        }
        catch (Exception ex)
        {
            appendLog("Axata bağlantı testi hatası:" + ex.toString());
            return;
        }
        
        // Thread'ı başlat
        axe.integration.IntThread it = new axe.integration.IntThread();
        it.addIntEventListener(
                new axe.integration.IntEventListener()
                {
                    public void intEventOccured(axe.integration.IntEvent Evt)
                    {
                        Main.appendLog(Evt.text);
                    }
                }
        );        
        it.start();           
        
        // Program başladı mesajı
        appendLog("Program başladı");
        
    }
    
    public static void appendLog(String Entry)
    {   
        cal = Calendar.getInstance();
        String logText = "[" + sdf.format(cal.getTime()) + "] " + Entry;
        logText = logText.replaceAll("ı", "i");
        logText = logText.replaceAll("İ", "I");
        logText = logText.replaceAll("ğ", "g");
        logText = logText.replaceAll("Ğ", "G");
        logText = logText.replaceAll("ü", "u");
        logText = logText.replaceAll("Ü", "U");
        logText = logText.replaceAll("ş", "s");
        logText = logText.replaceAll("Ş", "S");
        logText = logText.replaceAll("ö", "o");
        logText = logText.replaceAll("Ö", "O");
        logText = logText.replaceAll("ç", "c");
        logText = logText.replaceAll("Ç", "C");
        System.out.println(logText);
    }    
    
    public static void exitProgram()
    {
        System.exit(0);
    }

}
