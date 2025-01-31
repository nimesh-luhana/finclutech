package nimesh.luhana.finclutech.navigation

import nimesh.luhana.finclutech.features.login.LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nimesh.luhana.finclutech.features.savedrequests.SavedRequestsScreen
import nimesh.luhana.finclutech.features.sendmoney.SendMoneyScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") { LoginScreen(navController) }
        composable("send_money") { SendMoneyScreen(navController) }
        composable("saved_requests") { SavedRequestsScreen(navController) }
    }
}