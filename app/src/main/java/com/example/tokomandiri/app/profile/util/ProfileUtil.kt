package com.example.tokomandiri.app.profile.util

import com.example.tokomandiri.app.login.data.remote.model.Address

object ProfileUtil {
    fun constructAddressString(address: Address?): String{
        if(address == null){
            return ""
        }
        return "${address.city}, ${address.street} ${address.number} (${address.zipcode})"
    }
}