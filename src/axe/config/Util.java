/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package axe.config;

/**
 *
 * @author Kerem
 */

import java.io.*;
import java.util.*;

public class Util {
    
    public Util() {
    }
    
    public static ArrayList readTextFile(String path) throws Exception
    {
        File aFile = new File(path);
        ArrayList contents = new ArrayList();
        BufferedReader input = null;
        
        input = new BufferedReader( new FileReader(aFile));
        String line = null;
        while (( line = input.readLine()) != null)
        {
            contents.add(line);
        }
        
        return contents;
    }     

}
