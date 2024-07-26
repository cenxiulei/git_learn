package ewallet.domian.model.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Embeddable
public class Money {
    BigDecimal amount;
    public Money() {
        this.amount = BigDecimal.ZERO;
    }

    public Money(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        this.amount = amount;
    }
}