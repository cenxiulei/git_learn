package ewallet.application;

import ewallet.domian.entities.valueobject.Money;
import ewallet.domian.respository.WalletRepository;
import ewallet.domian.service.TransactionService;
import ewallet.dto.TransactionDTO;
import ewallet.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionApplicationService {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    public TransactionDTO createTransaction(Long walletId, Money amount, String type) {
        var wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
        var transaction = transactionService.createTransaction(wallet, amount, type);
        return transactionMapper.transactionToTransactionDTO(transaction);
    }




}
