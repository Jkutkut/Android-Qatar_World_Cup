package com.jkutkut.qatar_wold_cup.data;

import android.content.Context;

import com.jkutkut.qatar_wold_cup.R;

import java.util.ArrayList;

/**
 * Class to handle multiple match results.
 *
 * @author jkutkut
 */
public class MatchResultList {
    ArrayList<MatchResult> matchResults;

    public MatchResultList() {
        matchResults = new ArrayList<>();
    }

    public void addResult(MatchResult result) {
        matchResults.add(result);
    }

    /**
     * Obtain all matches played by a team.
     * @param team Team to search for.
     * @return List of matches played by the team.
     */
    public ArrayList<MatchResult> getResultsByTeam(String team) {
        ArrayList<MatchResult> results = new ArrayList<>();
        for (MatchResult match : matchResults)
            if (match.teamPlayed(team))
                results.add(match);
        return results;
    }

    /**
     * Load all the matches into the list.
     * @param context Context to load the matches from.
     * Note: This method requires the context to obtain the translation of the strings.
     */
    public void loadData(Context context) {
        Object[][] data = {
            {R.string.match_phase_round_of_16, "20/11/2022 17:00", R.string.team_qatar, 0, R.string.team_ecuador, 2},
            {R.string.match_phase_round_of_16, "21/11/2022 14:00", R.string.team_uk, 6, R.string.team_iran, 2},
            {R.string.match_phase_round_of_16, "21/11/2022 17:00", R.string.team_senegal, 0, R.string.team_netherlands, 2},
            {R.string.match_phase_round_of_16, "21/11/2022 20:00", R.string.team_usa, 1, R.string.team_wales, 1},
            {R.string.match_phase_round_of_16, "22/11/2022 11:00", R.string.team_argentina, 1, R.string.team_saudi_arabia, 2},
            {R.string.match_phase_round_of_16, "22/11/2022 14:00", R.string.team_denmark, 0, R.string.team_tunisia, 0},
            {R.string.match_phase_round_of_16, "22/11/2022 17:00", R.string.team_mexico, 0, R.string.team_poland, 0},
            {R.string.match_phase_round_of_16, "22/11/2022 20:00", R.string.team_france, 4, R.string.team_australia, 1},
            {R.string.match_phase_round_of_16, "23/11/2022 11:00", R.string.team_morocco, 0, R.string.team_croatia, 0},
            {R.string.match_phase_round_of_16, "23/11/2022 14:00", R.string.team_germany, 1, R.string.team_japan, 2},
            {R.string.match_phase_round_of_16, "23/11/2022 17:00", R.string.team_spain, 7, R.string.team_costa_rica, 0},
            {R.string.match_phase_round_of_16, "23/11/2022 20:00", R.string.team_belgium, 1, R.string.team_canada, 0},
            {R.string.match_phase_round_of_16, "24/11/2022 11:00", R.string.team_switzerland, 1, R.string.team_cameroon, 0},
            {R.string.match_phase_round_of_16, "24/11/2022 14:00", R.string.team_uruguay, 0, R.string.team_south_korea, 0},
            {R.string.match_phase_round_of_16, "24/11/2022 17:00", R.string.team_portugal, 3, R.string.team_ghana, 2},
            {R.string.match_phase_round_of_16, "24/11/2022 20:00", R.string.team_brazil, 2, R.string.team_serbia, 0},
            {R.string.match_phase_round_of_16, "25/11/2022 11:00", R.string.team_wales, 0, R.string.team_iran, 2},
            {R.string.match_phase_round_of_16, "25/11/2022 14:00", R.string.team_qatar, 1, R.string.team_senegal, 3},
            {R.string.match_phase_round_of_16, "25/11/2022 17:00", R.string.team_netherlands, 1, R.string.team_ecuador, 1},
            {R.string.match_phase_round_of_16, "25/11/2022 20:00", R.string.team_uk, 0, R.string.team_usa, 0},
            {R.string.match_phase_round_of_16, "26/11/2022 11:00", R.string.team_tunisia, 0, R.string.team_australia, 1},
            {R.string.match_phase_round_of_16, "26/11/2022 14:00", R.string.team_poland, 2, R.string.team_saudi_arabia, 0},
            {R.string.match_phase_round_of_16, "26/11/2022 17:00", R.string.team_france, 2, R.string.team_denmark, 1},
            {R.string.match_phase_round_of_16, "26/11/2022 20:00", R.string.team_argentina, 2, R.string.team_mexico, 0},
            {R.string.match_phase_round_of_16, "27/11/2022 11:00", R.string.team_japan, 0, R.string.team_costa_rica, 1},
            {R.string.match_phase_round_of_16, "27/11/2022 14:00", R.string.team_belgium, 0, R.string.team_morocco, 2},
            {R.string.match_phase_round_of_16, "27/11/2022 17:00", R.string.team_croatia, 4, R.string.team_canada, 1},
            {R.string.match_phase_round_of_16, "27/11/2022 20:00", R.string.team_spain, 1, R.string.team_germany, 1},
            {R.string.match_phase_round_of_16, "28/11/2022 11:00", R.string.team_cameroon, 3, R.string.team_serbia, 3},
            {R.string.match_phase_round_of_16, "28/11/2022 14:00", R.string.team_south_korea, 2, R.string.team_ghana, 3},
            {R.string.match_phase_round_of_16, "28/11/2022 17:00", R.string.team_brazil, 1, R.string.team_switzerland, 0},
            {R.string.match_phase_round_of_16, "28/11/2022 20:00", R.string.team_portugal, 2, R.string.team_uruguay, 0}
        };
        for (Object[] row : data) {
            matchResults.add(new MatchResult(
                context.getString((Integer) row[0]),
                context.getString((Integer) row[2]),
                context.getString((Integer) row[4]),
                (Integer) row[3],
                (Integer) row[5],
                (String) row[1]
            ));
        }
    }
}
