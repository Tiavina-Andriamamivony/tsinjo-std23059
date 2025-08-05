package com.example.demo.model.psp;

import static com.example.demo.model.VerificationStatus.*;

import com.example.demo.model.User;
import com.example.demo.model.VerificationStatus;
import java.time.Instant;
import lombok.Builder;

@Builder(toBuilder = true)
public record Payment(
    String id,
    PspPayment pspPayment,
    Instant creationInstant,
    Instant lastPspVerificationInstant,
    int verificationAttemptNb,
    User payer) {

  private static final int MAX_VERIFICATION_ATTEMPT_NB = 5;

  public boolean hasNoMoreVerificationAttempt() {
    return verificationAttemptNb > MAX_VERIFICATION_ATTEMPT_NB;
  }

  public VerificationStatus getVerificationStatus() {
    if (pspPayment.amount() != null) {
      return SUCCEEDED;
    }
    if (hasNoMoreVerificationAttempt()) {
      return FAILED;
    }
    return VERIFYING;
  }
}
