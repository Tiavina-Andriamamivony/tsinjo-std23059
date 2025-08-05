package com.example.demo.service;

import com.example.demo.model.psp.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class VolaService {
  private final RestTemplate restTemplate;

  public Payment verifyPayment(String email, String pspType, String pspPaymentId) {
    String apiKey = System.getenv("VOLA_API_KEY");
    String url =
        System.getenv("VOLA_BASE_URL")
            + "/payment?apiKey={apiKey}&payerEmail={email}&pspType={pspType}&pspPaymentId={pspPaymentId}";

    return restTemplate.getForObject(url, Payment.class, apiKey, email, pspType, pspPaymentId);
  }

  public Payment createPayment(String email, String pspType, String pspPaymentId) {
    String apiKey = System.getenv("VOLA_API_KEY");
    String url =
        System.getenv("VOLA_BASE_URL")
            + "/payment?apiKey={apiKey}&payerEmail={email}&pspType={pspType}&pspPaymentId={pspPaymentId}";

    return restTemplate.postForObject(
        url, null, Payment.class, apiKey, email, pspType, pspPaymentId);
  }
}
