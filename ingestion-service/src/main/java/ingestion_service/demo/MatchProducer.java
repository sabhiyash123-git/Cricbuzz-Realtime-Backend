package ingestion_service.demo; // Folder ke hisaab se package change kiya

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MatchProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "raw-ball-updates";

    public void sendBall(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}