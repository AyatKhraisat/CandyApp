package com.ayat.candyapp.di;

import android.content.Context;
import com.ayat.candyapp.App;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ApplicationModule {

    private App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Qualifiers.ApplicationContext
    @Singleton
    @Provides
    Context provideApplicationContext() {
        return application;
    }

}
