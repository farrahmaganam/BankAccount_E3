package BankAccount3;

/** Defines operations for managing bank accounts. */
public interface Bank {

  BankAccount addAccount(BankAccount account);
  
  BankAccount getAccount(int id);

  void listAccounts();

}
