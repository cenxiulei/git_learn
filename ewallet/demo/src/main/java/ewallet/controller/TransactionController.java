package ewallet.controller;

import ewallet.dto.TransactionDto;
import ewallet.model.Transaction;
import ewallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.createTransaction(transactionDto));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransaction(@PathVariable String transactionId) {
        return ResponseEntity.ok(transactionService.getTransaction(transactionId));
    }
}

