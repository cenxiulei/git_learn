package ewallet.service;

import ewallet.dto.TransactionDto;
import ewallet.model.Transaction;
import ewallet.respository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setFromWalletId(transactionDto.getFromWalletId());
        transaction.setToWalletId(transactionDto.getToWalletId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCurrency(transactionDto.getCurrency());
        transaction.setStatus("PENDING");
        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(String transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
    }
}
