package ewallet.domian.service;

import ewallet.domian.model.Transaction;
import ewallet.domian.model.Wallet;
import ewallet.domian.model.valueobject.Money;
import ewallet.domian.respository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Wallet wallet, Money amount, String type) {
        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setTransactionTime(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }




}
