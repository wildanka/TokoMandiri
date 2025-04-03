package com.example.tokomandiri.app

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tokomandiri.R
import com.example.tokomandiri.app.cart.presentation.CartScreen
import com.example.tokomandiri.app.product.presentation.ui.detail.DetailScreen
import com.example.tokomandiri.app.product.presentation.ui.home.HomeScreen
import com.example.tokomandiri.app.profile.ProfileScreen
import com.example.tokomandiri.ui.navigation.NavigationItem
import com.example.tokomandiri.ui.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TokoBerdiriApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var title by remember { mutableStateOf("") }



    Scaffold(
        topBar = {
            if (currentRoute != Screen.DetailProduct.route) {
                MandiriTopAppBar(title, currentRoute.orEmpty(), navController){
                    navController.popBackStack()
                }
            }
        },
        containerColor = Color.White,
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .padding(innerPadding)
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            composable(Screen.Home.route) {
                val context = LocalContext.current
                title = context.resources.getString(R.string.app_name)

                HomeScreen(modifier, onShowProductDetail = { productId ->

                    navController.navigate(Screen.DetailProduct.createRoute(productId))
                })
            }
            composable(Screen.Cart.route) {
                val context = LocalContext.current
                title = context.resources.getString(R.string.menu_cart)
                CartScreen(modifier)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(modifier)
            }
            composable(
                route = Screen.DetailProduct.route,
                enterTransition = {
                    scaleIn(
                        initialScale = 0.1f,
                        animationSpec = tween(700),
                        transformOrigin = TransformOrigin(pivotFractionX = 0.5f, 0.2f)
                    )
                },
                popExitTransition = {
                    scaleOut(
                        targetScale = 0.1f,
                        animationSpec = tween(700),
                        transformOrigin = TransformOrigin(pivotFractionX = 0.5f, 0.2f)
                    )
                },
                arguments = listOf(
                    navArgument("productId") { type = NavType.IntType },
                ),
            ) {
                val id = it.arguments?.getInt("productId") ?: 0
                DetailScreen(
                    productId = id,
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandiriTopAppBar(
    title: String,
    route: String,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
        },
        navigationIcon = {
            if(route != Screen.Home.route){
                IconButton(onClick = onBackClicked) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },

        actions = {
            IconButton(
                onClick = {
                    navHostController.navigate(Screen.Cart.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }

                }
            ) {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart")
            }

            IconButton(
                onClick = {
                    navHostController.navigate(Screen.Profile.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }

                }
            ) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Account")
            }
        }
    )
}
