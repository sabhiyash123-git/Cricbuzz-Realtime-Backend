package processor_service.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/score")
public class ScoreController {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @GetMapping("/test")
    public String test() {
        return "Controller is Working!";
    }

    @GetMapping("/{matchId}")
    public Map<Object, Object> getScore(@PathVariable String matchId) {
        // Hum direct Redis se data uthayenge kyunki ye fast hai
        return redisTemplate.opsForHash().entries("match:" + matchId + ":score");




    }
}
