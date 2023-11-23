package com.bbb.koha.common

import android.Manifest
import android.os.Build

/**
 *Created by Gaurav Kumar on 7/11/2022
 * QUYTECH
 */
object Constant {
    const val  LOCATION_SERVICE_NOTIF_ID = 1001
    const val BASE_URL ="http://dspace.bestbookbuddies.com:8081/api/v1/"
    const val GRANT_TYPE ="client_credentials"
    const val CLIENT_ID ="dc457ada-085d-4800-81a5-2411bed75009"
    const val CLIENT_SECRET  ="53c139ae-6d38-436f-98cd-ececf05cce5a"
    const val PREFS_NAME = "koha"
    const val MAC_ADDRESS = "mac_address"
    const val REQUEST_PERMISION =101
    const val PERMISSIONS_REQUEST_CODE = 102
    const val MOBILE ="mobile"
    const val VEHICLE_NO ="vehicle_no"
    const val IS_LOGIN ="is_login"
    const val ACCESS_TOKEN ="access_token"
    const val TOKEN_TYPE ="token_type"
    const val EXPIRES_IN ="expires_in"
    const val FIRST_NAME ="first_name"
    const val LAST_NAME ="last_name"
    const val ADDRESS ="address"
    const val DOB ="dob"
    object UserType{
        const val ADMIN = 1
        const val RETAILER = 2
        const val DISTRIBUTOR = 3
        const val MECHANIC = 4
        const val VEHICLE_OWNER = 5
    }

    object OTPVerificationIntentType{
        const val FORGOT_ACTIVITY = "forgot_activity"
        const val REGISTRATION_ACTIVITY = "registration_activity"
    }

    val PERMISSIONS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        )
    } else {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    }

}