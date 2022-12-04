package com.jkutkut.qatar_wold_cup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jkutkut.qatar_wold_cup.data.MatchResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    // ********* ARGS *********
    private static final String ARG_PHASE = "phase";
    private static final String ARG_DATETIME = "datetime";
    private static final String ARG_TEAM1 = "team1";
    private static final String ARG_TEAM2 = "team2";
    private static final String ARG_GOALS1 = "goals1";
    private static final String ARG_GOALS2 = "goals2";

    private String phase;
    private String datetime;
    private String team1;
    private String team2;
    private String goals1;
    private String goals2;

    // ********* UI Components *********
    private TextView txtvPhase;
    private TextView txtvDateTime;
    private TextView txtvTeam1;
    private TextView txtvTeam2;
    private TextView txtvScore1;
    private TextView txtvScore2;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment with the provided
     * match result.
     *
     * @param matchResult MathResult object to be displayed.
     * @return A new instance of fragment ResultFragment.
     */
    public static ResultFragment newInstance(MatchResult matchResult) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PHASE, matchResult.getPhase());
        args.putString(ARG_DATETIME, matchResult.getDate());
        args.putString(ARG_TEAM1, matchResult.getTeam1());
        args.putString(ARG_TEAM2, matchResult.getTeam2());
        args.putString(ARG_GOALS1, String.valueOf(matchResult.getScore1()));
        args.putString(ARG_GOALS2, String.valueOf(matchResult.getScore2()));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            phase = getArguments().getString(ARG_PHASE);
            datetime = getArguments().getString(ARG_DATETIME);
            team1 = getArguments().getString(ARG_TEAM1);
            team2 = getArguments().getString(ARG_TEAM2);
            goals1 = getArguments().getString(ARG_GOALS1);
            goals2 = getArguments().getString(ARG_GOALS2);
        }
        else {
            phase = "DEBUG"; // TODO remove this
            datetime = "";
            team1 = "";
            team2 = "";
            goals1 = "";
            goals2 = "";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        // ********* UI Components *********
        txtvPhase = view.findViewById(R.id.txtvPhase);
        txtvDateTime = view.findViewById(R.id.txtvDateTime);
        txtvTeam1 = view.findViewById(R.id.txtvTeam1);
        txtvTeam2 = view.findViewById(R.id.txtvTeam2);
        txtvScore1 = view.findViewById(R.id.txtvScore1);
        txtvScore2 = view.findViewById(R.id.txtvScore2);

        // ********* Set Text *********
        txtvPhase.setText(phase);
        txtvDateTime.setText(datetime);
        txtvTeam1.setText(team1);
        txtvTeam2.setText(team2);
        txtvScore1.setText(goals1);
        txtvScore2.setText(goals2);
        return view;
    }
}