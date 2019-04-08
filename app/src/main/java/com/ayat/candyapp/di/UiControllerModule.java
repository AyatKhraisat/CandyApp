package com.ayat.candyapp.di;

import android.content.Context;
import com.blessedtreeit.product.activitycenter.flows.tasks.TasksFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Ayat khraisat  on 12/18/18
 * akhraisat@blessedtreeit.com
 * <p>
 * Project Name: PocMvp
 * <p>
 * Blessed Tree IT
 */

@Module
public class UiControllerModule {

    private Context context;

    public UiControllerModule(Context context) {
        this.context = context;
    }

    @Provides
    @Qualifiers.ActivityContext
    @ViewScope
    Context provideContext() {
        return context;
    }

    @Provides
    @ViewScope
    TasksFragment provideTasksFragment() {
        return new TasksFragment();
    }

}
