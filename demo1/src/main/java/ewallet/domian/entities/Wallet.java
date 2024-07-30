package ewallet.domian.entities;

import ewallet.domian.entities.valueobject.Money;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Money balance; // 确保 Money 是 @Embeddable 类型

    private LocalDateTime createdAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> transactions; // 交易记录
}
