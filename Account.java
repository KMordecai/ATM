/*
 * This class is used to implement the
 * user's account balance in checkings
 * and savings.
 */
package atm;

/**
 * @author Krystal Mordecai
 * 1-31-2017
 */

public class Account {
    // the variable balance is type double
    private double balance;
    private double deduct = 1.50;
    public int withdrawn;

    // default Account constructor
    public Account() {
    }

    // sets the account balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // retrieves the user's balance
    public double getBalance() {
        return this.balance;
    }

    // Checking class
    public class Checking extends Account {
        // default Checkings constructor
        public Checking() {
        }
    }

    // Savings class
    public class Savings extends Account {
        // default Savings constructor
        public Savings() {
        }
    }

        // A method that subtracts the amount withdrawn from the balance.
        // The method also deducts a service charge if more than 4 withdraws 
        // are made.
        public void withdraw(double withdrawAmount) throws InsufficientFunds { // points to insufficientFunds class if thrown
           for (int i = 0; i >= 4; i++) // for loop to iterate withdraws
            i++;
           withdrawn++;
        if (this.balance - withdrawAmount < 0) { // 
            throw new InsufficientFunds();
        }
           if (withdrawn >= 4) { // if 4 or more withdraws are made, a deduction is processed
            this.balance = this.balance - withdrawAmount - deduct;
        }
        // subtracts the amount windrawn from the balance when less than 4
        this.balance = this.balance - withdrawAmount; 
        }
 
        // adds the deposit to the balance
        public void deposit(double depositAmount) {
            this.balance = this.balance + depositAmount;
        }

        // adds money to the balance
        public void transferTo(double transferAmount) {
            this.balance = this.balance + transferAmount;
        }

        // subtracts the transfered amount from the balance
        public void transferFrom(double transferAmount) throws InsufficientFunds { // throws to InsufficientFunds class
            if (this.balance - transferAmount < 0) {
                throw new InsufficientFunds();
            }
            this.balance = this.balance - transferAmount;
        }
}
