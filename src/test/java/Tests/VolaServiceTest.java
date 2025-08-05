package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.psp.Payment;
import com.example.demo.service.VolaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class VolaServiceTest {

  @Mock private RestTemplate restTemplate;

  @InjectMocks private VolaService volaService;

  @Test
  void shouldVerifyPayment() {
    Payment mockPayment = Payment.builder().id("123").build();
    when(restTemplate.getForObject(anyString(), eq(Payment.class), any(), any(), any(), any()))
        .thenReturn(mockPayment);

    Payment result = volaService.verifyPayment("test@hei.school", "ORANGE_MONEY", "ref123");

    assertNotNull(result);
    assertEquals("123", result.id());
    verify(restTemplate).getForObject(anyString(), eq(Payment.class), any(), any(), any(), any());
  }
}
