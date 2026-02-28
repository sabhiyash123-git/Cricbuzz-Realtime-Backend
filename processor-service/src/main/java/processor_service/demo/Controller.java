package processor_service.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Replaced specific imports with wildcards for clarity
import processor_service.demo.dto.MatchData;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/score")
// English Comment: Allow Angular (Port 4200) to access this API to avoid CORS errors
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CricketService cricketService;

    @GetMapping("/test")
    public String test() {
        return "Controller is Working!";
    }

    // English Comment: Fetch specific match score from Redis cache
    @GetMapping("/{matchId}")
    public Map<Object, Object> getScore(@PathVariable String matchId) {
        // Direct Redis fetch for high performance
        return redisTemplate.opsForHash().entries("match:" + matchId + ":score");
    }

    // English Comment: Fetch all categorized matches (Live and Past 7 Days)
    @GetMapping("/all")
    public ResponseEntity<Map<String, List<MatchData>>> getMatches() {
        // English Exception: Ensure service logic handles external API failures gracefully
        return ResponseEntity.ok(cricketService.getCategorizedMatches());
    }
}