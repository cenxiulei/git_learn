package ewallet.dto;

import lombok.Data;

@Data
public class FundDto {
    private String walletId;
    private double amount;
    private String currency;
}
