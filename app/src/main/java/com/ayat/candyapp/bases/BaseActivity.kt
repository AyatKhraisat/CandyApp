package com.ayat.candyapp.bases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ayat.candyapp.App
import com.ayat.candyapp.di.DaggerUiControllerComponent
import com.ayat.candyapp.di.UiControllerComponent
import com.ayat.candyapp.di.UiControllerModule

import io.reactivex.annotations.Nullable


abstract class BaseActivity : AppCompatActivity() {

    val uiControllerComponent: UiControllerComponent by lazy{
        DaggerUiControllerComponent.builder()
            .uiControllerModule(UiControllerModule(this))
            .applicationComponent((application as App).applicationComponent).build()

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()
    }



    protected abstract fun inject()

}
