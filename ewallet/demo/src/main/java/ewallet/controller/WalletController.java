package ewallet.controller;

import ewallet.dto.WalletDto;
import ewallet.model.Wallet;
import ewallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping
    public ResponseEntity<?> createWallet(@RequestBody WalletDto walletDto) {
        return ResponseEntity.ok(walletService.createWallet(walletDto));
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<?> getWallet(@PathVariable String walletId) {
        return ResponseEntity.ok(walletService.getWallet(walletId));
    }
}
