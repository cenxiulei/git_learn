package ewallet.controller;

import ewallet.application.WalletApplicationService;
import ewallet.domian.model.User;
import ewallet.domian.model.Wallet;
import ewallet.domian.model.valueobject.Money;
import ewallet.dto.WalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletApplicationService walletApplicationService;

    @GetMapping
    public List<WalletDTO> getWallets() {
        return walletApplicationService.getWallets();
    }

    @PostMapping
    public ResponseEntity<WalletDTO> createWallet(@RequestParam Long userId) {
        WalletDTO walletDTO = walletApplicationService.createWallet(userId);
        return ResponseEntity.ok(walletDTO);
    }

    @PostMapping("/{walletId}/deposit")
    public ResponseEntity<WalletDTO> deposit(@PathVariable Long walletId,
                                             @RequestParam BigDecimal amount) {
        WalletDTO walletDTO = walletApplicationService.deposit(walletId, new Money(amount));
        return ResponseEntity.ok(walletDTO);
    }

    @PostMapping("/{walletId}/withdraw")
    public ResponseEntity<WalletDTO> withdraw(@PathVariable Long walletId,
                                              @RequestParam BigDecimal amount) {
        WalletDTO walletDTO = walletApplicationService.withdraw(walletId, new Money(amount));
        return ResponseEntity.ok(walletDTO);
    }

    @PostMapping("/{sourceWalletId}/transfer/{targetWalletId}")
    public ResponseEntity<String> transferMoney(
            @PathVariable Long sourceWalletId,
            @PathVariable Long targetWalletId,
            @RequestParam BigDecimal amount) {
        walletApplicationService.transferMoney(sourceWalletId, targetWalletId, amount);
        return ResponseEntity.ok("Transfer successful");
    }
}