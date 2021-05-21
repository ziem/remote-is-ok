package com.github.ziem.remoteisok.feature.job

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ziem.remoteisok.feature.common.CompanyImage
import com.github.ziem.remoteisok.feature.common.Tags
import com.github.ziem.remoteisok.ui.typography


@Composable
fun JobScreen(viewModel: JobViewModel) {
    val state by viewModel.job.collectAsState()

    Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {
        CompanyImage(state.company, Modifier.size(128.dp).align(Alignment.CenterHorizontally))
        Text(state.company.name, style = typography.h1)
        Text(state.position, style = typography.h2)
        Text(state.description, style = typography.body1)
        Text(state.location, style = typography.h4)
        Text(state.url, style = typography.h5)
        Text(state.date, style = typography.h6)

        Tags(tags = state.tags)
    }
}