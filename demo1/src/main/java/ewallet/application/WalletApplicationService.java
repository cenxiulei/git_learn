package ewallet.application;

import ewallet.domian.model.Wallet;
import ewallet.domian.model.valueobject.Money;
import ewallet.domian.service.WalletService;
import ewallet.dto.WalletDTO;
import ewallet.mapper.WalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletApplicationService {

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletMapper walletMapper;

    public WalletDTO createWallet(Long userId) {
        Wallet wallet = walletService.createWallet(userId); // 为用户创建钱包
        return walletMapper.walletToWalletDTO(wallet);
    }

    public List<WalletDTO> getWallets() {
        List<Wallet> wallets = walletService.getAllWallets();
        return wallets.stream()
                .map(walletMapper::walletToWalletDTO)
                .collect(Collectors.toList());
    }

    public WalletDTO updateWallet(WalletDTO walletDTO) {
        Wallet wallet = walletService.updateWallet(walletDTO);
        return walletMapper.walletToWalletDTO(wallet);
    }

    public void deleteWallet(Long walletId) {
        walletService.deleteWallet(walletId);
    }

    public WalletDTO deposit(Long walletId, Money amount) {
        Wallet wallet = walletService.getWalletById(walletId); // 通过 WalletService 获取钱包
        Wallet updatedWallet = walletService.deposit(wallet, amount);
        return walletMapper.walletToWalletDTO(updatedWallet);
    }

    public WalletDTO withdraw(Long walletId, Money amount) {
        Wallet wallet = walletService.getWalletById(walletId); // 通过 WalletService 获取钱包
        Wallet updatedWallet = walletService.withdraw(wallet, amount);
        return walletMapper.walletToWalletDTO(updatedWallet);
    }

    public void transferMoney(Long sourceWalletId, Long targetWalletId, BigDecimal amount) {
        walletService.transferMoney(sourceWalletId, targetWalletId, new Money(amount));
    }

}
