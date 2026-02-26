package processor_service.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchData {
    private String id;
    private String name;
    private String matchType;
    private String status;
    private String venue;
    private String date;
    private List<TeamInfo> teamInfo;
    private List<Score> score;
    private boolean matchStarted;
    private boolean matchEnded;
}

@Data
class TeamInfo {
    private String name;
    private String shortname;
    private String img;
}

@Data
class Score {
    private int r; // Runs
    private int w; // Wickets
    private double o; // Overs
    private String inning;
}