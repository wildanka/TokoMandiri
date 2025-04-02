package com.example.tokomandiri.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tokomandiri.R
import com.example.tokomandiri.app.cart.CartScreen
import com.example.tokomandiri.app.detail.DetailScreen
import com.example.tokomandiri.app.home.presentation.HomeScreen
import com.example.tokomandiri.app.profile.ProfileScreen
import com.example.tokomandiri.ui.navigation.NavigationItem
import com.example.tokomandiri.ui.navigation.Screen


@Composable
fun TokoBerdiriApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailProduct.route) {
                BottomNavBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen(modifier, onShowProductDetail = {})
            }
            composable(Screen.Cart.route) {
                val context = LocalContext.current
                CartScreen(modifier)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(modifier)
            }
            composable(
                route = Screen.DetailProduct.route,
                arguments = listOf(
                    navArgument("productId") { type = NavType.LongType },
                ),
            ) {
                val id = it.arguments?.getLong("productId") ?: -1L
                DetailScreen()
//                    productId = id,
//                    navigateBack = { navController.navigateUp() },
//                    navigateToCart = {
//                        navController.popBackStack()
//                        navController.navigate(Screen.Cart.route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                )
            }
        }
    }
}

@Composable
fun BottomNavBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_cart),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            )
        )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navHostController.navigate(item.screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                modifier = modifier,
                label = {
                    Text(item.title)
                },
            )
        }
    }
}
