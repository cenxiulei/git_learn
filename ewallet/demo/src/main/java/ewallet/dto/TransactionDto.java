package ewallet.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private String fromWalletId;
    private String toWalletId;
    private double amount;
    private String currency;
}
