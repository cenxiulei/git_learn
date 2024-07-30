package ewallet.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferRequestDTO {
    private Long sourceWalletId;
    private Long targetWalletId;
    private BigDecimal amount;
}