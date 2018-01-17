/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package axe.axata;

/**
 *
 * @author Kerem
 */

import axe.*;
import com.microsoft.sqlserver.jdbc.*;
import java.sql.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Axata {

    private Connection conn;
    public enum DATE_FORMAT {ENGLISH, TURKISH};
    
    public Axata() throws Exception {

        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch(Exception ex)
        {
            throw new Exception("JDBC kütüphane hatası: " + ex.toString());
        }
        
    }    
    
    public static String sqlFormatDate(String SapDate)
    {
        // 2009-11-30 -> 30.11.2009 
        return SapDate.substring(8, 10) + "." + SapDate.substring(5, 7) + "." + SapDate.substring(0, 4);
    }
    
    public static String sqlFormatDate(Calendar C, DATE_FORMAT D, boolean IncludeTime)
    {
        String ret = "";
        
        if (D == DATE_FORMAT.ENGLISH)
        {
            ret += String.valueOf(C.get(Calendar.MONTH) + 1) + "/" + String.valueOf(C.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(C.get(Calendar.YEAR));
            if (IncludeTime)
            {
                ret += " ";
                ret += String.valueOf(C.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(C.get(Calendar.MINUTE)) + ":"+ String.valueOf(C.get(Calendar.SECOND));
            }
        }
        else
        {
            ret += String.valueOf(C.get(Calendar.DAY_OF_MONTH)) + "." + String.valueOf(C.get(Calendar.MONTH) + 1) + "." +  String.valueOf(C.get(Calendar.YEAR));
            if (IncludeTime)
            {
                ret += " ";
                ret += String.valueOf(C.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(C.get(Calendar.MINUTE)) + ":"+ String.valueOf(C.get(Calendar.SECOND));
            }
        }
        
        return ret;
    }
    
    public static String sqlFormatText(String S)
    {
        return sqlFormatText(S, 0);
    }

    public static String sqlFormatText(String S, int MaxLength)
    {
        if (S == null) return "";
        String ret = S.replaceAll("'", "''");
        
        if ((MaxLength != 0) && (MaxLength < ret.length())) ret = ret.substring(0, MaxLength);
        
        return ret;
    }    
    
    public void connect() throws Exception
    {
        try
        {
            String url="jdbc:sqlserver://" + Main.config.sqlIP + ";DatabaseName=" + Main.config.sqlDatabase;
            //String url = "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;DatabaseName=askandb";
            conn = DriverManager.getConnection(url, Main.config.sqlUser, Main.config.sqlPass);
            if (conn == null)
            {
                throw new Exception("JDBC bağlantısı açılamadı, ayar dosyasını kontrol edin");
            }
            
            executeQuery("set dateformat dmy");
        }
        catch(Exception ex)
        {
             throw new Exception("JDBC bağlantı hatası: " + ex.toString());
        }        
    }
    
    public void disconnect()
    {
        try
        {
            if (conn != null) conn.close();
        }
        catch(Exception ex)
        {
            
        }
    }
    
    public ResultSet selectData(String Query) throws Exception
    {
        ResultSet ret = null;
        
        Statement s = conn.createStatement();
        s.executeQuery(Query);
        ret = s.getResultSet();
        
        return ret;
    }
    
    public void executeQuery(String Query) throws Exception
    {
        Statement s = conn.createStatement();
        s.execute(Query);
    }    
    
    public void startTransaction() throws Exception
    {
        conn.setAutoCommit(false);
    }
    
    public void endTransaction() throws Exception
    {
        conn.commit();
        conn.setAutoCommit(true);
    }
    
    public void cancelTransaction() 
    {
        try
        {
            conn.rollback();
            conn.setAutoCommit(true);
        }
        catch(Exception ex)
        {
            Main.appendLog("Rollback hatası: " + ex.toString());
        }
    }
    
    public static String getString(ResultSet R, String ColumnName)
    {
        try
        {
            return (R.getString(ColumnName) == null ? "" : R.getString(ColumnName));
        }
        catch(Exception ex)
        {
            return "";
        }
    }
    
    public static boolean getBoolean(ResultSet R, String ColumnName)
    {
        return getString(R, ColumnName) != null && Axata.getString(R, ColumnName).equals("X");     
    } 
    
    public static Calendar getCalendar(ResultSet R, String ColumnName) throws Exception
    {
        Calendar ret = Calendar.getInstance();
        String s = R.getString(ColumnName);
        
        if (s != null)
        {
            ret.clear();
            
            int hour = Integer.parseInt(s.substring(11, 13));
            
            ret.set(
                    Integer.parseInt(s.substring(0, 4)), 
                    Integer.parseInt(s.substring(5, 7)), 
                    Integer.parseInt(s.substring(8, 10)), 
                    hour, 
                    Integer.parseInt(s.substring(14, 16)), 
                    Integer.parseInt(s.substring(17, 19))
                    );
        }
        else ret = null;
        return ret;
    }
    
    public void ENT004_WRITE(ArrayList A) throws Exception
    {
        Statement st = conn.createStatement();

        for (int n = 0; n < A.size(); n++)
        {
            // Get material line
            axe.sap.ZPP_ENT004_S_EXPORT at = (axe.sap.ZPP_ENT004_S_EXPORT) A.get(n);
            
            // Delete old material line
            //st.execute("delete from ENT004 where S04MKOD = '" + at.S04MKOD + "'");
            
            // Insert new material line
            String command = "insert into ENT004(S04SKOD, S04MKOD, S04KTAN, S04UTAN, S04SNFK, S04MGRP, S04MKBR, S04MBBR, S04BKOR, S04PSTAN, S04AGRL, S04NETA, S04EN, S04BOY, S04YUK, S04PUAN, S04ITAR, S04IZMN, S04HTIP, S04HNUM, S04ROMUR, S04SKTAR, S04KKONT, S04LOT, S04PALTIP, S04KULBRM, S04GTKON, S04SERI, S04DSIM, S04FIFO, S04FOFF, S04FOFFE, S04DBSUR, S04KARAN, S04IKOD, S04SERITIP) values (";
            command += (at.S04SKOD.length() <= 0 ? "null" : "'" + at.S04SKOD + "'") + ",";
            command += (at.S04MKOD.length() <= 0 ? "null" : "'" + at.S04MKOD + "'") + ",";
            command += (at.S04KTAN.length() <= 0 ? "null" : "'" + at.S04KTAN + "'") + ",";
            command += (at.S04UTAN.length() <= 0 ? "null" : "'" + at.S04UTAN + "'") + ",";
            command += (at.S04SNFK.length() <= 0 ? "null" : at.S04SNFK) + ",";
            command += (at.S04MGRP.length() <= 0 ? "null" : at.S04MGRP) + ",";
            command += (at.S04MKBR.length() <= 0 ? "null" : at.S04MKBR) + ",";
            command += (at.S04MBBR.length() <= 0 ? "null" : at.S04MBBR) + ",";
            command += (at.S04BKOR.length() <= 0 ? "null" : at.S04BKOR) + ",";
            command += (at.S04PSTAN.length() <= 0 ? "null" : at.S04PSTAN) + ",";
            command += (at.S04AGRL.length() <= 0 ? "null" : at.S04AGRL) + ",";
            command += (at.S04NETA.length() <= 0 ? "null" : at.S04NETA) + ",";
            command += (at.S04EN.length() <= 0 ? "null" : at.S04EN) + ",";
            command += (at.S04BOY.length() <= 0 ? "null" : at.S04BOY) + ",";
            command += (at.S04YUK.length() <= 0 ? "null" : at.S04YUK) + ",";
            command += (at.S04PUAN.length() <= 0 ? "null" : at.S04PUAN) + ",";
            command += (at.S04ITAR.length() <= 0 ? "null" : at.S04ITAR) + ",";
            command += (at.S04IZMN.length() <= 0 ? "null" : at.S04IZMN) + ",";
            command += (at.S04HTIP.length() <= 0 ? "null" : at.S04HTIP) + ",";
            command += (at.S04HNUM.length() <= 0 ? "null" : at.S04HNUM) + ",";
            command += (at.S04ROMUR.length() <= 0 ? "null" : at.S04ROMUR) + ",";
            command += (at.S04SKTAR.length() <= 0 ? "null" : at.S04SKTAR) + ",";
            command += (at.S04KKONT.length() <= 0 ? "null" : at.S04KKONT) + ",";
            command += (at.S04LOT.length() <= 0 ? "null" : at.S04LOT) + ",";
            command += (at.S04PALTIP.length() <= 0 ? "null" : at.S04PALTIP) + ",";
            command += (at.S04KULBRM.length() <= 0 ? "null" : at.S04KULBRM) + ",";
            command += (at.S04GTKON.length() <= 0 ? "null" : at.S04GTKON) + ",";
            command += (at.S04SERI.length() <= 0 ? "null" : at.S04SERI) + ",";
            command += (at.S04DSIM.length() <= 0 ? "null" : at.S04DSIM) + ",";
            command += (at.S04FIFO.length() <= 0 ? "null" : at.S04FIFO) + ",";
            command += (at.S04FOFF.length() <= 0 ? "null" : at.S04FOFF) + ",";
            command += (at.S04FOFFE.length() <= 0 ? "null" : at.S04FOFFE) + ",";
            command += (at.S04DBSUR.length() <= 0 ? "null" : at.S04DBSUR) + ",";
            command += (at.S04KARAN.length() <= 0 ? "null" : at.S04KARAN) + ",";
            command += (at.S04IKOD.length() <= 0 ? "null" : at.S04IKOD) + ",";
            command += (at.S04SERITIP.length() <= 0 ? "null" : at.S04SERITIP) + "";
            command += ")";
            st.execute(command);
        }
    }       
    public void ENT000_WRITE(ArrayList A) throws Exception
    {
        Statement st = conn.createStatement();

        for (int n = 0; n < A.size(); n++)
        {
            axe.sap.ZPP_ENT000_S_EXPORT at = (axe.sap.ZPP_ENT000_S_EXPORT) A.get(n);
            String command = "insert into ENT000(S00TESN, S00SIPN, S00KTUR, S00SORG, S00DKAN, S00TEST, S00TESS, S00SMUS, S00TMUS, S00TSEK, S00NKOD, S00FDRM, S00FBLK, S00SUSR, S00MSIP, S00SNOT, S00INT1, S00INT2, S00ACIN, S00ITAR, S00IZMN, S00IDOC, S00YUKN, S00SKOD, S00HTP1, S00HTP2, S00CDAT) values (";
            command += (at.S00TESN.length() <= 0 ? "null" : at.S00TESN) + ",";
            command += (at.S00SIPN.length() <= 0 ? "null" : at.S00SIPN) + ",";
            command += (at.S00KTUR.length() <= 0 ? "null" : "'" + at.S00KTUR + "'") + ",";
            command += (at.S00SORG.length() <= 0 ? "null" : at.S00SORG) + ",";
            command += (at.S00DKAN.length() <= 0 ? "null" : "'" + at.S00DKAN + "'") + ",";
            command += (at.S00TEST.length() <= 0 ? "null" : at.S00TEST) + ",";
            command += (at.S00TESS.length() <= 0 ? "null" : at.S00TESS) + ",";
            command += (at.S00SMUS.length() <= 0 ? "null" : at.S00SMUS) + ",";
            command += (at.S00TMUS.length() <= 0 ? "null" : at.S00TMUS) + ",";
            command += (at.S00TSEK.length() <= 0 ? "null" : at.S00TSEK) + ",";
            command += (at.S00NKOD.length() <= 0 ? "null" : at.S00NKOD) + ",";
            command += (at.S00FDRM.length() <= 0 ? "null" : at.S00FDRM) + ",";
            command += (at.S00FBLK.length() <= 0 ? "null" : at.S00FBLK) + ",";
            command += (at.S00SUSR.length() <= 0 ? "null" : at.S00SUSR) + ",";
            command += (at.S00MSIP.length() <= 0 ? "null" : at.S00MSIP) + ",";
            command += (at.S00SNOT.length() <= 0 ? "null" : at.S00SNOT) + ",";
            command += (at.S00INT1.length() <= 0 ? "null" : at.S00INT1) + ",";
            command += (at.S00INT2.length() <= 0 ? "null" : at.S00INT2) + ",";
            command += (at.S00ACIN.length() <= 0 ? "null" : at.S00ACIN) + ",";
            command += (at.S00ITAR.length() <= 0 ? "null" : at.S00ITAR) + ",";
            command += (at.S00IZMN.length() <= 0 ? "null" : at.S00IZMN) + ",";
            command += (at.S00IDOC.length() <= 0 ? "null" : at.S00IDOC) + ",";
            command += (at.S00YUKN.length() <= 0 ? "null" : at.S00YUKN) + ",";
            command += (at.S00SKOD.length() <= 0 ? "null" : "'" + at.S00SKOD + "'") + ",";
            command += (at.S00HTP1.length() <= 0 ? "null" : at.S00HTP1) + ",";
            command += (at.S00HTP2.length() <= 0 ? "null" : at.S00HTP2) + ",";
            command += (at.S00CDAT.length() <= 0 ? "null" : at.S00CDAT) + " ";
            command += ")";
            st.execute(command);
        }
    }    
    public void ENT001_WRITE(ArrayList A) throws Exception
    {
        Statement st = conn.createStatement();

        for (int n = 0; n < A.size(); n++)
        {
            axe.sap.ZPP_ENT001_S_EXPORT at = (axe.sap.ZPP_ENT001_S_EXPORT) A.get(n);
            String command = "insert into ENT001(S01SKOD, S01TESL, S01KALN, S01UKAL, S01FRDP, S01SKU, S01LOTN, S01MIKT, S01SIM, S01TMIK, S01MURU, S01MSTR, S01HKOD, S01ITIP, S01SARG, S01ITAR, S01IZMN, S01IDOC, S01PRICE, S01CDAT) values (";
            command += (at.S01SKOD.length() <= 0 ? "null" : "'" + at.S01SKOD + "'") + ",";
            command += (at.S01TESL.length() <= 0 ? "null" : at.S01TESL) + ",";
            command += (at.S01KALN.length() <= 0 ? "null" : at.S01KALN) + ",";
            command += (at.S01UKAL.length() <= 0 ? "null" : at.S01UKAL) + ",";
            command += (at.S01FRDP.length() <= 0 ? "null" : at.S01FRDP) + ",";
            command += (at.S01SKU.length() <= 0 ? "null" : "'" + at.S01SKU + "'") + ",";
            command += (at.S01LOTN.length() <= 0 ? "null" : at.S01LOTN) + ",";
            command += (at.S01MIKT.length() <= 0 ? "null" : at.S01MIKT) + ",";
            command += (at.S01SIM.length() <= 0 ? "null" : at.S01SIM) + ",";
            command += (at.S01TMIK.length() <= 0 ? "null" : at.S01TMIK) + ",";
            command += (at.S01MURU.length() <= 0 ? "null" : at.S01MURU) + ",";
            command += (at.S01MSTR.length() <= 0 ? "null" : at.S01MSTR) + ",";
            command += (at.S01HKOD.length() <= 0 ? "null" : at.S01HKOD) + ",";
            command += (at.S01ITIP.length() <= 0 ? "null" : "'" + at.S01ITIP + "'") + ",";
            command += (at.S01SARG.length() <= 0 ? "null" : at.S01SARG) + ",";
            command += (at.S01ITAR.length() <= 0 ? "null" : at.S01ITAR) + ",";
            command += (at.S01IZMN.length() <= 0 ? "null" : at.S01IZMN) + ",";
            command += (at.S01IDOC.length() <= 0 ? "null" : at.S01IDOC) + ",";
            command += (at.S01PRICE.length() <= 0 ? "null" : at.S01PRICE) + ",";
            command += (at.S01CDAT.length() <= 0 ? "null" : at.S01CDAT) + " ";
            command += ")";
            st.execute(command);
        }
    }        
    
    public void ENT013_WRITE(ArrayList A) throws Exception
    {
        Statement st = conn.createStatement();

        for (int n = 0; n < A.size(); n++)
        {
            axe.sap.ZPP_ENT013_S_EXPORT at = (axe.sap.ZPP_ENT013_S_EXPORT) A.get(n);
            String command = "insert into ENT013(S13STAT, S13SKOD, S13HKOD, S13BNUM, S13KALN, S13AKOD, S13SIPT, S13SKU, S13LOT, S13FIRM, S13MIKT, S13SIM, S13TMIK, S13KTIP, S13ITAR, S13IZMN, S13TMTR, S13TMZM, S13UYER, S13TESS, S13MAKK) values (";
            command += (at.S13STAT.length() <= 0 ? "null" : at.S13STAT) + ",";
            command += (at.S13SKOD.length() <= 0 ? "null" : "'" + at.S13SKOD + "'") + ",";
            command += (at.S13HKOD.length() <= 0 ? "null" : at.S13HKOD) + ",";
            command += (at.S13BNUM.length() <= 0 ? "null" : at.S13BNUM) + ",";
            command += (at.S13KALN.length() <= 0 ? "null" : at.S13KALN) + ",";
            command += (at.S13AKOD.length() <= 0 ? "null" : "'" + at.S13AKOD + "'") + ",";
            command += (at.S13SIPT.length() <= 0 ? "null" : at.S13SIPT) + ",";
            command += (at.S13SKU.length() <= 0 ? "null" : "'" + at.S13SKU + "'") + ",";
            command += (at.S13LOT.length() <= 0 ? "null" : at.S13LOT) + ",";
            command += (at.S13FIRM.length() <= 0 ? "null" : at.S13FIRM) + ",";
            command += (at.S13MIKT.length() <= 0 ? "null" : at.S13MIKT) + ",";
            command += (at.S13SIM.length() <= 0 ? "null" : at.S13SIM) + ",";
            command += (at.S13TMIK.length() <= 0 ? "null" : at.S13TMIK) + ",";
            command += (at.S13KTIP.length() <= 0 ? "null" : at.S13KTIP) + ",";
            command += (at.S13ITAR.length() <= 0 ? "null" : at.S13ITAR) + ",";
            command += (at.S13IZMN.length() <= 0 ? "null" : at.S13IZMN) + ",";
            command += (at.S13TMTR.length() <= 0 ? "null" : at.S13TMTR) + ",";
            command += (at.S13TMZM.length() <= 0 ? "null" : at.S13TMZM) + ",";
            command += (at.S13UYER.length() <= 0 ? "null" : at.S13UYER) + ",";
            command += (at.S13TESS.length() <= 0 ? "null" : at.S13TESS) + ",";
            command += (at.S13MAKK.length() <= 0 ? "null" : at.S13MAKK) + " ";
            command += ")";
            st.execute(command);
        }
    }   
    public void ENT019_WRITE(ArrayList A) throws Exception
    {
        Statement st = conn.createStatement();

        for (int n = 0; n < A.size(); n++)
        {
            axe.sap.ZPP_ENT019_S_EXPORT at = (axe.sap.ZPP_ENT019_S_EXPORT) A.get(n);
            String command = "insert into ENT019(S19PALN, S19PYED, S19SKU, S19LOT, S19HTIP, S19MAKN, S19TESS, S19UYER, S19PTIP, S19SKOD, S19AKOD, S19UTAR, S19UZMN, S19PTAR, S19PZMN, S19MIK, S19SIM, S19IPTK, S19ITAR, S19IZMN) values (";
            command += (at.S19PALN.length() <= 0 ? "null" : at.S19PALN) + ",";
            command += (at.S19PYED.length() <= 0 ? "null" : at.S19PYED) + ",";
            command += (at.S19SKU.length() <= 0 ? "null" : "'" + at.S19SKU + "'") + ",";
            command += (at.S19LOT.length() <= 0 ? "null" : at.S19LOT) + ",";
            command += (at.S19HTIP.length() <= 0 ? "null" : at.S19HTIP) + ",";
            command += (at.S19MAKN.length() <= 0 ? "null" : at.S19MAKN) + ",";
            command += (at.S19TESS.length() <= 0 ? "null" : at.S19TESS) + ",";
            command += (at.S19UYER.length() <= 0 ? "null" : at.S19UYER) + ",";
            command += (at.S19PTIP.length() <= 0 ? "null" : at.S19PTIP) + ",";
            command += (at.S19SKOD.length() <= 0 ? "null" : "'" + at.S19SKOD + "'") + ",";            
            command += (at.S19AKOD.length() <= 0 ? "null" : "'" + at.S19AKOD + "'") + ",";
            command += (at.S19UTAR.length() <= 0 ? "null" : at.S19UTAR) + ",";
            command += (at.S19UZMN.length() <= 0 ? "null" : at.S19UZMN) + ",";
            command += (at.S19PTAR.length() <= 0 ? "null" : at.S19PTAR) + ",";
            command += (at.S19PZMN.length() <= 0 ? "null" : at.S19PZMN) + ",";
            command += (at.S19MIK.length() <= 0 ? "null" : at.S19MIK) + ",";
            command += (at.S19SIM.length() <= 0 ? "null" : at.S19SIM) + ",";
            command += (at.S19IPTK.length() <= 0 ? "null" : at.S19IPTK) + ",";
            command += (at.S19ITAR.length() <= 0 ? "null" : at.S19ITAR) + ",";
            command += (at.S19IZMN.length() <= 0 ? "null" : at.S19IZMN) + " ";
            command += ")";
            st.execute(command);
        }
    }  
    
//************** ENT005 Üretim süreci    
    public ArrayList ENT005_READ() throws Exception
    {
        ArrayList al = new ArrayList();
        ResultSet r = selectData("select * from ENT005 where S05STAT = 0");
        if (r != null) 
        {
            while (r.next())
            {
                axe.sap.ZPP_ENT005_S_IMPORT z = new axe.sap.ZPP_ENT005_S_IMPORT();
                z.S05STAT = getString(r, "S05STAT");
                z.S05PALN = getString(r, "S05PALN");
                z.S05YPAL = getString(r, "S05YPAL");
                z.S05MALK = getString(r, "S05MALK");
                z.S05LOTN = getString(r, "S05LOTN");
                z.S05HKOD = getString(r, "S05HKOD");
                z.S05EHKD = getString(r, "S05EHKD");
                z.S05FISYIL = getString(r, "S05FISYIL");
                z.S05FISN = getString(r, "S05FISN");
                z.S05UTAR = getString(r, "S05UTAR");
                z.S05UZMN = getString(r, "S05UZMN");
                z.S05VARD = getString(r, "S05VARD");
                z.S05MIKT = getString(r, "S05MIKT");
                z.S05SIM = getString(r, "S05SIM");
                z.S05TMIK = getString(r, "S05TMIK");
                z.S05BELN = getString(r, "S05BELN");
                z.S05BELT = getString(r, "S05BELT");
                z.S05SIPN = getString(r, "S05SIPN");
                z.S05MUSK = getString(r, "S05MUSK");
                z.S05MKOD = getString(r, "S05MKOD");
                z.S05TKOD = getString(r, "S05TKOD");
                z.S05UYER = getString(r, "S05UYER");
                z.S05UNO = getString(r, "S05UNO");
                z.S05KALN = getString(r, "S05KALN");
                z.S05PERS = getString(r, "S05PERS");
                z.S05SKOD = getString(r, "S05SKOD");
                z.S05DEPO = getString(r, "S05DEPO");
                z.S05ITAR = getString(r, "S05ITAR");
                z.S05IZMN = getString(r, "S05IZMN");
                z.S05TMTR = getString(r, "S05TMTR");
                z.S05TMZM = getString(r, "S05TMZM");
                z.S05SIRA = getString(r, "S05SIRA");
                al.add(z);
            }
        }
        
        return al;
    }
    public void ENT005_RETURN(ArrayList A) throws Exception
    {
        Statement st = conn.createStatement();

        for (int n = 0; n < A.size(); n++)
        {
            axe.sap.ZPP_ENT005_S_IMPORT at = (axe.sap.ZPP_ENT005_S_IMPORT) A.get(n);
            String command = "UPDATE ENT005 SET S05STAT = 1 WHERE S05SIRA = " + at.S05SIRA;
            st.execute(command);
        }
    }       
//************** ENT006 Teslimat başlık    
    public ArrayList ENT006_READ() throws Exception
    {
        ArrayList al = new ArrayList();
        ResultSet r = selectData("select * from ENT006 where S06STAT = 0");
        if (r != null) 
        {
            while (r.next())
            {
                axe.sap.ZPP_ENT006_S_IMPORT z = new axe.sap.ZPP_ENT006_S_IMPORT();
                z.S06SKOD = getString(r, "S06SKOD");
                z.S06TESL = getString(r, "S06TESL");
                z.S06STAT = getString(r, "S06STAT");
                z.S06HKOD = getString(r, "S06HKOD");
                z.S06HTIP = getString(r, "S06HTIP");
                z.S06INUM = getString(r, "S06INUM");
                z.S06STTU = getString(r, "S06STTU");
                z.S06KAMN = getString(r, "S06KAMN");
                z.S06REFN = getString(r, "S06REFN");
                z.S06IRST = getString(r, "S06IRST");
                z.S06IRSZ = getString(r, "S06IRSZ");
                z.S06SSIP = getString(r, "S06SSIP");
                z.S06GBEK = getString(r, "S06GBEK");
                z.S06PLKA = getString(r, "S06PLKA");
                z.S06KONT = getString(r, "S06KONT");
                z.S06KAMT = getString(r, "S06KAMT");
                z.S06ACIN = getString(r, "S06ACIN");
                z.S06FSTR = getString(r, "S06FSTR");
                z.S06RPRT = getString(r, "S06RPRT");
                z.S06YUKN = getString(r, "S06YUKN");
                z.S06ITAR = getString(r, "S06ITAR");
                z.S06IZMN = getString(r, "S06IZMN");
                z.S06TMTR = getString(r, "S06TMTR");
                z.S06TMZM = getString(r, "S06TMZM");
                z.S06DEPO = getString(r, "S06DEPO");
                z.S06RSIP = getString(r, "S06RSIP");
                z.S06FIRM = getString(r, "S06FIRM");
                z.S06TFIR = getString(r, "S06TFIR");
                al.add(z);
            }
        }
        
        return al;
    }
    public void ENT006_RETURN(ArrayList A) throws Exception
    {
        Statement st = conn.createStatement();

        for (int n = 0; n < A.size(); n++)
        {
            axe.sap.ZPP_ENT006_S_IMPORT at = (axe.sap.ZPP_ENT006_S_IMPORT) A.get(n);
            String command = "UPDATE ENT006 SET S06STAT = 1 WHERE S06TESL = " + at.S06TESL;
            st.execute(command);
        }
    }     
//************** ENT007 Teslimat kalem    
    public ArrayList ENT007_READ() throws Exception
    {
        ArrayList al = new ArrayList();
        ResultSet r = selectData("select * from ENT007 where S07STAT = 0");
        if (r != null) 
        {
            while (r.next())
            {
                axe.sap.ZPP_ENT007_S_IMPORT z = new axe.sap.ZPP_ENT007_S_IMPORT();
                z.S07SIRK = getString(r, "S07SIRK");
                z.S07TESL = getString(r, "S07TESL");
                z.S07KALN = getString(r, "S07KALN");
                z.S07STAT = getString(r, "S07STAT");
                z.S07HKOD = getString(r, "S07HKOD");
                z.S07SKOD = getString(r, "S07SKOD");
                z.S07LOTN = getString(r, "S07LOTN");
                z.S07MIKT = getString(r, "S07MIKT");
                z.S07SIMM = getString(r, "S07SIMM");
                z.S07TOPM = getString(r, "S07TOPM");
                z.S07STTU = getString(r, "S07STTU");
                z.S07YUKN = getString(r, "S07YUKN");
                z.S07ITAR = getString(r, "S07ITAR");
                z.S07IZMN = getString(r, "S07IZMN");
                z.S07TMTR = getString(r, "S07TMTR");
                z.S07TMZM = getString(r, "S07TMZM");
                z.S07SIRA = getString(r, "S07SIRA");
                al.add(z);
            }
        }
        
        return al;
    }
    public void ENT007_RETURN(ArrayList A) throws Exception
    {
        Statement st = conn.createStatement();

        for (int n = 0; n < A.size(); n++)
        {
            axe.sap.ZPP_ENT007_S_IMPORT at = (axe.sap.ZPP_ENT007_S_IMPORT) A.get(n);
            String command = "UPDATE ENT007 SET S07STAT = 1 WHERE S07TESL = " + at.S07TESL;
                   command += "AND S07KALN = " + at.S07KALN;            
            st.execute(command);
        }
    }      
//************** ENT008 Axata Karantina ve Blokaj    
    public ArrayList ENT008_READ() throws Exception
    {
        ArrayList al = new ArrayList();
        ResultSet r = selectData("select * from ENT008 where S08STAT = 0");
        if (r != null) 
        {
            while (r.next())
            {
                axe.sap.ZPP_ENT008_S_IMPORT z = new axe.sap.ZPP_ENT008_S_IMPORT();
                z.S08STAT = getString(r, "S08STAT");
                z.S08SKOD = getString(r, "S08SKOD");
                z.S08AKOD = getString(r, "S08AKOD");
                z.S08SKUK = getString(r, "S08SKUK");
                z.S08LOTN = getString(r, "S08LOTN");
                z.S08BNED = getString(r, "S08BNED");
                z.S08MIKT = getString(r, "S08MIKT");
                z.S08SIM = getString(r, "S08SIM");
                z.S08TMIK = getString(r, "S08TMIK");
                z.S08STTU = getString(r, "S08STTU");
                z.S08PALN = getString(r, "S08PALN");
                z.S08ITAR = getString(r, "S08ITAR");
                z.S08IZMN = getString(r, "S08IZMN");
                z.S08TMTR = getString(r, "S08TMTR");
                z.S08TMZM = getString(r, "S08TMZM");
                z.S08SIPN = getString(r, "S08SIPN");
                z.S08SIRA = getString(r, "S08SIRA");
                al.add(z);
            }
        }
        
        return al;
    }
    public void ENT008_RETURN(ArrayList A) throws Exception
    {
        Statement st = conn.createStatement();

        for (int n = 0; n < A.size(); n++)
        {
            axe.sap.ZPP_ENT008_S_IMPORT at = (axe.sap.ZPP_ENT008_S_IMPORT) A.get(n);
            String command = "UPDATE ENT008 SET S08STAT = 1 WHERE S08SIRA = " + at.S08SIRA;
                   // command += "AND S08SKUK = " + at.S08SKUK;            
            st.execute(command);
        }
    }   
//************** ENT011 Axata Karantina ve Blokaj    
    public ArrayList ENT011_READ() throws Exception
    {
        ArrayList al = new ArrayList();
        ResultSet r = selectData("select * from ENT011 where S11STAT = 0");
        if (r != null) 
        {
            while (r.next())
            {
                axe.sap.ZPP_ENT011_S_IMPORT z = new axe.sap.ZPP_ENT011_S_IMPORT();
                z.S11STAT = getString(r, "S11STAT");
                z.S11SIRK = getString(r, "S11SIRK");
                z.S11DEPO = getString(r, "S11DEPO");
                z.S11HKOD = getString(r, "S11HKOD");
                z.S11HTIP = getString(r, "S11HTIP");
                z.S11STIP = getString(r, "S11STIP");
                z.S11HKD1 = getString(r, "S11HKD1");
                z.S11HKD2 = getString(r, "S11HKD2");
                z.S11PALN = getString(r, "S11PALN");
                z.S11MALK = getString(r, "S11MALK");
                z.S11LOTN = getString(r, "S11LOTN");
                z.S11MIKT = getString(r, "S11MIKT");
                z.S11SIM = getString(r, "S11SIM");
                z.S11TMIK = getString(r, "S11TMIK");
                z.S11UNO = getString(r, "S11UNO");
                z.S11MAKN = getString(r, "S11MAKN");
                z.S11TESS = getString(r, "S11TESS");
                z.S11UYER = getString(r, "S11UYER");
                z.S11UTAR = getString(r, "S11UTAR");
                z.S11USAA = getString(r, "S11USAA");
                z.S11ITAR = getString(r, "S11ITAR");
                z.S11IZMN = getString(r, "S11IZMN");
                z.S11TMTR = getString(r, "S11TMTR");
                z.S11TMZM = getString(r, "S11TMZM");
                z.S11PERS = getString(r, "S11PERS");
                z.S11SIRA = getString(r, "S11SIRA");
                al.add(z);
            }
        }
        
        return al;
    }
    public void ENT011_RETURN(ArrayList A) throws Exception
    {
        Statement st = conn.createStatement();

        for (int n = 0; n < A.size(); n++)
        {
            axe.sap.ZPP_ENT011_S_IMPORT at = (axe.sap.ZPP_ENT011_S_IMPORT) A.get(n);
            String command = "UPDATE ENT011 SET S11STAT = 1 WHERE S11SIRA = " + at.S11SIRA;           
            st.execute(command);
        }
    }           
    
    //************** ENT020 Axata Sevk (view)
    public ArrayList ENT020_READ(axe.sap.ZPP_ENT020_S_PARAM P) throws Exception
    {        
        ArrayList al = new ArrayList();
        
        conn.createStatement().execute("set dateformat dmy;");
        ResultSet r = selectData("select * from JD_Sevk where Cikis_Tarihi >= '" + sqlFormatDate(P.DATUM) + "'");
        if (r != null) 
        {
            while (r.next())
            {
                axe.sap.ZPP_ENT020_S_IMPORT z = new axe.sap.ZPP_ENT020_S_IMPORT();
                z.S20DEPO = getString(r, "Depo");
                z.S20PALN = getString(r, "Palet_No");
                z.S20SKUK = getString(r, "SKU_Kodu");
                z.S20SNFK = getString(r, "Sinif_Kodu");
                z.S20SKUT = getString(r, "SKU_Tanimi");
                z.S20LOTN = getString(r, "Lot#");
                z.S20CKST = getString(r, "Cikis_Tipi");
                z.S20SVKY = getString(r, "Sevk_Yili");
                z.S20SVKN = getString(r, "Sevk_No");
                z.S20MIKT = getString(r, "Miktar");
                z.S20BRM = getString(r, "BRM");
                z.S20SPVK = getString(r, "Sip_Veren_Firma_Kodu");
                z.S20SPVR = getString(r, "Sip_Veren_Firma");
                z.S20TSLK = getString(r, "Tes_Al_Firma_Kodu");
                z.S20TSLA = getString(r, "Tes_Al_Firma");
                z.S20UTAR = getString(r, "Uretim_Tarihi");
                z.S20CTAR = getString(r, "Cikis_Tarihi");
                z.S20STAR = getString(r, "SK_Tarihi");
                z.S20TESS = getString(r, "Tesis");
                z.S20MAKN = getString(r, "Makine_Kodu");
                z.S20UYER = getString(r, "Uretim_Yeri");
                al.add(z);
            }
        }
        
        return al;
    }    
    
    //************** ENT021 Axata Stok (view)
    public ArrayList ENT021_READ() throws Exception
    {
        ArrayList al = new ArrayList();
        ResultSet r = selectData("select * from JD_Stok");
        if (r != null) 
        {
            while (r.next())
            {
                axe.sap.ZPP_ENT021_S_IMPORT z = new axe.sap.ZPP_ENT021_S_IMPORT();
                z.S21DEPO = getString(r, "Depo");
                z.S21PALN = getString(r, "Palet_No");
                z.S21SKUK = getString(r, "SKU_Kodu");
                z.S21SNFK = getString(r, "Sinif_Kodu");
                z.S21SKUT = getString(r, "SKU_Tanimi");
                z.S21LOTN = getString(r, "Lot#");
                z.S21PLTS = getString(r, "Palet_Statu");
                z.S21SIPN = getString(r, "Siparis_No");
                z.S21UTAR = getString(r, "Uretim_Tarihi");
                z.S21STAR = getString(r, "SK_Tarihi");
                z.S21MIKT = getString(r, "Stok");
                z.S21BRM = getString(r, "BRM");
                z.S21ADRS = getString(r, "Adres");
                z.S21OZLB = getString(r, "Ozel_Bolge");
                z.S21BLKN = getString(r, "Bloke_Nedeni");
                z.S21BTAR = getString(r, "Bloke_Tarihi");
                z.S21TESS = getString(r, "Tesis");
                z.S21MKNK = getString(r, "Makine_Kodu");
                z.S21UYER = getString(r, "Uretim_Yeri");
                z.S21PLTT = getString(r, "Palet_Tipi");
                z.S21STKT = getString(r, "Stok_Tipi");
                al.add(z);
            }
        }
        
        return al;
    }        
}
