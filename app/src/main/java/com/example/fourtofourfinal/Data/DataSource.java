package com.example.fourtofourfinal.Data;



import com.example.fourtofourfinal.Model.PairModel;
import com.example.fourtofourfinal.Model.QuestionModel;
import com.example.fourtofourfinal.Model.UserData;

import java.util.ArrayList;



/**
 * Created by Rick on 3/6/2018.
 */

public interface DataSource {

    interface Local{

        ArrayList<PairModel> getPairs();

        QuestionModel getRandomQuestionObj();

        ArrayList<String> getAnswer();
    }

    interface Remote {

        //FirebaseDatabase getDatabaseInstance();

        void setNewLanguage(String language);

        void setDailyXp(int xp);

        void setUserTotalXp(int xp);

        void setLastTimeVisited();

        void setDailyGoal(int dailyGoal);

        void setUserInfo(UserData userData);

        void setLessonComplete(String lesson, boolean completeness);

        void setLessonCompleteDate(String lesson);

        void getDailyGoal();

        void getDailyXp();

        void getWeekXp();

        void getLessonCompleted();
    }
}
