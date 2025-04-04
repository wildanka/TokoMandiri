package com.example.tokomandiri.app

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.tokomandiri.R
import com.example.tokomandiri.app.cart.presentation.list.CartScreen
import com.example.tokomandiri.app.cart.presentation.summary.SummaryScreen
import com.example.tokomandiri.app.login.presentation.login.LoginScreen
import com.example.tokomandiri.app.product.presentation.ui.detail.DetailScreen
import com.example.tokomandiri.app.product.presentation.ui.home.HomeScreen
import com.example.tokomandiri.app.profile.ProfileBottomSheetContent
import com.example.tokomandiri.framework.AppUtility
import com.example.tokomandiri.ui.navigation.NavigationItem
import com.example.tokomandiri.ui.navigation.Screen
import org.koin.core.context.GlobalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TokoBerdiriApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val encryptedSharedPreferences: SharedPreferences = GlobalContext.get().get()
    val encryptedPrefs = encryptedSharedPreferences as EncryptedSharedPreferences
    val appToken = encryptedPrefs.getString(AppUtility.APP_TOKEN, "")
    Log.d("WLDN TBA", "TokoBerdiriApp: $appToken")

    //TODO : handle the login logic
    val isUserLoggedIn = remember { mutableStateOf(!appToken.isNullOrBlank()) }
    val startDestination = if (isUserLoggedIn.value) Screen.Home.route else Screen.Login.route


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var title by remember { mutableStateOf("") }

    //bottomSheet
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val noAppBarRoutes = listOf(Screen.DetailProduct.route, Screen.Login.route)

    Scaffold(
        topBar = {
            if (currentRoute !in noAppBarRoutes) {
                MandiriTopAppBar(
                    title,
                    currentRoute.orEmpty(),
                    navController,
                    onProfileMenuClick = {
                        showBottomSheet = true
                    },
                    onBackClicked = { navController.popBackStack() }
                )
            }
        },
        containerColor = Color.White,
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier.fillMaxSize(),
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
                    CartScreen(
                        modifier = modifier,
                        onCheckoutClick = { navController.navigate(Screen.OrderSummary.route) }
                    )
                }
                composable(Screen.Login.route) {
                    LoginScreen(
                        modifier = modifier,
                        onLoginSuccess = {
                            val newToken = encryptedPrefs.getString(AppUtility.APP_TOKEN, "")
                            Log.d("WLDN APP", "TokoBerdiriApp: onLoginSuccess newToken = $newToken")

                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
                composable(Screen.OrderSummary.route) {
                    val context = LocalContext.current
                    title = context.resources.getString(R.string.order_summary)
                    SummaryScreen(modifier)
                }
                composable(
                    route = Screen.DetailProduct.route,
                    enterTransition = {
                        scaleIn(
                            initialScale = 0.1f,
                            animationSpec = tween(700),
                            transformOrigin = TransformOrigin(pivotFractionX = 0.5f, 0.5f)
                        )
                    },
                    popExitTransition = {
                        scaleOut(
                            targetScale = 0.1f,
                            animationSpec = tween(700),
                            transformOrigin = TransformOrigin(pivotFractionX = 0.5f, 0.5f)
                        )
                    },
                    exitTransition = {
                        scaleOut(
                            targetScale = 0.1f,
                            animationSpec = tween(700),
                            transformOrigin = TransformOrigin(pivotFractionX = 0.5f, 1.0f)
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
                        onAddToCart = {
                            navController.popBackStack()
                            navController.navigate(Screen.Cart.route)
                        },
                    )
                }
            }
        }

        // Display BottomSheet when triggered
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                ProfileBottomSheetContent(){
                    //TODO : why not getting triggered?v
                    isUserLoggedIn.value = false
                    val editor = encryptedPrefs.edit()
                    editor.remove(AppUtility.APP_TOKEN)

                    // Commit or apply the changes
                    editor.apply()

                    val tokenAfterLogout = encryptedPrefs.getString(AppUtility.APP_TOKEN, "")
                    Log.d("WLDN TBA", "Token after logout: $tokenAfterLogout")
                    showBottomSheet = false

                }
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
    onBackClicked: () -> Unit,
    onProfileMenuClick: () -> Unit
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
            if (route != Screen.Home.route) {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },

        actions = {
            if (route == Screen.Home.route) {
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
                        onProfileMenuClick()
                    }
                ) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Account")
                }
            }

        }
    )
}
