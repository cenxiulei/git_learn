package ewallet.service;

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

    public Deposit createDeposit(String walletId, double amount, String currency) {
        Deposit deposit = new Deposit();
        deposit.setDepositId(UUID.randomUUID().toString());
        deposit.setWalletId(walletId);
        deposit.setAmount(amount);
        deposit.setCurrency(currency);
        deposit.setStatus("COMPLETED");
        deposit.setCreatedAt(LocalDateTime.now());
        return depositRepository.save(deposit);
    }

    public Withdrawal createWithdrawal(String walletId, double amount, String currency) {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setWithdrawalId(UUID.randomUUID().toString());
        withdrawal.setWalletId(walletId);
        withdrawal.setAmount(amount);
        withdrawal.setCurrency(currency);
        withdrawal.setStatus("COMPLETED");
        withdrawal.setCreatedAt(LocalDateTime.now());
        return withdrawalRepository.save(withdrawal);
    }
}
