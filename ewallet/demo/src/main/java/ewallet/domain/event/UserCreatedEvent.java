package ewallet.domain.event;

public class UserCreatedEvent {
    private Long userId;

    public UserCreatedEvent(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
