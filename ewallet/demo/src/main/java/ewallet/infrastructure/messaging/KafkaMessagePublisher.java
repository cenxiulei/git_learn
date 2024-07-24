package ewallet.infrastructure.messaging;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagePublisher implements MessagePublisher {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(String message) {
        kafkaTemplate.send("topic-name", message);
    }
}