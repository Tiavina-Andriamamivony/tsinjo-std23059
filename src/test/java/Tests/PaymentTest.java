package Tests;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.User;
import com.example.demo.model.VerificationStatus;
import com.example.demo.model.psp.Payment;
import com.example.demo.model.psp.PspPayment;
import com.example.demo.model.psp.PspType;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class PaymentTest {

  @Test
  void shouldReturnCorrectVerificationStatus() {
    User user = new User("user@hei.school", "John", "Doe", "john@hei.school");
    PspPayment pspPayment = new PspPayment(PspType.ORANGE_MONEY, "123", 1000, Instant.now());

    // Cas SUCCEEDED
    Payment payment =
        Payment.builder().pspPayment(pspPayment).verificationAttemptNb(0).payer(user).build();
    assertEquals(VerificationStatus.SUCCEEDED, payment.getVerificationStatus());

    // Cas FAILED
    Payment failedPayment =
        payment.toBuilder()
            .pspPayment(new PspPayment(PspType.ORANGE_MONEY, "123", null, Instant.now()))
            .verificationAttemptNb(6)
            .build();
    assertEquals(VerificationStatus.FAILED, failedPayment.getVerificationStatus());

    // Cas VERIFYING
    Payment verifyingPayment =
        payment.toBuilder()
            .pspPayment(new PspPayment(PspType.ORANGE_MONEY, "123", null, Instant.now()))
            .verificationAttemptNb(3)
            .build();
    assertEquals(VerificationStatus.VERIFYING, verifyingPayment.getVerificationStatus());
  }
}
