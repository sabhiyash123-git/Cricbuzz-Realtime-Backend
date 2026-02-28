package processor_service.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class MatchProcessor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private BallRepository ballRepository; // Naya addition: DB ke liye

    @KafkaListener(topics = "raw-ball-updates", groupId = "cric-processor-group")
    public void consume(String message) {
        // Maan lo message 1 run ki ball hai
        int runsFromMessage = 1;
        int matchId = 101;

        // 1. UPDATE POSTGRES (History save karo)
        Entity ball = new Entity();
        ball.setMatchId(matchId);
        ball.setRuns(runsFromMessage);
        ball.setTimestamp(LocalDateTime.now());
        ballRepository.save(ball);

        // 2. UPDATE REDIS (Live Score badhao)
        String key = "match:" + matchId + ":score";
        redisTemplate.opsForHash().increment(key, "total_runs", runsFromMessage);

        System.out.println("Kafka message processed! Saved to DB & Redis Updated.");
    }
}