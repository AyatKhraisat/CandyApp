package com.ayat.candyapp.utils

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created by Ayat khraisat  on 2019-06-09
 * ayatzkhraisat@gmail.com
 * <p>
 * Project Name: CandyApp
 * <p>
 */

inline fun <T : Fragment> T.withArgs(
    argsBuilder: Bundle.() -> Unit
): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }