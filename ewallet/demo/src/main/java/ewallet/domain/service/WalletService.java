package ewallet.domain.service;


import ewallet.domain.model.Wallet;
import ewallet.domain.respository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet getWallet(String id) {
        return walletRepository.findById(id).orElse(null);
    }
}
