package ewallet.infrastructure.persistence;

import ewallet.domain.respository.TransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;



@Repository
public abstract class HibernateTransactionRepository implements TransactionRepository {
    // 可以在这里实现一些通用的方法，但不能实现 flush()
}
