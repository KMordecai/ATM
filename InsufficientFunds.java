/*
 * This class is called
 * when insufficient funds 
 * result from user requests.
 */
package atm;

import javax.swing.JOptionPane;

/**
 * @author Krystal Mordecai
 * 1-31-2017
 */

public class InsufficientFunds extends Exception {
    
    // This method is called when the user has insuffient funds.
    public InsufficientFunds() {
        JOptionPane frame = new JOptionPane();
        JOptionPane.showMessageDialog(frame, "Insufficient Funds"); // Insufficient Funds appear in a frame.
    }
}
