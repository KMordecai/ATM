/*
 * This class creates the JFrame interface.
 * Tere are four classes within this
 * program that create account objects.
 */
package atm;

// imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @author Krystal Mordecai
 * 1-31-2017
 */

// ATM class
public class ATM extends JFrame {
    // Variables to create the JFrame interface.
    private JButton withdrawButton = new JButton("Withdraw");
    private JButton depositButton = new JButton("Deposit");
    private JButton transferToButton = new JButton("Transfer");
    private JButton balanceButton = new JButton("Balance");
    private JRadioButton checkingRadio = new JRadioButton("Checking");
    private JRadioButton savingsRadio = new JRadioButton("Savings");
    private JTextField entry = new JTextField("");
    private ButtonGroup radios = new ButtonGroup();
    private JOptionPane frame = new JOptionPane();
    private static final DecimalFormat dollar = new DecimalFormat("0.00"); // This outputs all currency into dollar formats follwed by two decimals.
    private static Account checkingAccount = new Account().new Checking(); // Account objects for the checkings accounts.
    private static Account savingsAccount = new Account().new Savings(); // Account objects for the savings accounts.
    
        // ATM constructor initialization
    public ATM(double checkingStartingBalance, double savingsStartingBalance) {
        super("ATM Machine");
        setLayout(new GridBagLayout());
        GridBagConstraints gridLayout = new GridBagConstraints();
        setFrame(350, 200);
        JPanel buttonPanel = new JPanel();
        JPanel textValue = new JPanel();
        setResizable(true);
        gridLayout.gridy = 2;
        add(buttonPanel);
        add(textValue, gridLayout);
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        textValue.setLayout(new GridLayout(1, 1));
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(transferToButton);
        buttonPanel.add(balanceButton);
        radios.add(checkingRadio);
        radios.add(savingsRadio);
        buttonPanel.add(checkingRadio);
        buttonPanel.add(savingsRadio);
        entry.setPreferredSize(new Dimension(225, 25));
        checkingRadio.setSelected(true);
        textValue.add(entry);
        // methods that correspond to the buttons chosen by the user
        withdrawButton.addActionListener(new Withdraw());
        depositButton.addActionListener(new Deposit());
        transferToButton.addActionListener(new Transfer());
        balanceButton.addActionListener(new Balance());
        makeAccounts(checkingStartingBalance, savingsStartingBalance);
    }
    
        // A method that declares the Account signatures.
    public static void makeAccounts(double checkingBalance, double savingsBalance) {
        checkingAccount.setBalance(checkingBalance);
        savingsAccount.setBalance(savingsBalance);
    }
    
    // This method executes when the withdraw option is selected by the user.
    class Withdraw implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if (getValue() > 0 && getValue() % 20 == 0) {
 
                    if (checkingRadio.isSelected()) {
                        checkingAccount.withdraw(getValue());
                        showMessageDialog(frame, dollar.format(getValue()) +  " withdrawn from Checking.");
                        // Prints the amount withdrawn and the balance from checkings as a receipt.
                        showMessageDialog(frame, "Recepit\n" +  "Withdrawn:\n" + dollar.format(getValue()) +
                                "\nBalance:\n" + dollar.format(checkingAccount.getBalance()));
                       
                    } else if (savingsRadio.isSelected()) {
                        savingsAccount.withdraw(getValue());
                        showMessageDialog(frame, dollar.format(getValue()) +" withdrawn from Savings.");
                        // Prints the amount withdrawn and balance from savings as a recepit.
                        showMessageDialog(frame, "Recepit\n" +  "Withdrawn:\n" + dollar.format(getValue()) +
                                "\nBalance:\n" + dollar.format(savingsAccount.getBalance()));
                    }
                    clearValue();
                } else numberCheck(); // calls numberCheck method if an invalid format is entered by the user
                clearValue(); // calls the clear entry method regardless of input chosen
            } catch (InsufficientFunds insufficientFunds) { // calls the insufficient funds class 
            }
        }
    }

    // This class executes when the Deposit option is chosen.
    class Deposit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (getValue() > 0) {
                if (checkingRadio.isSelected()) {
                    checkingAccount.deposit(getValue());
                    showMessageDialog(frame, dollar.format(getValue()) +
                            " deposited into Checking.");
                } else if (savingsRadio.isSelected()) {
                    savingsAccount.deposit(getValue());
                    showMessageDialog(frame, dollar.format(getValue()) +
                            " deposited into Savings.");
                }
                clearValue(); // calls the clear entry method regardless of input chosen
            } else numberCheck(); // calls numberCheck method if an invalid format is entered by the user
            clearValue(); // calls the clear entry method regardless of input chosen
        }
    }

    // This class executes when the Transfer option is chosen.
    class Transfer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if (getValue() > 0) {
                    if (checkingRadio.isSelected()) {
                        savingsAccount.transferFrom(getValue());
                        checkingAccount.transferTo(getValue());
                        showMessageDialog(frame, dollar.format(getValue()) +
                                " transferred from Savings to Checking.");
                    } else if (savingsRadio.isSelected()) {
                        checkingAccount.transferFrom(getValue());
                        savingsAccount.transferTo(getValue());
                        showMessageDialog(frame, dollar.format(getValue()) +
                                " transferred from Checking to Savings.");
                    }
                    clearValue();
                } else numberCheck();
                clearValue();
            } catch (InsufficientFunds insufficientFunds) {
            }
        }
    }

    // This class executes when the Balance option is chosen.
    class Balance implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (checkingRadio.isSelected()) {
                showMessageDialog(frame,
                        "Your checkings balance is: \n" +
                                dollar.format(checkingAccount.getBalance()));
            } else if (savingsRadio.isSelected()) {
                showMessageDialog(frame,
                        "Your savings balance is: \n" +
                                dollar.format(savingsAccount.getBalance()));
            } else numberCheck();
            clearValue();
        }
    }
    
    // gets the value from the Text field
    public double getValue() {
        try {
            return Double.parseDouble(entry.getText());
        } catch (NumberFormatException exception) {
            clearValue();
            return 0;
        }
    }
    
    // This method is displayed if an invalid input is received
    public void numberCheck() {
        showMessageDialog(frame, "Only enter whole numbers to withdraw." +
                "\nEnter whole numbers or decimals to deposit or transfer");
    }

    public void clearValue() {
        entry.setText(" ");
    }

    // Sets the frame size location and default action.
    private void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // allows the ATM machine to be shown
    public void displayATM() {
       setVisible(true);
    }

    public static void main(String[] args) {
        // creates the account checkings and savings ammounts
        ATM frame = new ATM(2000, 2000); // 2000 is the starting Balances for both accounts
        frame.displayATM(); // calls the display method
    }
}
