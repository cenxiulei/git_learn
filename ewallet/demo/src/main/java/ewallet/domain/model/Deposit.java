package ewallet.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "deposit")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Deposit {
    @Id
    private String depositId;
    private String walletId;
    private double amount;
    private String currency;
    private String status;
    private LocalDateTime createdAt;


}