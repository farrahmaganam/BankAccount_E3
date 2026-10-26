package BankAccount3;

import java.util.ArrayList;
import java.util.List;

/** Represents a bank account with deposit, withdrawal, and freeze operations. */
public class BankAccount {
  private double balance;
  private boolean isFrozen;
  List<Transaction> transactionHistory;
    
  /** Creates a new, unfrozen bank account with a zero balance. */
  public BankAccount() {
    this.balance = 0;
    this.isFrozen = false;
    this.transactionHistory = new ArrayList<>();
  }

  /** DEPOSIT. */
  public void deposit(double amount) { 
    if (isFrozen) {
      throw new AccountFrozenException();
    }
    if (!Double.isFinite(amount) || amount <= 0) {
      System.out.printf("Amount deposited: %.2f%n", amount);
      throw new InvalidAmountException();
    }
    System.out.printf("Amount deposited: %.2f%n", amount);
    balance += amount;
    transactionHistory.add(new Transaction("Deposit", amount));
    System.out.printf("Current balance: %.2f%n", balance);
  }

  /** WITHDRAW. */
  public void withdraw(double amount) {
    if (isFrozen) {
      throw new AccountFrozenException();
    }
    if (!Double.isFinite(amount) || amount <= 0) {
      throw new InvalidAmountException();
    }
    if (amount > balance) {
      System.out.printf("Amount withdrawn: %.2f%n", amount);
      throw new InsufficientFundsException(balance);
    }
    System.out.printf("Amount withdrawn: %.2f%n", amount);
    balance -= amount;
    transactionHistory.add(new Transaction("Withdraw", amount));
    System.out.printf("Current balance: %.2f%n", balance);
  }


  public List<Transaction> getTransactionHistory() {
    return transactionHistory;
  }

  /** HISTORY. */
  public void printTransactionHistory() {
    if (transactionHistory.isEmpty()) {
      System.out.println("No transactions yet.");
      return;
    }

    System.out.println("Transaction history:");
    for (Transaction transaction : transactionHistory) {
      System.out.printf("%s: %.2f at %s%n",
          transaction.type, transaction.amount,
          transaction.getFormattedTimestamp());
    }
  }

  /** BALANCE. */
  public double getBalance() {
    return balance;
  }

  /** FREEZE. */
  public void freezeAccount() {
    isFrozen = true;
    System.out.println("Account is now FROZEN.");
  }

  /** UNFREEZE. */
  public void unfreezeAccount() {
    isFrozen = false;
    System.out.println("Account is now UNFROZEN.");
  }
}
