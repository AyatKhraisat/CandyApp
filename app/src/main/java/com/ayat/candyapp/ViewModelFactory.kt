package com.ayat.candyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ayat.candyapp.di.ViewScope

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Scope
import javax.inject.Singleton

/**
 * Created by Ayat khraisat  on 12/27/18
 * ayatzkhraisat@gmail.com
 * taken from
 * https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di/AppComponent.kt
 */
@ViewScope
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}
