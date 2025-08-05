package com.example.demo.model;

import com.example.demo.model.psp.PspType;
import lombok.Data;

@Data
public class DonationForm {
  private String email;
  private PspType pspType;
  private String pspPaymentId;
}
