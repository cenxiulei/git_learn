package ewallet.dto;

import ewallet.domian.model.valueobject.Money;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private Long walletId;
    private LocalDateTime transactionTime;
    private BigDecimal amount;
    private String type;
}