/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package axe.sap;

/**
 *
 * @author Kerem
 */

import axe.*;
import java.io.*;
import com.sap.mw.jco.*;  
import java.util.*;

public class Sap {

    private JCO.Client client;
    private IRepository repository;      
    
    public Sap()
    {
    }
    
    public void connect()
    {
        client = JCO.createClient
                (Main.config.sapClient,
                Main.config.sapUser,
                Main.config.sapPass,
                Main.config.sapLang,
                Main.config.sapIP,
                Main.config.sapSystem
                );

        client.connect(); 
        repository = JCO.createRepository("rep", client);
    }    
    
    public void disconnect()
    {
        client.disconnect();
    }    
     
/* ENT004 entegrasyonu AXATA Malzeme yarat */        
    public ArrayList ZAXATA_ENT004_EXPORT()
    {
        ArrayList ret = new ArrayList();
        boolean b = true;
        
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT004_EXPORT");
        JCO.Function function = ftemp.getFunction();     
        
        client.execute(function);
        JCO.Table t = function.getTableParameterList().getTable("E_DATA");   
        
        if (t.getNumRows() > 0)
        {
            t.firstRow();
            while (b)
            {
                ZPP_ENT004_S_EXPORT at = new ZPP_ENT004_S_EXPORT();
                for (JCO.FieldIterator e = t.fields(); e.hasMoreElements();)
                {
                    JCO.Field field = e.nextField();
                    if (field.getName().equals("S04SKOD")) at.S04SKOD = field.getString();
                    if (field.getName().equals("S04MKOD")) at.S04MKOD = field.getString();
                    if (field.getName().equals("S04KTAN")) at.S04KTAN = field.getString();
                    if (field.getName().equals("S04UTAN")) at.S04UTAN = field.getString();
                    if (field.getName().equals("S04SNFK")) at.S04SNFK = field.getString();
                    if (field.getName().equals("S04MGRP")) at.S04MGRP = field.getString();
                    if (field.getName().equals("S04MKBR")) at.S04MKBR = field.getString();
                    if (field.getName().equals("S04MBBR")) at.S04MBBR = field.getString();
                    if (field.getName().equals("S04BKOR")) at.S04BKOR = field.getString();
                    if (field.getName().equals("S04PSTAN")) at.S04PSTAN = field.getString();
                    if (field.getName().equals("S04AGRL")) at.S04AGRL = field.getString();
                    if (field.getName().equals("S04NETA")) at.S04NETA = field.getString();
                    if (field.getName().equals("S04EN")) at.S04EN = field.getString();
                    if (field.getName().equals("S04BOY")) at.S04BOY = field.getString();
                    if (field.getName().equals("S04YUK")) at.S04YUK = field.getString();
                    if (field.getName().equals("S04PUAN")) at.S04PUAN = field.getString();
                    if (field.getName().equals("S04ITAR")) at.S04ITAR = field.getString();
                    if (field.getName().equals("S04IZMN")) at.S04IZMN = field.getString();
                    if (field.getName().equals("S04HTIP")) at.S04HTIP = field.getString();
                    if (field.getName().equals("S04HNUM")) at.S04HNUM = field.getString();
                    if (field.getName().equals("S04ROMUR")) at.S04ROMUR = field.getString();
                    if (field.getName().equals("S04SKTAR")) at.S04SKTAR = field.getString();
                    if (field.getName().equals("S04KKONT")) at.S04KKONT = field.getString();
                    if (field.getName().equals("S04LOT")) at.S04LOT = field.getString();
                    if (field.getName().equals("S04PALTIP")) at.S04PALTIP = field.getString();
                    if (field.getName().equals("S04KULBRM")) at.S04KULBRM = field.getString();
                    if (field.getName().equals("S04GTKON")) at.S04GTKON = field.getString();
                    if (field.getName().equals("S04SERI")) at.S04SERI = field.getString();
                    if (field.getName().equals("S04DSIM")) at.S04DSIM = field.getString();
                    if (field.getName().equals("S04FIFO")) at.S04FIFO = field.getString();
                    if (field.getName().equals("S04FOFF")) at.S04FOFF = field.getString();
                    if (field.getName().equals("S04FOFFE")) at.S04FOFFE = field.getString();
                    if (field.getName().equals("S04DBSUR")) at.S04DBSUR = field.getString();
                    if (field.getName().equals("S04KARAN")) at.S04KARAN = field.getString();
                    if (field.getName().equals("S04IKOD")) at.S04IKOD = field.getString();
                    if (field.getName().equals("S04SERITIP")) at.S04SERITIP = field.getString();
                }

                ret.add(at);
                b = t.nextRow();
            }    
        }
        
        return ret;        
    }        
    public void ZAXATA_ENT004_RETURN(ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT004_RETURN");
        JCO.Function function = ftemp.getFunction();
        
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT004_S_EXPORT p = (ZPP_ENT004_S_EXPORT) A.get(n);
            t.appendRow();
            t.setValue(p.S04MKOD, "MATNR");           
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }   
    
/* ENT000 entegrasyonu AXATA Sevkiyat */        
    public ArrayList ZAXATA_ENT000_EXPORT()
    {
        ArrayList ret = new ArrayList();
        boolean b = true;
        
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT000_EXPORT");
        JCO.Function function = ftemp.getFunction();     
        
        client.execute(function);
        JCO.Table t = function.getTableParameterList().getTable("E_DATA");   
        
        if (t.getNumRows() > 0)
        {
            t.firstRow();
            while (b)
            {
                ZPP_ENT000_S_EXPORT at = new ZPP_ENT000_S_EXPORT();
                for (JCO.FieldIterator e = t.fields(); e.hasMoreElements();)
                {
                    JCO.Field field = e.nextField();
                    if (field.getName().equals("VBELN")) at.VBELN = field.getString();
                    if (field.getName().equals("ERDAT")) at.ERDAT = field.getString();                  
                    if (field.getName().equals("S00TESN")) at.S00TESN = field.getString();
                    if (field.getName().equals("S00SIPN")) at.S00SIPN = field.getString();
                    if (field.getName().equals("S00KTUR")) at.S00KTUR = field.getString();
                    if (field.getName().equals("S00SORG")) at.S00SORG = field.getString();
                    if (field.getName().equals("S00DKAN")) at.S00DKAN = field.getString();
                    if (field.getName().equals("S00TEST")) at.S00TEST = field.getString();
                    if (field.getName().equals("S00TESS")) at.S00TESS = field.getString();
                    if (field.getName().equals("S00SMUS")) at.S00SMUS = field.getString();
                    if (field.getName().equals("S00TMUS")) at.S00TMUS = field.getString();
                    if (field.getName().equals("S00TSEK")) at.S00TSEK = field.getString();
                    if (field.getName().equals("S00NKOD")) at.S00NKOD = field.getString();
                    if (field.getName().equals("S00FDRM")) at.S00FDRM = field.getString();
                    if (field.getName().equals("S00FBLK")) at.S00FBLK = field.getString();
                    if (field.getName().equals("S00SUSR")) at.S00SUSR = field.getString();
                    if (field.getName().equals("S00MSIP")) at.S00MSIP = field.getString();
                    if (field.getName().equals("S00SNOT")) at.S00SNOT = field.getString();
                    if (field.getName().equals("S00INT1")) at.S00INT1 = field.getString();
                    if (field.getName().equals("S00INT2")) at.S00INT2 = field.getString();
                    if (field.getName().equals("S00ACIN")) at.S00ACIN = field.getString();
                    if (field.getName().equals("S00ITAR")) at.S00ITAR = field.getString();
                    if (field.getName().equals("S00IZMN")) at.S00IZMN = field.getString();
                    if (field.getName().equals("S00IDOC")) at.S00IDOC = field.getString();
                    if (field.getName().equals("S00YUKN")) at.S00YUKN = field.getString();
                    if (field.getName().equals("S00SKOD")) at.S00SKOD = field.getString();
                    if (field.getName().equals("S00HTP1")) at.S00HTP1 = field.getString();
                    if (field.getName().equals("S00HTP2")) at.S00HTP2 = field.getString();
                    if (field.getName().equals("S00CDAT")) at.S00CDAT = field.getString();

                }

                ret.add(at);
                b = t.nextRow();
            }    
        }
        
        return ret;        
    }        
    public void ZAXATA_ENT000_RETURN(ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT000_RETURN");
        JCO.Function function = ftemp.getFunction();
        
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT000_S_EXPORT p = (ZPP_ENT000_S_EXPORT) A.get(n);
            t.appendRow();
            t.setValue(p.VBELN, "VBELN");
            t.setValue(p.ERDAT, "ERDAT");            
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }   
    
/* ENT001 entegrasyonu AXATA Sevkiyat Kalem */        
    public ArrayList ZAXATA_ENT001_EXPORT()
    {
        ArrayList ret = new ArrayList();
        boolean b = true;
        
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT001_EXPORT");
        JCO.Function function = ftemp.getFunction();     
        
        client.execute(function);
        JCO.Table t = function.getTableParameterList().getTable("E_DATA");   
        
        if (t.getNumRows() > 0)
        {
            t.firstRow();
            while (b)
            {
                ZPP_ENT001_S_EXPORT at = new ZPP_ENT001_S_EXPORT();
                for (JCO.FieldIterator e = t.fields(); e.hasMoreElements();)
                {
                    JCO.Field field = e.nextField();
                    if (field.getName().equals("VBELN")) at.VBELN = field.getString();
                    if (field.getName().equals("POSNR")) at.POSNR = field.getString();  
                    if (field.getName().equals("ERDAT")) at.ERDAT = field.getString();
                    if (field.getName().equals("S01SKOD")) at.S01SKOD = field.getString();
                    if (field.getName().equals("S01TESL")) at.S01TESL = field.getString();
                    if (field.getName().equals("S01KALN")) at.S01KALN = field.getString();
                    if (field.getName().equals("S01UKAL")) at.S01UKAL = field.getString();
                    if (field.getName().equals("S01FRDP")) at.S01FRDP = field.getString();
                    if (field.getName().equals("S01SKU")) at.S01SKU = field.getString();
                    if (field.getName().equals("S01LOTN")) at.S01LOTN = field.getString();
                    if (field.getName().equals("S01MIKT")) at.S01MIKT = field.getString();
                    if (field.getName().equals("S01SIM")) at.S01SIM = field.getString();
                    if (field.getName().equals("S01TMIK")) at.S01TMIK = field.getString();
                    if (field.getName().equals("S01MURU")) at.S01MURU = field.getString();
                    if (field.getName().equals("S01MSTR")) at.S01MSTR = field.getString();
                    if (field.getName().equals("S01HKOD")) at.S01HKOD = field.getString();
                    if (field.getName().equals("S01ITIP")) at.S01ITIP = field.getString();
                    if (field.getName().equals("S01SARG")) at.S01SARG = field.getString();
                    if (field.getName().equals("S01ITAR")) at.S01ITAR = field.getString();
                    if (field.getName().equals("S01IZMN")) at.S01IZMN = field.getString();
                    if (field.getName().equals("S01IDOC")) at.S01IDOC = field.getString();
                    if (field.getName().equals("S01PRICE")) at.S01PRICE = field.getString();
                    if (field.getName().equals("S01CDAT")) at.S01CDAT = field.getString();

                }

                ret.add(at);
                b = t.nextRow();
            }    
        }
        
        return ret;        
    }        
    public void ZAXATA_ENT001_RETURN(ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT001_RETURN");
        JCO.Function function = ftemp.getFunction();
        
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT001_S_EXPORT p = (ZPP_ENT001_S_EXPORT) A.get(n);
            t.appendRow();
            t.setValue(p.VBELN, "VBELN");
            t.setValue(p.POSNR, "POSNR");
            t.setValue(p.ERDAT, "ERDAT");            
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }       
    

/* ENT019 entegrasyonu AXATA Üretim Girişi */        
    public ArrayList ZAXATA_ENT019_EXPORT()
    {
        ArrayList ret = new ArrayList();
        boolean b = true;
        
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT019_EXPORT");
        JCO.Function function = ftemp.getFunction();     
        
        client.execute(function);
        JCO.Table t = function.getTableParameterList().getTable("E_DATA");   
        
        if (t.getNumRows() > 0)
        {
            t.firstRow();
            while (b)
            {
                ZPP_ENT019_S_EXPORT at = new ZPP_ENT019_S_EXPORT();
                for (JCO.FieldIterator e = t.fields(); e.hasMoreElements();)
                {
                    JCO.Field field = e.nextField();
                    if (field.getName().equals("BUKRS")) at.BUKRS = field.getString();
                    if (field.getName().equals("WERKS")) at.WERKS = field.getString();
                    if (field.getName().equals("BELNO")) at.BELNO = field.getString();
                    if (field.getName().equals("MJAHR")) at.MJAHR = field.getString();
                    if (field.getName().equals("POSNR")) at.POSNR = field.getString();
                    if (field.getName().equals("PLTNO")) at.PLTNO = field.getString();                    
                    if (field.getName().equals("S19PALN")) at.S19PALN = field.getString();
                    if (field.getName().equals("S19PYED")) at.S19PYED = field.getString();
                    if (field.getName().equals("S19SKU")) at.S19SKU = field.getString();
                    if (field.getName().equals("S19LOT")) at.S19LOT = field.getString();
                    if (field.getName().equals("S19HTIP")) at.S19HTIP = field.getString();
                    if (field.getName().equals("S19MAKN")) at.S19MAKN = field.getString();
                    if (field.getName().equals("S19TESS")) at.S19TESS = field.getString();
                    if (field.getName().equals("S19UYER")) at.S19UYER = field.getString();
                    if (field.getName().equals("S19PTIP")) at.S19PTIP = field.getString();
                    if (field.getName().equals("S19SKOD")) at.S19SKOD = field.getString();
                    if (field.getName().equals("S19AKOD")) at.S19AKOD = field.getString();
                    if (field.getName().equals("S19UTAR")) at.S19UTAR = field.getString();
                    if (field.getName().equals("S19UZMN")) at.S19UZMN = field.getString();
                    if (field.getName().equals("S19PTAR")) at.S19PTAR = field.getString();
                    if (field.getName().equals("S19PZMN")) at.S19PZMN = field.getString();
                    if (field.getName().equals("S19MIK")) at.S19MIK = field.getString();
                    if (field.getName().equals("S19SIM")) at.S19SIM = field.getString();
                    if (field.getName().equals("S19IPTK")) at.S19IPTK = field.getString();
                    if (field.getName().equals("S19ITAR")) at.S19ITAR = field.getString();
                    if (field.getName().equals("S19IZMN")) at.S19IZMN = field.getString();

                }

                ret.add(at);
                b = t.nextRow();
            }    
        }
        
        return ret;        
    }        
    public void ZAXATA_ENT019_RETURN(ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT019_RETURN");
        JCO.Function function = ftemp.getFunction();
        
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT019_S_EXPORT p = (ZPP_ENT019_S_EXPORT) A.get(n);
            t.appendRow();
            t.setValue(p.BUKRS, "BUKRS");
            t.setValue(p.WERKS, "WERKS");
            t.setValue(p.BELNO, "BELNO");
            t.setValue(p.MJAHR, "MJAHR");
            t.setValue(p.POSNR, "POSNR");
            t.setValue(p.PLTNO, "PLTNO");            
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }   
    
   
/* ENT013 entegrasyonu */      
    public ArrayList ZAXATA_ENT013_EXPORT()
    {
        ArrayList ret = new ArrayList();
        boolean b = true;
        
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT013_EXPORT");
        JCO.Function function = ftemp.getFunction();     
        
        client.execute(function);
        JCO.Table t = function.getTableParameterList().getTable("E_DATA");   
        
        if (t.getNumRows() > 0)
        {
            t.firstRow();
            while (b)
            {
                ZPP_ENT013_S_EXPORT at = new ZPP_ENT013_S_EXPORT();
                for (JCO.FieldIterator e = t.fields(); e.hasMoreElements();)
                {
                    JCO.Field field = e.nextField();
                    if (field.getName().equals("BUKRS")) at.BUKRS = field.getString();
                    if (field.getName().equals("WERKS")) at.WERKS = field.getString();
                    if (field.getName().equals("BELNO")) at.BELNO = field.getString();
                    if (field.getName().equals("POSNR")) at.POSNR = field.getString();
                    if (field.getName().equals("VBELN")) at.VBELN = field.getString();
                    if (field.getName().equals("STATU")) at.STATU = field.getString();
                    if (field.getName().equals("S13STAT")) at.S13STAT = field.getString();
                    if (field.getName().equals("S13SKOD")) at.S13SKOD = field.getString();
                    if (field.getName().equals("S13HKOD")) at.S13HKOD = field.getString();
                    if (field.getName().equals("S13BNUM")) at.S13BNUM = field.getString();
                    if (field.getName().equals("S13KALN")) at.S13KALN = field.getString();
                    if (field.getName().equals("S13AKOD")) at.S13AKOD = field.getString();
                    if (field.getName().equals("S13SIPT")) at.S13SIPT = field.getString();
                    if (field.getName().equals("S13SKU")) at.S13SKU = field.getString();
                    if (field.getName().equals("S13LOT")) at.S13LOT = field.getString();
                    if (field.getName().equals("S13FIRM")) at.S13FIRM = field.getString();
                    if (field.getName().equals("S13MIKT")) at.S13MIKT = field.getString();
                    if (field.getName().equals("S13SIM")) at.S13SIM = field.getString();
                    if (field.getName().equals("S13TMIK")) at.S13TMIK = field.getString();
                    if (field.getName().equals("S13KTIP")) at.S13KTIP = field.getString();
                    if (field.getName().equals("S13ITAR")) at.S13ITAR = field.getString();
                    if (field.getName().equals("S13IZMN")) at.S13IZMN = field.getString();
                    if (field.getName().equals("S13TMTR")) at.S13TMTR = field.getString();
                    if (field.getName().equals("S13TMZM")) at.S13TMZM = field.getString();
                    if (field.getName().equals("S13UYER")) at.S13UYER = field.getString();
                    if (field.getName().equals("S13TESS")) at.S13TESS = field.getString();
                    if (field.getName().equals("S13MAKK")) at.S13MAKK = field.getString();
                }

                ret.add(at);
                b = t.nextRow();
            }    
        }
        
        return ret;        
    }    
    public void ZAXATA_ENT013_RETURN(ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT013_RETURN");
        JCO.Function function = ftemp.getFunction();
        
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT013_S_EXPORT p = (ZPP_ENT013_S_EXPORT) A.get(n);
            t.appendRow();
            t.setValue(p.BUKRS, "BUKRS");
            t.setValue(p.WERKS, "WERKS");
            t.setValue(p.BELNO, "BELNO");
            t.setValue(p.POSNR, "POSNR");
            t.setValue(p.VBELN, "VBELN");
            t.setValue(p.STATU, "STATU");
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }      
    
    
    public void ZAXATA_ENT005_IMPORT(ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT005_IMPORT");
        JCO.Function function = ftemp.getFunction();
        
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT005_S_IMPORT p = (ZPP_ENT005_S_IMPORT) A.get(n);
            t.appendRow();
            t.setValue(p.S05STAT, "S05STAT");
            t.setValue(p.S05PALN, "S05PALN");
            t.setValue(p.S05YPAL, "S05YPAL");
            t.setValue(p.S05MALK, "S05MALK");
            t.setValue(p.S05LOTN, "S05LOTN");
            t.setValue(p.S05HKOD, "S05HKOD");
            t.setValue(p.S05EHKD, "S05EHKD");
            t.setValue(p.S05FISYIL, "S05FISYIL");
            t.setValue(p.S05FISN, "S05FISN");
            t.setValue(p.S05UTAR, "S05UTAR");
            t.setValue(p.S05UZMN, "S05UZMN");
            t.setValue(p.S05VARD, "S05VARD");
            t.setValue(p.S05MIKT, "S05MIKT");
            t.setValue(p.S05SIM, "S05SIM");
            t.setValue(p.S05TMIK, "S05TMIK");
            t.setValue(p.S05BELN, "S05BELN");
            t.setValue(p.S05BELT, "S05BELT");
            t.setValue(p.S05SIPN, "S05SIPN");
            t.setValue(p.S05MUSK, "S05MUSK");
            t.setValue(p.S05MKOD, "S05MKOD");
            t.setValue(p.S05TKOD, "S05TKOD");
            t.setValue(p.S05UYER, "S05UYER");
            t.setValue(p.S05UNO, "S05UNO");
            t.setValue(p.S05KALN, "S05KALN");
            t.setValue(p.S05PERS, "S05PERS");
            t.setValue(p.S05SKOD, "S05SKOD");
            t.setValue(p.S05DEPO, "S05DEPO");
            t.setValue(p.S05ITAR, "S05ITAR");
            t.setValue(p.S05IZMN, "S05IZMN");
            t.setValue(p.S05TMTR, "S05TMTR");
            t.setValue(p.S05TMZM, "S05TMZM");
            t.setValue(p.S05SIRA, "S05SIRA");
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }        
    public void ZAXATA_ENT006_IMPORT(ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT006_IMPORT");
        JCO.Function function = ftemp.getFunction();
        
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT006_S_IMPORT p = (ZPP_ENT006_S_IMPORT) A.get(n);
            t.appendRow();           
            t.setValue(p.S06SKOD, "S06SKOD");
            t.setValue(p.S06TESL, "S06TESL");
            t.setValue(p.S06STAT, "S06STAT");
            t.setValue(p.S06HKOD, "S06HKOD");
            t.setValue(p.S06HTIP, "S06HTIP");
            t.setValue(p.S06INUM, "S06INUM");
            t.setValue(p.S06STTU, "S06STTU");
            t.setValue(p.S06KAMN, "S06KAMN");
            t.setValue(p.S06REFN, "S06REFN");
            t.setValue(p.S06IRST, "S06IRST");
            t.setValue(p.S06IRSZ, "S06IRSZ");
            t.setValue(p.S06SSIP, "S06SSIP");
            t.setValue(p.S06GBEK, "S06GBEK");
            t.setValue(p.S06PLKA, "S06PLKA");
            t.setValue(p.S06KONT, "S06KONT");
            t.setValue(p.S06KAMT, "S06KAMT");
            t.setValue(p.S06ACIN, "S06ACIN");
            t.setValue(p.S06FSTR, "S06FSTR");
            t.setValue(p.S06RPRT, "S06RPRT");
            t.setValue(p.S06YUKN, "S06YUKN");
            t.setValue(p.S06ITAR, "S06ITAR");
            t.setValue(p.S06IZMN, "S06IZMN");
            t.setValue(p.S06TMTR, "S06TMTR");
            t.setValue(p.S06TMZM, "S06TMZM");
            t.setValue(p.S06DEPO, "S06DEPO");
            t.setValue(p.S06RSIP, "S06RSIP");
            t.setValue(p.S06FIRM, "S06FIRM");
            t.setValue(p.S06TFIR, "S06TFIR");
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }        
    public void ZAXATA_ENT007_IMPORT(ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT007_IMPORT");
        JCO.Function function = ftemp.getFunction();
        
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT007_S_IMPORT p = (ZPP_ENT007_S_IMPORT) A.get(n);
            t.appendRow();
            t.setValue(p.S07SIRK, "S07SIRK");
            t.setValue(p.S07TESL, "S07TESL");
            t.setValue(p.S07KALN, "S07KALN");
            t.setValue(p.S07STAT, "S07STAT");
            t.setValue(p.S07HKOD, "S07HKOD");
            t.setValue(p.S07SKOD, "S07SKOD");
            t.setValue(p.S07LOTN, "S07LOTN");
            t.setValue(p.S07MIKT, "S07MIKT");
            t.setValue(p.S07SIMM, "S07SIMM");
            t.setValue(p.S07TOPM, "S07TOPM");
            t.setValue(p.S07STTU, "S07STTU");
            t.setValue(p.S07YUKN, "S07YUKN");
            t.setValue(p.S07ITAR, "S07ITAR");
            t.setValue(p.S07IZMN, "S07IZMN");
            t.setValue(p.S07TMTR, "S07TMTR");
            t.setValue(p.S07TMZM, "S07TMZM");
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }        
    public void ZAXATA_ENT008_IMPORT(ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT008_IMPORT");
        JCO.Function function = ftemp.getFunction();
        
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT008_S_IMPORT p = (ZPP_ENT008_S_IMPORT) A.get(n);
            t.appendRow();
            t.setValue(p.S08STAT, "S08STAT");
            t.setValue(p.S08SKOD, "S08SKOD");
            t.setValue(p.S08AKOD, "S08AKOD");
            t.setValue(p.S08SKUK, "S08SKUK");
            t.setValue(p.S08LOTN, "S08LOTN");
            t.setValue(p.S08BNED, "S08BNED");
            t.setValue(p.S08MIKT, "S08MIKT");
            t.setValue(p.S08SIM, "S08SIM");
            t.setValue(p.S08TMIK, "S08TMIK");
            t.setValue(p.S08STTU, "S08STTU");
            t.setValue(p.S08PALN, "S08PALN");
            t.setValue(p.S08ITAR, "S08ITAR");
            t.setValue(p.S08IZMN, "S08IZMN");
            t.setValue(p.S08TMTR, "S08TMTR");
            t.setValue(p.S08TMZM, "S08TMZM");
            t.setValue(p.S08SIPN, "S08SIPN");
            t.setValue(p.S08SIRA, "S08SIRA");
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }     
    public void ZAXATA_ENT011_IMPORT(ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT011_IMPORT");
        JCO.Function function = ftemp.getFunction();
        
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT011_S_IMPORT p = (ZPP_ENT011_S_IMPORT) A.get(n);
            t.appendRow();
            t.setValue(p.S11STAT, "S11STAT");
            t.setValue(p.S11SIRK, "S11SIRK");
            t.setValue(p.S11DEPO, "S11DEPO");
            t.setValue(p.S11HKOD, "S11HKOD");
            t.setValue(p.S11HTIP, "S11HTIP");
            t.setValue(p.S11STIP, "S11STIP");
            t.setValue(p.S11HKD1, "S11HKD1");
            t.setValue(p.S11HKD2, "S11HKD2");
            t.setValue(p.S11PALN, "S11PALN");
            t.setValue(p.S11MALK, "S11MALK");
            t.setValue(p.S11LOTN, "S11LOTN");
            t.setValue(p.S11MIKT, "S11MIKT");
            t.setValue(p.S11SIM, "S11SIM");
            t.setValue(p.S11TMIK, "S11TMIK");
            t.setValue(p.S11UNO, "S11UNO");
            t.setValue(p.S11MAKN, "S11MAKN");
            t.setValue(p.S11TESS, "S11TESS");
            t.setValue(p.S11UYER, "S11UYER");
            t.setValue(p.S11UTAR, "S11UTAR");
            t.setValue(p.S11USAA, "S11USAA");
            t.setValue(p.S11ITAR, "S11ITAR");
            t.setValue(p.S11IZMN, "S11IZMN");
            t.setValue(p.S11TMTR, "S11TMTR");
            t.setValue(p.S11TMZM, "S11TMZM");
            t.setValue(p.S11PERS, "S11PERS");
            t.setValue(p.S11SIRA, "S11SIRA");
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }      
    
