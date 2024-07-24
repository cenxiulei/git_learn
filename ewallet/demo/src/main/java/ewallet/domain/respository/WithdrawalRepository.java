package ewallet.domain.respository;

import ewallet.domain.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, String> {
}
