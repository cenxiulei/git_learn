package ewallet.controller;

import ewallet.model.Transaction;
import ewallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestParam String fromWalletId, @RequestParam String toWalletId, @RequestParam double amount, @RequestParam String currency) {
        return transactionService.createTransaction(fromWalletId, toWalletId, amount, currency);
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransaction(@PathVariable String transactionId) {
        return transactionService.getTransaction(transactionId);
    }
}

