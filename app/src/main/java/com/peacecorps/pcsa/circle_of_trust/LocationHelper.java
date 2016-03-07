package com.peacecorps.pcsa.circle_of_trust;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Helper Class for handling location listening and retrieving
 * Created by chamika on 2/29/16.
 */
public class LocationHelper {
    public static final String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;
    private static final String TAG = LocationHelper.class.getSimpleName();

    private Location lastLocation;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Context context;

    public LocationHelper(Context context) {
        this.context = context;
    }

    /**
     * Start acquiring location updates
     */
    public void startAcquiringLocation() {
        if (context != null) {
            if (locationManager == null) {
                // Acquire a reference to the system Location Manager
                locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            }

            if (locationListener == null) {
                // Define a listener that responds to location updates
                locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        // Called when a new location is found by the location provider.
                        Log.d(TAG, "Location Updated:" + location.toString());
                        updateLocation(location);
                    }

                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    public void onProviderEnabled(String provider) {
                    }

                    public void onProviderDisabled(String provider) {
                    }
                };
            }

            try {
                // Register the listener with the Location Manager to receive location updates
                locationManager.requestLocationUpdates(LOCATION_PROVIDER, 0, 0, locationListener);
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "Unable to listen to GPS location updates", e);
            } catch (NullPointerException e) {
                Log.e(TAG, "Unable to get location services", e);
            }
        }
    }

    /**
     * Stop acquiring location updates.
     * This is important to call to save battery consumption of retrieving GPS
     */
    public void stopAcquiringLocation() {
        if (locationManager != null && locationListener != null) {
            // Remove the listener which added by calling #startAcquiringLocation
            locationManager.removeUpdates(locationListener);
        }
    }

    /**
     * Retrive acquired location
     *
     * @param needLastKnown if no location update found, return last known location
     * @return
     */
    public Location retrieveLocation(boolean needLastKnown) {
        if (lastLocation == null && locationManager != null && needLastKnown) {
            lastLocation = locationManager.getLastKnownLocation(LOCATION_PROVIDER);
        }
        return lastLocation;
    }

    private void updateLocation(Location location) {
        this.lastLocation = location;
    }

}
