package ewallet.controller;

import ewallet.dto.FundDto;
import ewallet.model.Deposit;
import ewallet.model.Withdrawal;
import ewallet.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funds")
public class FundController {

    @Autowired
    private FundService fundService;

    @PostMapping("/deposits")
    public ResponseEntity<?> createDeposit(@RequestBody FundDto fundDto) {
        return ResponseEntity.ok(fundService.createDeposit(fundDto));
    }

    @PostMapping("/withdrawals")
    public ResponseEntity<?> createWithdrawal(@RequestBody FundDto fundDto) {
        return ResponseEntity.ok(fundService.createWithdrawal(fundDto));
    }
}
