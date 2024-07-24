package ewallet.infrastructure.messaging;

public interface MessagePublisher {
    void publish(String message);
}