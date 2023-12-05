package com.example.bykuliner

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bykuliner.ui.navigation.NavigationItem
import com.example.bykuliner.ui.navigation.Screen
import com.example.bykuliner.ui.screen.about.ProfileScreen
import com.example.bykuliner.ui.screen.detail.DetailScreen
import com.example.bykuliner.ui.screen.home.HomeScreen
import com.example.bykuliner.ui.theme.ByKulinerTheme

@Composable
fun ByKulinerApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),

    ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailKuliner.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen( navigateToDetail = { kulinerId ->
                    navController.navigate(Screen.DetailKuliner.createRoute(kulinerId))
                })
            }
            composable(Screen.About.route) {
                ProfileScreen()
            }
            composable(
                    route = Screen.DetailKuliner.route,
                    arguments = listOf(navArgument("kulinerId") { type = NavType.IntType }),
            ) {
            val id = it.arguments?.getInt("kulinerId") ?: -1
            DetailScreen(
                kulinerId = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
            }
        }

    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,

    ) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier,
    ) {
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.AccountCircle,
                screen = Screen.About
            )
        )
        navigationItem.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    ByKulinerTheme {
        ByKulinerApp()
    }
}