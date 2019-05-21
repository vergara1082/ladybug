package com.example.fourtofourfinal.Data;

import android.support.annotation.NonNull;


import com.example.fourtofourfinal.Model.PairModel;
import com.example.fourtofourfinal.Model.QuestionModel;
import com.example.fourtofourfinal.Model.UserData;

import java.util.ArrayList;




public class Repository implements DataSource.Local{

    private static Repository INSTANCE;

    private final DataSource.Local dataSourceLocal;
   // private final DataSource.Remote dataSourceRemote;

    private Repository(
            @NonNull DataSource.Local questionDataSource
            /*@NonNull DataSource.Remote dataSourceRemote*/) {
        this.dataSourceLocal = questionDataSource;
        //this.dataSourceRemote = dataSourceRemote;

    }

    public static Repository getInstance(
            DataSource.Local dataSourceLocal
            /*DataSource.Remote dataSourceRemote*/) {

        if (INSTANCE == null) {

            INSTANCE = new Repository(dataSourceLocal/*, dataSourceRemote*/);
           // dataSourceRemote.getDatabaseInstance();
        }

        return INSTANCE;
    }

    //@Override
    /*public FirebaseDatabase getDatabaseInstance() {
        return dataSourceRemote.getDatabaseInstance();
    }*/

    @Override
    public ArrayList<PairModel> getPairs() {
        return dataSourceLocal.getPairs();
    }

    @Override
    public QuestionModel getRandomQuestionObj() {
        return dataSourceLocal.getRandomQuestionObj();
    }

    @Override
    public ArrayList<String> getAnswer() {
        return dataSourceLocal.getAnswer();
    }


}
