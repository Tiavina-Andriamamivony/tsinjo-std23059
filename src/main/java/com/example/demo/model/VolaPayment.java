package com.example.demo.model;

import java.time.Instant;
import lombok.Data;

@Data
public class VolaPayment {
  private String id;
  private Integer amount;
  private String verificationStatus;
  private Instant creationInstant;
}
