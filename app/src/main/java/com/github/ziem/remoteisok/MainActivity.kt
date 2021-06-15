package com.github.ziem.remoteisok

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.github.ziem.remoteisok.feature.job.JobScreen
import com.github.ziem.remoteisok.feature.jobs.JobsScreen
import com.github.ziem.remoteisok.ui.RemoteIsOkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            RemoteIsOkTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavGraph(navController) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://remoteok.io/")
                            )
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun NavGraph(navController: NavHostController, onHeaderClick: () -> Unit) {
    NavHost(navController, startDestination = "jobs") {
        composable("jobs") { JobsScreen(hiltViewModel(), navController, onHeaderClick) }
        composable("job/{jobId}", listOf(navArgument("jobId") { type = NavType.LongType })) { backStackEntry ->
            JobScreen(hiltViewModel(), backStackEntry.arguments!!.getLong("jobId"))
        }
    }
}