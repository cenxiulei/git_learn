package ewallet.controller;

import ewallet.application.TransactionApplicationService;
import ewallet.domian.entities.valueobject.Money;
import ewallet.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionApplicationService transactionApplicationService;

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Money amount = new Money(transactionDTO.getAmount());
        TransactionDTO createdTransaction = transactionApplicationService.createTransaction(
                transactionDTO.getWalletId(), amount, transactionDTO.getType());
        return ResponseEntity.ok(createdTransaction);
    }


}

