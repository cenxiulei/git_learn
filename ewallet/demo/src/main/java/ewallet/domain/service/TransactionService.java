package ewallet.domain.service;

import ewallet.domain.model.Transaction;
import ewallet.domain.respository.TransactionRepository;
import ewallet.infrastructure.messaging.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MessagePublisher messagePublisher;

    public Transaction createTransaction(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        messagePublisher.publish("Transaction created: " + savedTransaction.getId());
        return savedTransaction;
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }
}
