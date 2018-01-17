/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package axe.integration;

/**
 *
 * @author Kerem
 */

import axe.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;

public class IntTimer extends TimerTask {

    protected EventListenerList listenerList;
    
    public IntTimer() 
    {
        listenerList = new EventListenerList();
    }

    @Override
    public void run() {
        // Bağlantıları aç
        try
        {
            fireIntEvent("Entegrasyon başlıyor");
            Main.sap.connect();
            Main.axata.connect();
            fireIntEvent("Bağlantılar açıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("Bağlantı hatası:" + ex.toString());
            return;
        }
        
        
        // ENT004 Aktarımı
        try
        {
            fireIntEvent("ENT004 aktarımı başlatılıyor");
            ArrayList al = Main.sap.ZAXATA_ENT004_EXPORT();
            fireIntEvent("SAP'den ENT004 verileri çekildi");
            
            Main.axata.ENT004_WRITE(al);
            fireIntEvent("Veriler AXATA'ya yazıldı");
            
            Main.sap.ZAXATA_ENT004_RETURN(al);
            fireIntEvent("Entegrasyon bilgisi SAP'ye aktarıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT004 aktarım hatası: " + ex.toString());
            return;
        }  
        
        // ENT000 Aktarımı
        try
        {
            fireIntEvent("ENT000 aktarımı başlatılıyor");
            ArrayList al = Main.sap.ZAXATA_ENT000_EXPORT();
            fireIntEvent("SAP'den ENT000 verileri çekildi");
            
            Main.axata.ENT000_WRITE(al);
            fireIntEvent("Veriler AXATA'ya yazıldı");
            
            Main.sap.ZAXATA_ENT000_RETURN(al);
            fireIntEvent("Entegrasyon bilgisi SAP'ye aktarıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT000 aktarım hatası: " + ex.toString());
            return;
        }  
        
        // ENT001 Aktarımı
        try
        {
            fireIntEvent("ENT001 aktarımı başlatılıyor");
            ArrayList al = Main.sap.ZAXATA_ENT001_EXPORT();
            fireIntEvent("SAP'den ENT001 verileri çekildi");
            
            Main.axata.ENT001_WRITE(al);
            fireIntEvent("Veriler AXATA'ya yazıldı");
            
            Main.sap.ZAXATA_ENT001_RETURN(al);
            fireIntEvent("Entegrasyon bilgisi SAP'ye aktarıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT001 aktarım hatası: " + ex.toString());
            return;
        }          
        
        // ENT019 Aktarımı
        try
        {
            fireIntEvent("ENT019 aktarımı başlatılıyor");
            ArrayList al = Main.sap.ZAXATA_ENT019_EXPORT();
            fireIntEvent("SAP'den ENT019 verileri çekildi");
            
            Main.axata.ENT019_WRITE(al);
            fireIntEvent("Veriler AXATA'ya yazıldı");
            
            Main.sap.ZAXATA_ENT019_RETURN(al);
            fireIntEvent("Entegrasyon bilgisi SAP'ye aktarıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT019 aktarım hatası: " + ex.toString());
            return;
        }
        
        
        // ENT013 Aktarımı
        try
        {
            fireIntEvent("ENT013 aktarımı başlatılıyor");
            ArrayList al = Main.sap.ZAXATA_ENT013_EXPORT();
            fireIntEvent("SAP'den ENT013 verileri çekildi");
            
            Main.axata.ENT013_WRITE(al);
            fireIntEvent("Veriler AXATA'ya yazıldı");
            
            Main.sap.ZAXATA_ENT013_RETURN(al);
            fireIntEvent("Entegrasyon bilgisi SAP'ye aktarıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT013 aktarım hatası: " + ex.toString());
            return;
        }
        
        // ENT005 Aktarımı
        try
        {
            fireIntEvent("ENT005 aktarımı başlatılıyor");
            ArrayList al = Main.axata.ENT005_READ();
            fireIntEvent("AXATA'dan ENT005 verileri çekildi");
            
            Main.sap.ZAXATA_ENT005_IMPORT(al);
            fireIntEvent("Veriler SAP'ye yazıldı");
            
            Main.axata.ENT005_RETURN(al);
            fireIntEvent("Entegrasyon bilgisi AXATA'ya aktarıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT005 aktarım hatası: " + ex.toString());
            return;            
        }
        
        // ENT006 Aktarımı
        try
        {
            fireIntEvent("ENT006 aktarımı başlatılıyor");
            ArrayList al = Main.axata.ENT006_READ();
            fireIntEvent("AXATA'dan ENT006 verileri çekildi");
            
            Main.sap.ZAXATA_ENT006_IMPORT(al);
            fireIntEvent("Veriler SAP'ye yazıldı");
            
            Main.axata.ENT006_RETURN(al);
            fireIntEvent("Entegrasyon bilgisi AXATA'ya aktarıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT006 aktarım hatası: " + ex.toString());
            return;            
        }
        
        // ENT007 Aktarımı
        try
        {
            fireIntEvent("ENT007 aktarımı başlatılıyor");
            ArrayList al = Main.axata.ENT007_READ();
            fireIntEvent("AXATA'dan ENT007 verileri çekildi");
            
            Main.sap.ZAXATA_ENT007_IMPORT(al);
            fireIntEvent("Veriler SAP'ye yazıldı");
            
            Main.axata.ENT007_RETURN(al);
            fireIntEvent("Entegrasyon bilgisi AXATA'ya aktarıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT007 aktarım hatası: " + ex.toString());
            return;            
        } 
        
        // ENT008 Aktarımı
        try
        {
            fireIntEvent("ENT008 aktarımı başlatılıyor");
            ArrayList al = Main.axata.ENT008_READ();
            fireIntEvent("AXATA'dan ENT008 verileri çekildi");
            
            Main.sap.ZAXATA_ENT008_IMPORT(al);
            fireIntEvent("Veriler SAP'ye yazıldı");
            
            Main.axata.ENT008_RETURN(al);
            fireIntEvent("Entegrasyon bilgisi AXATA'ya aktarıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT008 aktarım hatası: " + ex.toString());
            return;            
        }            
        
        // ENT011 Aktarımı
        try
        {
            fireIntEvent("ENT011 aktarımı başlatılıyor");
            ArrayList al = Main.axata.ENT011_READ();
            fireIntEvent("AXATA'dan ENT011 verileri çekildi");
            
            Main.sap.ZAXATA_ENT011_IMPORT(al);
            fireIntEvent("Veriler SAP'ye yazıldı");
            
            Main.axata.ENT011_RETURN(al);
            fireIntEvent("Entegrasyon bilgisi AXATA'ya aktarıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT011 aktarım hatası: " + ex.toString());
            return;            
        }   
        
        // ENT020 Aktarımı
        try
        {
            fireIntEvent("ENT020 aktarımı başlatılıyor");
            axe.sap.ZPP_ENT020_S_PARAM p = Main.sap.ZAXATA_ENT020_GET_DATE();
            fireIntEvent("Sınır tarih tespit edildi: " + p.DATUM);
            
            ArrayList al = Main.axata.ENT020_READ(p);
            fireIntEvent("AXATA'dan ENT020 verileri çekildi");            
            
            Main.sap.ZAXATA_ENT020_IMPORT(p, al);
            fireIntEvent("Veriler SAP'ye yazıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT020 aktarım hatası: " + ex.toString());
            return;            
        }              
        
        // ENT021 Aktarımı
        try
        {
            fireIntEvent("ENT021 aktarımı başlatılıyor");
            ArrayList al = Main.axata.ENT021_READ();
            fireIntEvent("AXATA'dan ENT021 verileri çekildi");
            
            Main.sap.ZAXATA_ENT021_IMPORT(al);
            fireIntEvent("Veriler SAP'ye yazıldı");
        }
        catch(Exception ex)
        {
            fireIntEvent("ENT021 aktarım hatası: " + ex.toString());
            return;            
        }           
        
        // Bağlantıları kapat
        try
        {
            Main.sap.disconnect();
            Main.axata.disconnect();
            fireIntEvent("Bağlantılar kapatıldı");
            fireIntEvent("Entegrasyon tamamlandı");
        }
        catch(Exception ex)
        {
            fireIntEvent("Bağlantı kapama hatası:" + ex.toString());
            return;
        }
        
        // Gerekiyorsa programdan çık
        if (Main.config.intExitAfterComplete) 
        {
            fireIntEvent("Programdan çıkılıyor");
            Main.exitProgram();
        }
    }
    
    /////////////////////////////////
    // EVENTS
    /////////////////////////////////
    
    public void addIntEventListener(IntEventListener Listener) 
    {
        listenerList.add(IntEventListener.class, Listener);
    }

    private void fireIntEvent(IntEvent Evt)
    {
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i+=2) {
            if (listeners[i] == IntEventListener.class) {
                ((IntEventListener)listeners[i+1]).intEventOccured(Evt);
            }
        }
    }    
    
    private void fireIntEvent(String S)
    {
        IntEvent ie = new IntEvent(this);
        ie.text = S;
        fireIntEvent(ie);        
    }    
    
}

