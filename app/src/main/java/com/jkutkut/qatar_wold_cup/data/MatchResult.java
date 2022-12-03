package com.jkutkut.qatar_wold_cup.data;

/**
 * JavaBean class to store the result of a match.
 *
 * Note: All data validation should be done elsewhere. This class is only responsible
 * for storing the data.
 *
 * @author jkutkut
 */
public class MatchResult {

    private String phase;
    private final String team1;
    private final String team2;
    private final int score1;
    private final int score2;
    private final String date;

    public MatchResult(String phase, String team1, String team2, int score1, int score2, String date) {
        this.phase = phase;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.date = date;
    }

    public String getPhase() {
        return phase;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", date='" + date + '\'' +
                '}';
    }
}
