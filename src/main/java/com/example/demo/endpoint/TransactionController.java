package com.example.demo.endpoint;

import com.example.demo.model.Transaction;
import com.example.demo.model.psp.Payment;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TransactionController {
  private final PaymentService paymentService;
  private final TransactionService transactionService;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("donationForm", new DonationForm());
    model.addAttribute("transactions", transactionService.getAllTransactions());
    return "index";
  }

  @PostMapping("/donation")
  public String donate(@Valid DonationForm form, Model model) {
    Payment payment = paymentService.processDonation(form);
    model.addAttribute(
        "message",
        payment.getVerificationStatus() == SUCCEEDED
            ? "Donation réussie!"
            : "Donation en cours de vérification");
    return "redirect:/";
  }

  @GetMapping("/transactions")
  @ResponseBody
  public List<Transaction> getTransactions() {
    return transactionService.getAllTransactions();
  }
}
