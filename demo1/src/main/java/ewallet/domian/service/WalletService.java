package ewallet.domian.service;

import ewallet.domian.model.Transaction;
import ewallet.domian.model.User;
import ewallet.domian.model.Wallet;
import ewallet.domian.model.valueobject.Money;
import ewallet.domian.respository.TransactionRepository;
import ewallet.domian.respository.WalletRepository;
import ewallet.dto.WalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;

    public Wallet createWallet(Long userId) {
        User user = userService.getUserById(userId);
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(new Money(BigDecimal.ZERO));
        wallet.setCreatedAt(LocalDateTime.now());
        return walletRepository.save(wallet);
    }

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet getWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + walletId));
    }

    public Wallet updateWallet(WalletDTO walletDTO) {
        Wallet wallet = walletRepository.findById(walletDTO.getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
        return walletRepository.save(wallet);
    }

    public void deleteWallet(Long walletId) {
        walletRepository.deleteById(walletId);
    }


    public Wallet deposit(Wallet wallet, Money amount) {
        wallet.setBalance(new Money(wallet.getBalance().getAmount().add(amount.getAmount())));
        return walletRepository.save(wallet);
    }

    public Wallet withdraw(Wallet wallet, Money amount) {
        if (wallet.getBalance().getAmount().compareTo(amount.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        wallet.setBalance(new Money(wallet.getBalance().getAmount().subtract(amount.getAmount())));
        return walletRepository.save(wallet);
    }

    @Transactional
    public void transferMoney(Long sourceWalletId, Long targetWalletId, Money amount) {
        Wallet sourceWallet = walletRepository.findById(sourceWalletId)
                .orElseThrow(() -> new IllegalArgumentException("Source wallet not found"));
        Wallet targetWallet = walletRepository.findById(targetWalletId)
                .orElseThrow(() -> new IllegalArgumentException("Target wallet not found"));

        if (sourceWallet.getBalance().getAmount().compareTo(amount.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance in source wallet");
        }

        // 扣减源钱包余额
        sourceWallet.setBalance(new Money(sourceWallet.getBalance().getAmount().subtract(amount.getAmount())));
        walletRepository.save(sourceWallet);

        // 增加目标钱包余额
        targetWallet.setBalance(new Money(targetWallet.getBalance().getAmount().add(amount.getAmount())));
        walletRepository.save(targetWallet);

        // 创建转账交易记录
        Transaction sourceTransaction = new Transaction();
        sourceTransaction.setWallet(sourceWallet);
        sourceTransaction.setTransactionTime(LocalDateTime.now());
        sourceTransaction.setAmount(amount);
        sourceTransaction.setType("debit");
        sourceTransaction.setTargetWalletId(targetWalletId);
        transactionRepository.save(sourceTransaction);

        Transaction targetTransaction = new Transaction();
        targetTransaction.setWallet(targetWallet);
        targetTransaction.setTransactionTime(LocalDateTime.now());
        targetTransaction.setAmount(amount);
        targetTransaction.setType("credit");
        targetTransaction.setTargetWalletId(sourceWalletId);
        transactionRepository.save(targetTransaction);
    }
}
