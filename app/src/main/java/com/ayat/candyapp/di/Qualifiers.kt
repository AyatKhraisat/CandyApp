package com.ayat.candyapp.di

import javax.inject.Qualifier
import java.lang.annotation.RetentionPolicy
import kotlin.annotation.Retention

/**
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
interface Qualifiers {
    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ActivityContext

    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ApplicationContext

    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LoginViewModel
    //
    //    @Qualifier
    //    @Retention(RetentionPolicy.RUNTIME)
    //    public @interface EntryViewModel {
    //    }
    //
    //    @Qualifier
    //    @Retention(RetentionPolicy.RUNTIME)
    //    public @interface NavViewModel {
    //    }
    //
    //    @Qualifier
    //    @Retention(RetentionPolicy.RUNTIME)
    //    public @interface TaskViewModel {
    //    }
    //
    //    @Qualifier
    //    @Retention(RetentionPolicy.RUNTIME)
    //    public @interface AddTaskViewModel {
    //    }

}
