package com.ayat.candyapp.login;

import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.blessedtreeit.product.activitycenter.bases.BaseViewModel;
import com.blessedtreeit.product.activitycenter.flows.login.model.LoginModel;
import com.blessedtreeit.product.activitycenter.utils.SingleLiveEvent;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import javax.inject.Inject;

/**
 * Created by Ayat khraisat  on 12/27/18
 * akhraisat@blessedtreeit.com
 * <p>
 * Project Name: ActivityCenter
 * <p>
 * Blessed Tree IT
 */
public class LoginViewModel extends BaseViewModel {

    private final MutableLiveData<String> name = new MutableLiveData<>();
    private final MutableLiveData<String> password = new MutableLiveData<>();

    private final SingleLiveEvent<Void> openMainActivity = new SingleLiveEvent<>();

    private UserManagementRepository userManagementRepository;
    private MutableLiveData<Boolean> showErrorMessage = new MutableLiveData<>();

    private final MutableLiveData<Integer> loginButtonVisibility = new MutableLiveData<>(View.VISIBLE);
    private final MutableLiveData<Integer> loadingVisibility = new MutableLiveData<>(View.GONE);
    private final MutableLiveData<Integer> successVisibility = new MutableLiveData<>(View.GONE);

    private final LiveData<Boolean> showUserNameError = Transformations.map(name,
            input -> input == null || input.toString().isEmpty());

    private final LiveData<Boolean> showPasswordError = Transformations.map(password,
            input -> input == null || input.toString().isEmpty());

    @Inject
    public LoginViewModel(UserManagementRepository userManagementRepository) {
        super();
        this.userManagementRepository = userManagementRepository;

    }


    SingleLiveEvent<Void> getOpenMainActivityEvent() {
        return openMainActivity;
    }

    public MutableLiveData<String> getName() {
        return name;
    }


    public MutableLiveData<String> getPassword() {
        return password;
    }


    public void onLoginClicked() {
        if (isInputValid()) {

            Single<LoginModel> loginObservable =
                    userManagementRepository.getLoginObservable(name.getValue(), password.getValue());

            Disposable loginDisposable = loginObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> showLoading())
                    .doOnEvent((loginModel, throwable) -> showLoginButton())
                    .doOnError(this::showError)
                    .subscribe(getOnSuccessConsumer());

            compositeDisposable.add(loginDisposable);
        }
    }


    private boolean isInputValid() {
        return (!getShowPasswordError().getValue() && !getShowUserNameError().getValue());
    }

    private void showLoading() {
        loadingVisibility.setValue(View.VISIBLE);
        loginButtonVisibility.setValue(View.GONE);
    }

    private void showLoginButton() {
        loadingVisibility.setValue(View.GONE);
        loginButtonVisibility.setValue(View.VISIBLE);
    }

    private void showError(Throwable throwable) {
        showErrorMessage.setValue(true);
        successVisibility.setValue(View.GONE);
        Timber.d(throwable);
    }

    private Consumer<LoginModel> getOnSuccessConsumer() {
        return successObservableItem -> {
            if (successObservableItem.isSuccess()) {
                showLoginSuccess();
            } else {
                showLoginFail();
            }
        };
    }

    private void showLoginFail() {
        successVisibility.setValue(View.GONE);
        showErrorMessage.setValue(true);
    }


    private void showLoginSuccess() {
        successVisibility.setValue(View.VISIBLE);
        loginButtonVisibility.setValue(View.VISIBLE);
        openMainActivity.call();
    }

    public LiveData<Integer> getLoadingVisibility() {
        return loadingVisibility;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<Boolean> getShowErrorMessage() {
        return showErrorMessage;
    }

    public LiveData<Boolean> getShowPasswordError() {
        return showPasswordError;
    }

    public LiveData<Boolean> getShowUserNameError() {
        return showUserNameError;
    }

    public LiveData<Integer> getSuccessVisibility() {
        return successVisibility;
    }

    public LiveData<Integer> getLoginButtonVisibility() {
        return loginButtonVisibility;
    }

}
