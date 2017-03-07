package com.peacecorps.pcsa;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by HIman$hu on 3/7/2017.
 */

public class StatusBarColorUtil {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void changeStatusBarColor(Context context, Window window) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(context, R.color.status_bar_color));
        }
    }
}
