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
import java.util.*;
import javax.swing.event.*;

public class IntThread extends Thread {

    protected EventListenerList listenerList;
    
    public IntThread() 
    {
        listenerList = new EventListenerList();
    }    
    
    public void run()
    {
        IntTimer it = new IntTimer();
        it.addIntEventListener(
                new IntEventListener()
                {
                    public void intEventOccured(IntEvent Evt)
                    {
                        fireIntEvent(Evt);
                    }
                }
        );
        
        Timer t = new Timer();
        long delay = 0;
        t.scheduleAtFixedRate(it, delay, Main.config.intPeriod);
    }
    
    public void addIntEventListener(IntEventListener Listener) 
    {
        listenerList.add(IntEventListener.class, Listener);
    }   
    
    private void fireIntEvent(axe.integration.IntEvent Evt)
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
}
