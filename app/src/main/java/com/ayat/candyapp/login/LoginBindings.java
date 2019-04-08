package com.ayat.candyapp.login;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import com.blessedtreeit.product.activitycenter.utils.PixelUtil;

/**
 * Created by: Ahmed Al-Hashimi
 * Created on: Jan. 07, 2019.
 * <p>
 * aalhashimi@blessedtreeit.com
 * <p>
 * Project Name: ActivityCenterProduct
 * <p>
 * BTIT
 */
public class LoginBindings {


    private LoginBindings(){

    }


    // TODO: 1/7/19 if this is reused, move to general binding class
    @BindingAdapter(value = {"tvLabel"})
    public static void animateEditText(EditText et, TextView tv) {
        et.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                raiseView(v);
                raiseView(tv);
            } else {
                lowerView(v);
                lowerView(tv);
            }
        });
    }

    private static void lowerView(View v) {
        changeViewElevation(v, (int) v.getElevation(), PixelUtil.dpToPx(v.getContext(), 1),
                new DecelerateInterpolator(15));
    }

    private static void raiseView(View v) {
        changeViewElevation(v, (int) v.getElevation(), PixelUtil.dpToPx(v.getContext(), 5),
                new OvershootInterpolator(5));
    }

    private static void changeViewElevation(View v, int start, int end, TimeInterpolator timeInterpolator) {
        ValueAnimator anim = ValueAnimator.ofInt(start, end);
        anim.setInterpolator(timeInterpolator);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            v.setElevation(val);
        });
        anim.setDuration(1000);
        anim.start();
    }
}
