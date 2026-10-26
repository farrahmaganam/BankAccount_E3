package BankAccount3;

/** Exception thrown when a transaction is attempted on a frozen account. */
public class AccountFrozenException extends RuntimeException {
  public AccountFrozenException() {
    super("Account is FROZEN. Unable to transact.");
  }
}
