package com.github.ziem.remoteisok.feature.job

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ziem.remoteisok.feature.common.CompanyImage
import com.github.ziem.remoteisok.feature.common.Tag
import com.github.ziem.remoteisok.ui.typography
import com.google.accompanist.flowlayout.FlowRow
import org.commonmark.node.Document
import org.commonmark.parser.Parser
import se.hellsoft.markdowncomposer.MDDocument

@Composable
fun JobScreen(viewModel: JobViewModel, jobId: Long) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Remote is OK")
                },
            )
        },
        content = {
            JobScreenContent(viewModel, jobId)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(state.job?.url)))
            }) {
                Icon(
                    Icons.Filled.OpenInBrowser,
                    contentDescription = "apply",
                )
            }
        }
    )
}

@Composable
fun JobScreenContent(viewModel: JobViewModel, jobId: Long) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(jobId) {
        viewModel.loadJob(jobId)
    }

    val job = state.job
    val parser = Parser.builder().build()
    if (job != null) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp, 32.dp, 16.dp, 48.dp)
        ) {
            CompanyImage(
                job.company,
                Modifier
                    .size(128.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(job.position, modifier = Modifier.align(Alignment.CenterHorizontally), style = typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            Text(job.company.name, Modifier.align(Alignment.CenterHorizontally), style = typography.h6)
            if (job.location.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(job.location)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Posted ${job.date}")
            Spacer(modifier = Modifier.height(8.dp))
            val root = parser.parse(job.description) as Document
            MDDocument(root)
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(mainAxisSpacing = 8.dp, crossAxisSpacing = 8.dp) {
                for (tag in job.tags) {
                    Tag(tag, 16.sp)
                }
            }
        }
    }
}
