package ewallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wallet")
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
