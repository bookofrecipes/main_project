package ru.geekbrains.bookofrecipes.service.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

class NetworkAvailabilityHandler(private val context: Context) {

    fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network: Network = cm.activeNetwork ?: return false
            val activeNetwork = cm.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = cm.activeNetworkInfo ?: return false

            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}