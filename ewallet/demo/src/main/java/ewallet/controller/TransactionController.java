package ewallet.controller;


import ewallet.application.TransactionApplicationService;
import ewallet.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionApplicationService transactionApplicationService;

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO createdTransaction = transactionApplicationService.createTransaction(transactionDTO);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long id) {
        TransactionDTO transaction = transactionApplicationService.getTransaction(id);
        return ResponseEntity.ok(transaction);
    }
}

