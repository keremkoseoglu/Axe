/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package axe.integration;

/**
 *
 * @author Kerem
 */

import java.util.*;

public class IntEvent extends EventObject {
    public String text;
    
    /** Creates a new instance of IntEvent */
    public IntEvent(Object source) {
        super(source);
    }    
}
