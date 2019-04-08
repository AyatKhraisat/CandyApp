package com.ayat.candyapp.di;


import com.blessedtreeit.product.activitycenter.flows.add_task.AddTaskActivity;
import com.blessedtreeit.product.activitycenter.flows.add_task.AssignedToSelectionFragment;
import com.blessedtreeit.product.activitycenter.flows.entry.EntryActivity;
import com.blessedtreeit.product.activitycenter.flows.login.LoginActivity;
import com.blessedtreeit.product.activitycenter.flows.nav_drawer.NavActivity;
import com.blessedtreeit.product.activitycenter.flows.tasks.TasksFragment;
import dagger.Component;

@ViewScope
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {UiControllerModule.class, FactoryModule.class}
)
public interface UiControllerComponent {

    void inject(LoginActivity loginActivity);

    void inject(EntryActivity entryActivity);

    //  void inject(BaseFragment baseFragment);

    void inject(NavActivity navActivity);

    void inject(TasksFragment tasksFragment);

    void inject(AddTaskActivity addTaskActivity);

    void inject(AssignedToSelectionFragment assignedToSelectionFragment);
}