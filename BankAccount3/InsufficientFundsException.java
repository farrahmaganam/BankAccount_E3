package BankAccount3;

/** Exception thrown when an account does not have sufficient funds. */
public class InsufficientFundsException extends RuntimeException {
  public InsufficientFundsException(double balance) {
    super(String.format("Insufficient Balance. \nCurrent balance: %.2f", balance));
  }
}
