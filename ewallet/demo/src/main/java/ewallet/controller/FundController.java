package ewallet.controller;

import ewallet.model.Deposit;
import ewallet.model.Withdrawal;
import ewallet.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funds")
public class FundController {

    @Autowired
    private FundService fundService;

    @PostMapping("/deposit")
    public Deposit createDeposit(@RequestParam String walletId, @RequestParam double amount, @RequestParam String currency) {
        return fundService.createDeposit(walletId, amount, currency);
    }

    @PostMapping("/withdrawal")
    public Withdrawal createWithdrawal(@RequestParam String walletId, @RequestParam double amount, @RequestParam String currency) {
        return fundService.createWithdrawal(walletId, amount, currency);
    }
}
