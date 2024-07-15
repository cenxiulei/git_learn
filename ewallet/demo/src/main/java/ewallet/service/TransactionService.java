package ewallet.service;

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

    public Transaction createTransaction(String fromWalletId, String toWalletId, double amount, String currency) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setFromWalletId(fromWalletId);
        transaction.setToWalletId(toWalletId);
        transaction.setAmount(amount);
        transaction.setCurrency(currency);
        transaction.setStatus("PENDING");
        transaction.setCreatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(String transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
    }
}
