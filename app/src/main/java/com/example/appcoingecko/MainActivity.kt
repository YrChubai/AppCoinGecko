package com.example.appcoingecko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appcoingecko.ui.theme.AppCoinGeckoTheme
import com.example.appcoingecko.ui.screen.crypto_detail.CryptoDetailScreen
import com.example.appcoingecko.ui.screen.crypto_list.CryptoListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCoinGeckoTheme {
                MainApp()
            }
        }
    }
}


@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "crypto_list") {
        composable("crypto_list") {
            CryptoListScreen(navController)
        }
        composable("crypto_detail/{coinId}/{coinName}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("coinId") ?: "tron"
            val itemName = backStackEntry.arguments?.getString("coinName") ?: "TRX"
            CryptoDetailScreen(itemId, itemName, navController)
        }
    }

}