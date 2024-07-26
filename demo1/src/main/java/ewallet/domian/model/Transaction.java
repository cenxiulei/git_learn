package ewallet.domian.model;

import ewallet.domian.model.valueobject.Money;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    private LocalDateTime transactionTime;
    private Money amount;
    private String type; // e.g., credit, debit
    private Long targetWalletId;
}
