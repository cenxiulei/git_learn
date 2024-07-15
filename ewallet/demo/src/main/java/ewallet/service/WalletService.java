package ewallet.service;

import ewallet.model.Wallet;
import ewallet.respository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createWallet(String userId, String currency) {
        Wallet wallet = new Wallet();
        wallet.setWalletId(UUID.randomUUID().toString());
        wallet.setUserId(userId);
        wallet.setCurrency(currency);
        wallet.setBalance(0.0);
        wallet.setCreatedAt(LocalDateTime.now());
        return walletRepository.save(wallet);
    }

    public Wallet getWallet(String walletId) {
        return walletRepository.findById(walletId).orElse(null);
    }
}
