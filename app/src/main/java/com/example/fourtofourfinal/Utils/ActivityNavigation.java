package com.example.fourtofourfinal.Utils;

import android.content.Context;
import android.content.Intent;

import com.example.fourtofourfinal.Presentation.Tasks.TapPairTask.TapPairActivity;
import com.example.fourtofourfinal.Presentation.Tasks.TranslateSentenceTask.TSTaskActivity;
import com.example.fourtofourfinal.Presentation.Tasks.WordTask.WordTaskActivity;

import java.util.ArrayList;
import java.util.Random;



/**
 * Created by Rick on 3/2/2018.
 */

public class ActivityNavigation {

    static ActivityNavigation INSTANCE;

    Context context;

    ArrayList<Class> activities = new ArrayList<>();

    Random random = new Random();

    public ActivityNavigation(Context context) {

        this.context = context;

        initData();
    }

    public static ActivityNavigation getInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = new ActivityNavigation(context);
        }

        return INSTANCE;
    }

    private void initData() {

        activities.add(WordTaskActivity.class);
        activities.add(TSTaskActivity.class);
        activities.add(TapPairActivity.class);
    }

    public void takeToRandomTask() {

        int randomIndex = random.nextInt(activities.size());

        Intent intent = new Intent(context, activities.get(randomIndex));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    /*public void lessonCompleted() {

        Intent intent = new Intent(context, LessonCompletedActivity.class);
        context.startActivity(intent);
    }*/
}
