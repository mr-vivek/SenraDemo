package com.vivek.demo.utils;

@SuppressWarnings("WeakerAccess")
public abstract class PermissionsCallback {
    public void onGranted(boolean newPermissionsGranted) {
    }

    public void onDenied() {
    }
}

