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
public class Withdrawal {
    @Id
    private String withdrawalId;
    private String walletId;
    private double amount;
    private String currency;
    private String status;
    private LocalDateTime createdAt;

    // Getters and Setters
}
