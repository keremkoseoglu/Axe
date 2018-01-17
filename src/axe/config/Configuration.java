/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package axe.config;

/**
 *
 * @author Kerem
 */

import java.util.*;

public class Configuration {

    private ArrayList fileCont;
    
    public String sapIP, sapClient, sapUser, sapPass, sapSystem, sapLang;
    public String sqlIP, sqlDatabase, sqlUser, sqlPass;
    public long intPeriod;
    public boolean intExitAfterComplete;
    
    public Configuration() throws Exception 
    {   
        fileCont = Util.readTextFile("config.txt");
        if (fileCont.size() <= 0) throw new Exception("Konfigürasyon dosyasında bir hata var");
        
        sapIP = getValue("sapIP");
        sapClient = getValue("sapClient");
        sapUser = getValue("sapUser");
        sapPass = getValue("sapPass");
        sapSystem = getValue("sapSystem");
        sapLang = getValue("sapLang");
        
        sqlIP = getValue("sqlIP");
        sqlDatabase = getValue("sqlDatabase");
        sqlUser = getValue("sqlUser");
        sqlPass = getValue("sqlPass");
        
        intPeriod = Long.valueOf(getValue("intPeriod")) * 1000;  
        intExitAfterComplete = getValue("intExitAfterComplete").equals("TRUE");
    }    
    
    private String getValue(String Key)
    {
        int eqPos = 0;
        String curKey = "";
        String ret = "";
        boolean eqFound = false;
        boolean keyFound = false;
        
        for (int n = 0; n < fileCont.size(); n++)
        {
            String s = (String) fileCont.get(n);
            eqFound = false;
            keyFound = false;
            curKey = "";
            
            for (int m = 0; m < s.length(); m++)
            {
                String letter = s.substring(m, m + 1);
                if (!eqFound)
                {
                    if (letter.equals("=")) eqFound = true; else curKey += letter;
                }
                else
                {
                    if (curKey.equals(Key)) 
                    {
                        keyFound = true;
                        ret += letter;
                    }
                }
            }
            
            if (keyFound) return ret;
        }
        
        return ret;
    }    
}
