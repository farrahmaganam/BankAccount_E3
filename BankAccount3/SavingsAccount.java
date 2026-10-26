package BankAccount3;

/** Represents a bank account with an associated owner name. */
public class SavingsAccount extends BankAccount {
  
  public SavingsAccount(String ownerName) {
    this.ownerName = ownerName;
  }

  private String ownerName;

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }
  
}
