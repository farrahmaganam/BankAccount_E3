package BankAccount3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** For transaction history, time and date. */
public class Transaction {

  private static final DateTimeFormatter TIMESTAMP_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  String type;
  double amount;
  LocalDateTime timeStamp;

  /** Creates a transaction with the specified type and amount. */
  public Transaction(String type, double amount) {
    this.type = type;
    this.amount = amount;
    this.timeStamp = LocalDateTime.now();
  }

  public String getFormattedTimestamp() {
    return timeStamp.format(TIMESTAMP_FORMAT);
  }

  @Override
  public String toString() {
    return String.format("%s: %.2f at %s", type, amount, getFormattedTimestamp());
  }
}
