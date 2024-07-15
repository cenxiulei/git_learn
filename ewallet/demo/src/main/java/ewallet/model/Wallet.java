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
public class Wallet {
    @Id
    private String walletId;
    private String userId;
    private double balance;
    private String currency;
    private LocalDateTime createdAt;

    // Getters and Setters
}
