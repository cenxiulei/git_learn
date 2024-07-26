package ewallet.mapper;

import ewallet.domian.model.User;
import ewallet.domian.model.Wallet;
import ewallet.domian.model.valueobject.Money;
import ewallet.dto.UserDTO;
import ewallet.dto.WalletDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    @Mapping(source = "balance", target = "balance", qualifiedByName = "moneyToBigDecimal")
    @Mapping(source = "user", target = "userDTO", qualifiedByName = "userToUserDTO")
    WalletDTO walletToWalletDTO(Wallet wallet);

    @Named("moneyToBigDecimal")
    static BigDecimal moneyToBigDecimal(Money money) {
        return money.getAmount();
    }

    @Named("userToUserDTO")
    static UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}