    public ZPP_ENT020_S_PARAM ZAXATA_ENT020_GET_DATE()
    {
        ZPP_ENT020_S_PARAM ret = new ZPP_ENT020_S_PARAM();
        
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT020_GET_DATE");
        JCO.Function function = ftemp.getFunction();     
        
        client.execute(function);
        
        ret.DATUM = function.getExportParameterList().getString("E_DATUM");
        ret.ENTID = function.getExportParameterList().getString("E_ENTID");
        
        return ret;
    }
    
    public void ZAXATA_ENT020_IMPORT(ZPP_ENT020_S_PARAM P, ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT020_IMPORT");
        JCO.Function function = ftemp.getFunction();
        
        // Parameters
        function.getImportParameterList().setValue(P.ENTID, "I_ENTID");
        
        String s = P.DATUM.substring(0, 4) + P.DATUM.substring(5, 7) + P.DATUM.substring(8, 10);
        function.getImportParameterList().setValue(s, "I_DATUM");
        
        // Table
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT020_S_IMPORT p = (ZPP_ENT020_S_IMPORT) A.get(n);
            t.appendRow();
            t.setValue(p.S20DEPO, "S20DEPO");
            t.setValue(p.S20PALN, "S20PALN");
            t.setValue(p.S20SKUK, "S20SKUK");
            t.setValue(p.S20SNFK, "S20SNFK");
            t.setValue(p.S20SKUT, "S20SKUT");
            t.setValue(p.S20LOTN, "S20LOTN");
            t.setValue(p.S20CKST, "S20CKST");
            t.setValue(p.S20SVKY, "S20SVKY");
            t.setValue(p.S20SVKN, "S20SVKN");
            t.setValue(p.S20MIKT, "S20MIKT");
            t.setValue(p.S20BRM, "S20BRM");
            t.setValue(p.S20SPVK, "S20SPVK");
            t.setValue(p.S20SPVR, "S20SPVR");
            t.setValue(p.S20TSLK, "S20TSLK");
            t.setValue(p.S20TSLA, "S20TSLA");
            t.setValue(p.S20UTAR, "S20UTAR");
            t.setValue(p.S20CTAR, "S20CTAR");
            t.setValue(p.S20STAR, "S20STAR");
            t.setValue(p.S20TESS, "S20TESS");
            t.setValue(p.S20MAKN, "S20MAKN");
            t.setValue(p.S20UYER, "S20UYER");
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }    
    
