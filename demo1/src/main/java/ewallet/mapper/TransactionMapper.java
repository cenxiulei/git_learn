package ewallet.mapper;

import ewallet.domian.model.Transaction;
import ewallet.domian.model.valueobject.Money;
import ewallet.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "amount", target = "amount", qualifiedByName = "moneyToBigDecimal")
    @Mapping(source = "wallet.id", target = "walletId")
    TransactionDTO transactionToTransactionDTO(Transaction transaction);

    @Named("moneyToBigDecimal")
    static BigDecimal moneyToBigDecimal(Money money) {
        return money.getAmount();
    }
}
