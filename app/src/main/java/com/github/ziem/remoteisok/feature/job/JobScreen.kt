package com.github.ziem.remoteisok.feature.job

import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.github.ziem.remoteisok.feature.common.CompanyImage
import com.github.ziem.remoteisok.feature.common.Tags
import com.github.ziem.remoteisok.ui.typography


@Composable
fun JobScreen(viewModel: JobViewModel, jobId: Long) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(jobId) {
        viewModel.loadJob(jobId)
    }

    val job = state.job
    if (job != null) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            CompanyImage(
                job.company,
                Modifier
                    .size(128.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(job.company.name, style = typography.h1)
            Text(job.position, style = typography.h2)
            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { context -> TextView(context) }
            ) { view ->
                view.text = HtmlCompat.fromHtml(job.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            }
            Text(job.location, style = typography.h4)
            Text(job.url, style = typography.h5)
            Text(job.date.toString(), style = typography.h6)
            Tags(tags = job.tags)
        }
    }
}