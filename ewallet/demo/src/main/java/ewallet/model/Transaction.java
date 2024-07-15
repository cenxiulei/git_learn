package ewallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    private String transactionId;
    private String fromWalletId;
    private String toWalletId;
    private double amount;
    private String currency;
    private String status;
    private LocalDateTime createdAt;


}
