package ewallet.mapper;

import ewallet.dto.TransactionDTO;
import ewallet.domain.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDTO entityToDto(Transaction transaction);
    Transaction dtoToEntity(TransactionDTO transactionDTO);
}
