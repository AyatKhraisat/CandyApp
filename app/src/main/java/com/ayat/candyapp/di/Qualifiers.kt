package com.ayat.candyapp.di

import javax.inject.Qualifier
import java.lang.annotation.RetentionPolicy
import kotlin.annotation.Retention

/**
 * Created by: Ahmed Al-Hashimi
 * Created on: Dec. 27, 2018.
 *
 *
 * aalhashimi@blessedtreeit.com
 *
 *
 * Project Name: ActivityCenterProduct
 *
 *
 * BTIT
 */
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
