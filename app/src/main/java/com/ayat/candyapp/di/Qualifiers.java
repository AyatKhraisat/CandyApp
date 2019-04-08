package com.ayat.candyapp.di;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by: Ahmed Al-Hashimi
 * Created on: Dec. 27, 2018.
 * <p>
 * aalhashimi@blessedtreeit.com
 * <p>
 * Project Name: ActivityCenterProduct
 * <p>
 * BTIT
 */
public interface Qualifiers {
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ActivityContext {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ApplicationContext {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LoginViewModel {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface EntryViewModel {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NavViewModel {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TaskViewModel {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface AddTaskViewModel {
    }

}
