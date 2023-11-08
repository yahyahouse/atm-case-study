package data.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import data.constant.TransactionType;
import lombok.Data;

@Data
public class Transaction {
  private String id;

  private LocalDateTime timestamp;

  private Customer customer;
  private TransactionType type;

  private BigDecimal expense;
}
