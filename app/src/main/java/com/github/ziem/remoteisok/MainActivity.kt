package com.github.ziem.remoteisok

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.github.ziem.remoteisok.feature.job.JobViewModel
import com.github.ziem.remoteisok.feature.jobs.JobsViewModel
import com.github.ziem.remoteisok.model.Company
import com.github.ziem.remoteisok.model.Job
import com.github.ziem.remoteisok.ui.RemoteIsOkTheme
import com.github.ziem.remoteisok.ui.typography
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.flowlayout.FlowRow
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
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
                            NavGraph(onHeaderClick = {
                                startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://remoteok.io/")
                                    )
                                )
                            })
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NavGraph(onHeaderClick: () -> Unit) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "jobs") {
        composable("jobs") { JobsScreen(hiltNavGraphViewModel(), navController, onHeaderClick) }
        composable("job") { JobScreen(hiltNavGraphViewModel()) }
    }
}

@Composable
fun JobScreen(viewModel: JobViewModel) {
    val state by viewModel.job.collectAsState()

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        CompanyImageComposable(128.dp, state.company)
        Text(state.company.name, style = typography.h1)
        Text(state.position, style = typography.h2)
        Text("This is a h3", style = typography.h3)
        Text("This is a h4", style = typography.h4)
        Text("This is a h5", style = typography.h5)
        Text("This is a h6", style = typography.h6)
        Text("This is a body1", style = typography.body1)
        Text("This is a body2", style = typography.body2)
        Text("This is a caption", style = typography.caption)
        Text("This is a overline", style = typography.overline)
        Text("This is a subtitle2", style = typography.subtitle2)
        Text("This is a subtitle1", style = typography.subtitle1)

        TagsComposable(tags = state.tags)
    }
}

@Composable
fun JobsScreen(viewModel: JobsViewModel, navController: NavController, onHeaderClick: () -> Unit) {
    val state by viewModel.jobs.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            LazyColumn {
                items(state) { job ->
                    JobRowComposable(job) { navController.navigate("job") }
                    Divider()
                }
            }
        }
        Text(
            text = "Powered by Remote OK",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clickable(onClick = onHeaderClick)
                .clip(RoundedCornerShape(4.dp, 0.dp, 0.dp, 0.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp, 0.dp, 0.dp, 0.dp))
                .background(MaterialTheme.colors.background)
                .padding(16.dp, 8.dp, 8.dp, 8.dp),
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
fun JobRowComposable(job: Job, onJobClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onJobClick)
            .background(MaterialTheme.colors.surface)
            .clip(RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        CompanyImageComposable(64.dp, job.company)
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(job.company.name, style = MaterialTheme.typography.body2)
                Box(modifier = Modifier.weight(1f))
                if (job.isWorldwide()) {
                    Icon(
                        Icons.Filled.Public,
                        contentDescription = "worldwide",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(job.location, style = typography.caption)
                } else if (job.hasLocation()) {
                    Icon(
                        Icons.Filled.Flag,
                        contentDescription = "with location",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(job.location, style = typography.caption)
                }
            }
            Text(
                job.position, fontWeight = FontWeight.Bold,
                style = typography.h6
            )
            if (job.tags.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                TagsComposable(tags = job.tags)
            }
        }
    }
}

@Composable
fun CompanyImageComposable(size: Dp, company: Company) {
    Box(
        Modifier
            .size(size)
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
            .padding(4.dp)
    ) {
        if (company.logoUrl != null && company.logoUrl != "https://cdn.sstatic.net/careers/Img/ico-no-company-logo.svg") {
            CoilImage(company.logoUrl, "logo", Modifier.fillMaxSize())
        } else {
            Text(
                text = company.name.first().toString().toUpperCase(Locale.getDefault()),
                modifier = Modifier.align(Alignment.Center),
                style = typography.h5
            )
        }
    }
}

@Composable
fun TagsComposable(tags: List<String>) {
    FlowRow(mainAxisSpacing = 4.dp, crossAxisSpacing = 4.dp) {
        for (tag in tags) {
            Text(
                tag,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                    .padding(4.dp),
                style = typography.overline,
            )
        }
    }
}