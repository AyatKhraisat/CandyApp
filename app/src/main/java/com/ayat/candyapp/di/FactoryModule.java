package com.ayat.candyapp.di;

import androidx.lifecycle.ViewModelProvider;
import com.blessedtreeit.product.activitycenter.ViewModelProviderFactory;
import com.blessedtreeit.product.activitycenter.flows.add_task.AddTaskViewModel;
import com.blessedtreeit.product.activitycenter.flows.entry.EntryViewModel;
import com.blessedtreeit.product.activitycenter.flows.login.LoginViewModel;
import com.blessedtreeit.product.activitycenter.flows.nav_drawer.NavViewModel;
import com.blessedtreeit.product.activitycenter.flows.tasks.TasksViewModel;
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
public class FactoryModule {

    public FactoryModule() {

    }

    @Provides
    @Qualifiers.LoginViewModel
    ViewModelProvider.Factory provideLoginViewModelFactory(LoginViewModel loginViewModel) {
        return new ViewModelProviderFactory<>(loginViewModel);
    }

    @Provides
    @Qualifiers.EntryViewModel
    ViewModelProvider.Factory provideEntryViewModelFactory(EntryViewModel entryViewModel) {
        return new ViewModelProviderFactory<>(entryViewModel);
    }

    @Provides
    @Qualifiers.TaskViewModel
    ViewModelProvider.Factory provideTasksViewModelFactory(TasksViewModel tasksViewModel) {
        return new ViewModelProviderFactory<>(tasksViewModel);
    }

    @Provides
    @Qualifiers.NavViewModel
    ViewModelProvider.Factory provideNavViewModelFactory(NavViewModel navViewModel) {
        return new ViewModelProviderFactory<>(navViewModel);
    }

    @Provides
    @Qualifiers.AddTaskViewModel
    ViewModelProvider.Factory provideAddTaskViewModelFactory(AddTaskViewModel addTaskViewModel) {
        return new ViewModelProviderFactory<>(addTaskViewModel);
    }

}
