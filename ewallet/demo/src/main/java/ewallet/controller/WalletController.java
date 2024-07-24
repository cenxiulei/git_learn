package ewallet.controller;

import ewallet.domain.model.Wallet;
import ewallet.domain.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ewallet.application.WalletApplicationService;
import ewallet.dto.WalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletApplicationService walletApplicationService;

    @PostMapping
    public ResponseEntity<WalletDTO> createWallet(@RequestBody WalletDTO walletDTO) {
        WalletDTO createdWallet = walletApplicationService.createWallet(walletDTO);
        return ResponseEntity.ok(createdWallet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDTO> getWallet(@PathVariable String id) {
        WalletDTO wallet = walletApplicationService.getWallet(id);
        return ResponseEntity.ok(wallet);
    }
}