    public void ZAXATA_ENT021_IMPORT(ArrayList A)
    {
        IFunctionTemplate ftemp = repository.getFunctionTemplate("ZAXATA_ENT021_IMPORT");
        JCO.Function function = ftemp.getFunction();
        
        JCO.ParameterList pl = function.getTableParameterList();
        JCO.Table t = pl.getTable("I_DATA");
        
        for (int n = 0; n < A.size(); n++)
        {
            ZPP_ENT021_S_IMPORT p = (ZPP_ENT021_S_IMPORT) A.get(n);
            t.appendRow();
            t.setValue(p.S21DEPO, "S21DEPO");
            t.setValue(p.S21PALN, "S21PALN");
            t.setValue(p.S21SKUK, "S21SKUK");
            t.setValue(p.S21SNFK, "S21SNFK");
            t.setValue(p.S21SKUT, "S21SKUT");
            t.setValue(p.S21LOTN, "S21LOTN");
            t.setValue(p.S21PLTS, "S21PLTS");
            t.setValue(p.S21SIPN, "S21SIPN");
            t.setValue(p.S21UTAR, "S21UTAR");
            t.setValue(p.S21STAR, "S21STAR");
            t.setValue(p.S21MIKT, "S21MIKT");
            t.setValue(p.S21BRM, "S21BRM");
            t.setValue(p.S21ADRS, "S21ADRS");
            t.setValue(p.S21OZLB, "S21OZLB");
            t.setValue(p.S21BLKN, "S21BLKN");
            t.setValue(p.S21BTAR, "S21BTAR");
            t.setValue(p.S21TESS, "S21TESS");
            t.setValue(p.S21MKNK, "S21MKNK");
            t.setValue(p.S21UYER, "S21UYER");
            t.setValue(p.S21PLTT, "S21PLTT");
            t.setValue(p.S21STKT, "S21STKT");
        }
        
        pl.setValue(t, "I_DATA");
        function.setTableParameterList(pl);
        
        client.execute(function);        
    }     
    
    
}
