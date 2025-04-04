package com.example.tokomandiri.app.product.presentation

import com.example.tokomandiri.R

object ProductUtil {
    fun getCategoryIcon(category: String): Int? {
        when (category) {
            "men's clothing" -> {
                return R.drawable.ic_mens_wear
            }

            "women's clothing" -> {
                return R.drawable.ic_womens_wear
            }

            "electronics" -> {
                return R.drawable.ic_gadget
            }

            "jewelery" -> {
                return R.drawable.ic_jewellery
            }

            else -> {
                return null
            }
        }
    }
}