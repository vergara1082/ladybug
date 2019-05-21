package com.example.fourtofourfinal.Utils;


import com.example.fourtofourfinal.Data.Local.QuestionDataSource;
import com.example.fourtofourfinal.Data.Repository;

/**
 * Created by Rick on 3/6/2018.
 */

public class Injection {

    public static Repository provideRepository() {

        return Repository.getInstance(
                QuestionDataSource.getInstance()/*,
                FirebaseDatabaseHelper.getHelperInstance()*/);
    }

/*    public static FirebaseAuthHelper providesAuthHelper() {

        return FirebaseAuthHelper.getClassInstance();
    }  */
}
