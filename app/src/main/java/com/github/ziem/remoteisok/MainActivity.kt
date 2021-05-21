package com.github.ziem.remoteisok

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            RemoteIsOkTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text("Remote is OK")
                                },
                                actions = {
                                    IconButton(onClick = {}) {
                                        Icon(Icons.Filled.Search, "search")
                                    }
                                }
                            )
                        },
                        content = {
                            NavGraph(navController) {
                                startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://remoteok.io/")
                                    )
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController, onHeaderClick: () -> Unit) {
    NavHost(navController, startDestination = "jobs") {
        composable("jobs") { JobsScreen(hiltNavGraphViewModel(), navController, onHeaderClick) }
        composable("job/{jobId}", listOf(navArgument("jobId") { type = NavType.LongType })) { backStackEntry ->
            JobScreen(hiltNavGraphViewModel(), backStackEntry.arguments!!.getLong("jobId"))
        }
    }
}