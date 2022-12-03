package com.jkutkut.qatar_wold_cup;

import android.app.Application;

import com.jkutkut.qatar_wold_cup.data.MatchResultList;

/**
 * This class is used to store the data of the application.
 * This way, all activities can access the same data.
 */
public class QatarApplication extends Application {

    private MatchResultList resultData;

    @Override
    public void onCreate() {
        super.onCreate();
        resultData = new MatchResultList();
    }

    // ********* Getters *********
    public MatchResultList getResultData() {
        return resultData;
    }
}
