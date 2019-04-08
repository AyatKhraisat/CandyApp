package com.ayat.candyapp;


import android.app.Application;
import com.blessedtreeit.product.activitycenter.di.ApplicationComponent;
import com.blessedtreeit.product.activitycenter.di.ApplicationModule;
import com.blessedtreeit.product.activitycenter.di.DaggerApplicationComponent;
import com.blessedtreeit.product.activitycenter.di.NetworkModule;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import timber.log.Timber;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        createComponent();

        Stetho.initializeWithDefaults(this);

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    protected void createComponent() {
        applicationComponent =
                DaggerApplicationComponent.builder()
                        .networkModule(new NetworkModule("http://dev5.cloudapp.net/ActivityCenterAPI/"))
                        .applicationModule(new ApplicationModule(this))
                        .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
