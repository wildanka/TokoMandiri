package com.example.tokomandiri.ui.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object Cart: Screen("cart")
    data object OrderSummary: Screen("summary")
    data object Profile: Screen("profile")
    data object DetailProduct: Screen("home/{productId}"){
        fun createRoute(productId: Int) = "home/$productId"
    }
}