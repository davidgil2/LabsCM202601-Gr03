package co.edu.udea.compumovil.gr03_20261.lab2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.edu.udea.compumovil.gr03_20261.lab2.ui.detail.DetailScreen
import co.edu.udea.compumovil.gr03_20261.lab2.ui.home.HomeScreen
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onArticleClick = { url ->
                    val encoded = URLEncoder.encode(url, "UTF-8")
                    navController.navigate("detail/$encoded")
                }
            )
        }
        composable(
            route = "detail/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStack ->
            val url = URLDecoder.decode(
                backStack.arguments?.getString("url") ?: "", "UTF-8"
            )
            DetailScreen(url = url, onBack = { navController.popBackStack() })
        }
    }
}