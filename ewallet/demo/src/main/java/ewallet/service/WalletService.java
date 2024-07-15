package ewallet.service;

import ewallet.dto.WalletDto;
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

    public Wallet createWallet(WalletDto walletDto) {
        Wallet wallet = new Wallet();
        wallet.setUserId(walletDto.getUserId());
        wallet.setCurrency(walletDto.getCurrency());
        wallet.setBalance(0.0);
        return walletRepository.save(wallet);
    }

    public Wallet getWallet(String walletId) {
        return walletRepository.findById(walletId).orElse(null);
    }
}
