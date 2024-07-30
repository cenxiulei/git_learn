package ewallet.controller;

import ewallet.application.WalletApplicationService;
import ewallet.domian.model.valueobject.Money;
import ewallet.dto.WalletDTO;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "获取所有钱包信息")
    @GetMapping
    public List<WalletDTO> getWallets() {
        return walletApplicationService.getWallets();
    }

    @Operation(summary = "创建钱包" , description = "根据用户ID创建对应的钱包")
    @PostMapping
    public ResponseEntity<WalletDTO> createWallet(@RequestParam Long userId) {
        WalletDTO walletDTO = walletApplicationService.createWallet(userId);
        return ResponseEntity.ok(walletDTO);
    }

    @Operation(summary = "更新钱包信息")
    @PutMapping("/{walletId}")
    public ResponseEntity<WalletDTO> updateWallet(@PathVariable Long walletId, @RequestBody WalletDTO walletDTO) {
        walletDTO.setId(walletId);
        WalletDTO updatedWallet = walletApplicationService.updateWallet(walletDTO);
        return ResponseEntity.ok(updatedWallet);
    }

    @Operation(summary = "删除钱包")
    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long walletId) {
        walletApplicationService.deleteWallet(walletId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "存款")
    @PostMapping("/{walletId}/deposit")
    public ResponseEntity<WalletDTO> deposit(@PathVariable Long walletId,
                                             @RequestParam BigDecimal amount) {
        WalletDTO walletDTO = walletApplicationService.deposit(walletId, new Money(amount));
        return ResponseEntity.ok(walletDTO);
    }

    @Operation(summary = "取款")
    @PostMapping("/{walletId}/withdraw")
    public ResponseEntity<WalletDTO> withdraw(@PathVariable Long walletId,
                                              @RequestParam BigDecimal amount) {
        WalletDTO walletDTO = walletApplicationService.withdraw(walletId, new Money(amount));
        return ResponseEntity.ok(walletDTO);
    }

    @Operation(summary = "转账")
    @PostMapping("/{sourceWalletId}/transfer/{targetWalletId}")
    public ResponseEntity<String> transferMoney(
            @PathVariable Long sourceWalletId,
            @PathVariable Long targetWalletId,
            @RequestParam BigDecimal amount) {
        walletApplicationService.transferMoney(sourceWalletId, targetWalletId, amount);
        return ResponseEntity.ok("Transfer successful");
    }
}