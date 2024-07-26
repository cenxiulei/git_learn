package ewallet.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WalletDTO {
    private Long id;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private UserDTO userDTO;
}