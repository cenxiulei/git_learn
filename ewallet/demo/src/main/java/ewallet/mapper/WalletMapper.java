package ewallet.mapper;

import ewallet.dto.WalletDTO;
import ewallet.domain.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {
    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    WalletDTO entityToDto(Wallet wallet);
    Wallet dtoToEntity(WalletDTO walletDTO);
}
