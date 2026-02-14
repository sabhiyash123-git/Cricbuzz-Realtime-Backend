package ingestion_service.demo; // Folder path se match hona chahiye

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class BallUpdateJob extends QuartzJobBean {

    @Autowired
    private MatchProducer matchProducer; // Ab ye same package mein hai toh error nahi dega

    @Override
    protected void executeInternal(JobExecutionContext context) {
        // Random runs generate kar rahe hain (0 to 6)
        int runs = (int) (Math.random() * 7);
        String mockBall = "{\"matchId\": 101, \"runs\": " + runs + ", \"isWicket\": false}";

        matchProducer.sendBall(mockBall);
        System.out.println("Data sent to Kafka: " + mockBall);
    }
}