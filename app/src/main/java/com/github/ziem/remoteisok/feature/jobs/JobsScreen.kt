package com.github.ziem.remoteisok.feature.jobs

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Public
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.ziem.remoteisok.feature.common.CompanyImage
import com.github.ziem.remoteisok.feature.common.Tags
import com.github.ziem.remoteisok.model.Job
import com.github.ziem.remoteisok.ui.typography

@Composable
fun JobsScreen(viewModel: JobsViewModel, navController: NavController, onHeaderClick: () -> Unit) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            LazyColumn {
                items(state.jobs) { job ->
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
        CompanyImage(job.company, Modifier.size(64.dp))
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
                Tags(tags = job.tags)
            }
        }
    }
}
