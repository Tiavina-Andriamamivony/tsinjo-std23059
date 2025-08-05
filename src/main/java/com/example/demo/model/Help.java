package com.example.demo.model;

import com.example.demo.model.psp.PspPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Help {
    private String id;
    private Donor donor;
    private int amount;
    private Beneficiary beneficiary;
    private PspPayment payment;
}
