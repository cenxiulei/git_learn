package ewallet.domian.entities;

import ewallet.domian.entities.valueobject.Money;
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

    @Embedded
    private Money amount; // 确保 Money 是 @Embeddable 类型

    private String type; // e.g., credit, debit

    private Long targetWalletId; // 用于转账时指定目标钱包
}
