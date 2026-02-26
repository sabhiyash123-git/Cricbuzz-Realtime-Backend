package processor_service.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import processor_service.demo.dto.MatchData;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CricketService {
    @Autowired
    private RestTemplate restTemplate;

    private final String API_URL = "https://api.cricapi.com/v1/currentMatches?apikey=99d0f134-cd3a-4b67-9573-c0c148488bf9&offset=0";

    public Map<String, List<MatchData>> getCategorizedMatches() {
        // External API Call
        Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);
        List<Map<String, Object>> rawData = (List<Map<String, Object>>) response.get("data");

        // ObjectMapper use karke map ko list of DTOs mein convert karo
        ObjectMapper mapper = new ObjectMapper();
        List<MatchData> allMatches = mapper.convertValue(rawData, new TypeReference<List<MatchData>>() {});

        // Logic to filter
        List<MatchData> live = allMatches.stream()
                .filter(m -> m.isMatchStarted() && !m.isMatchEnded())
                .collect(Collectors.toList());

        List<MatchData> history = allMatches.stream()
                .filter(MatchData::isMatchEnded)
                .collect(Collectors.toList());

        Map<String, List<MatchData>> result = new HashMap<>();
        result.put("live", live);
        result.put("history", history);

        return result;
    }



    // English Comment: Filter matches that ended within the last 7 days
    public List<MatchData> getPastWeekHistory(List<MatchData> allMatches) {
        long sevenDaysAgo = System.currentTimeMillis() - (7L * 24 * 60 * 60 * 1000);

        return allMatches.stream()
                .filter(MatchData::isMatchEnded)
                // English Exception: Ensure date parsing doesn't fail if format is unexpected
                .filter(m -> {
                    try {
                        long matchDate = Long.parseLong(m.getDate()); // Assuming date is a timestamp string
                        return matchDate >= sevenDaysAgo;
                    } catch (Exception e) {
                        System.err.println("Error parsing match date: " + e.getMessage());
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }



}