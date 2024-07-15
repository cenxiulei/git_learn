package ewallet.service;

import ewallet.dto.FundDto;
import ewallet.model.Deposit;
import ewallet.model.Withdrawal;
import ewallet.respository.DepositRepository;
import ewallet.respository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FundService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    public Deposit createDeposit(FundDto fundDto) {
        Deposit deposit = new Deposit();
        deposit.setWalletId(fundDto.getWalletId());
        deposit.setAmount(fundDto.getAmount());
        deposit.setCurrency(fundDto.getCurrency());
        deposit.setStatus("COMPLETED");
        return depositRepository.save(deposit);
    }

    public Withdrawal createWithdrawal(FundDto fundDto) {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setWalletId(fundDto.getWalletId());
        withdrawal.setAmount(fundDto.getAmount());
        withdrawal.setCurrency(fundDto.getCurrency());
        withdrawal.setStatus("COMPLETED");
        return withdrawalRepository.save(withdrawal);
    }
}
