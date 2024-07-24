package ewallet.domain.event;

public class TransactionCreatedEvent {
    private Long transactionId;

    public TransactionCreatedEvent(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }
}
