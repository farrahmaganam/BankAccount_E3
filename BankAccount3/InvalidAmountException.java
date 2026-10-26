package BankAccount3;

/** Thrown when an account operation receives an invalid amount. */
public class InvalidAmountException extends RuntimeException {
  public InvalidAmountException() {
    super("Invalid amount. Must be greater than 0");
  }
}