package com.ayat.candyapp.di;

import android.content.Context;

import com.ayat.candyapp.App;
import com.ayat.candyapp.UserPreferences;
import com.ayat.candyapp.login.UserManagementRepository;
import com.ayat.candyapp.network.EndPoint;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(App app);

    @Qualifiers.ApplicationContext
    Context context();

    EndPoint getEndPoint();

    UserPreferences getUserPreferences();

    UserManagementRepository getUserManagementRepository();
}
