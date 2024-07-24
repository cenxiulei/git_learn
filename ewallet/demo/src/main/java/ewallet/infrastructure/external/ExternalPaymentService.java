package ewallet.infrastructure.external;

public interface ExternalPaymentService {
    boolean processPayment(Long userId, Double amount);
}
