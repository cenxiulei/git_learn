package ewallet.application;

import ewallet.domain.model.Transaction;
import ewallet.domain.service.TransactionService;
import ewallet.dto.TransactionDTO;
import ewallet.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionApplicationService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionMapper transactionMapper;

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.dtoToEntity(transactionDTO);
        return transactionMapper.entityToDto(transactionService.createTransaction(transaction));
    }

    public TransactionDTO getTransaction(Long id) {
        return transactionMapper.entityToDto(transactionService.getTransaction(id));
    }
}
