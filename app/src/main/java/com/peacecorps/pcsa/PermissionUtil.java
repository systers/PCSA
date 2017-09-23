package com.peacecorps.pcsa;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Utility class that wraps access to the runtime permissions API in M and provides basic helper
 * methods.
 *
 * @author Aagam Shah
 * @since 4/21/2017
 */

public abstract class PermissionUtil {

    /**
     * @param permission The permission to check for.
     * @param context    Context returned by getActivity().
     * @return true if permission is granted.
     */
    public static boolean isPermissionGranted(String permission, Context context) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @param permissions Permissions required at run time.
     * @param context     Context returned by getActivity().
     * @return true if all permissions are granted.
     */
    public static boolean areAllRunTimePermissionsGranted(String[] permissions, Context context) {
        boolean allPermissionsGranted = true;
        for (String permission : permissions) {
            allPermissionsGranted = allPermissionsGranted && isPermissionGranted(permission, context);
        }
        return allPermissionsGranted;
    }
}
