package ewallet.controller;

import ewallet.model.Wallet;
import ewallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping
    public Wallet createWallet(@RequestParam String userId, @RequestParam String currency) {
        return walletService.createWallet(userId, currency);
    }

    @GetMapping("/{walletId}")
    public Wallet getWallet(@PathVariable String walletId) {
        return walletService.getWallet(walletId);
    }
}
