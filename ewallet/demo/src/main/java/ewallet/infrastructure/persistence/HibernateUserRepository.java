package ewallet.infrastructure.persistence;

import ewallet.domain.respository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class HibernateUserRepository implements UserRepository {
    // 实现持久化方法，例如使用JPA或Hibernate进行数据库操作
}
