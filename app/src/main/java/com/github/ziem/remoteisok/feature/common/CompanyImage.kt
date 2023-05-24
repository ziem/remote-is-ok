package com.github.ziem.remoteisok.feature.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import com.github.ziem.remoteisok.model.Company
import com.github.ziem.remoteisok.ui.typography
import java.util.*

@Composable
fun CompanyImage(company: Company, modifier: Modifier = Modifier) {
    Box(
        modifier
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
            .padding(4.dp)
    ) {
        if (company.logoUrl?.isNotBlank() == true && company.logoUrl != "https://cdn.sstatic.net/careers/Img/ico-no-company-logo.svg") {
            Image(
                painter = rememberAsyncImagePainter(
                    model = company.logoUrl,
                    imageLoader = LocalContext.current.imageLoader
                ),
                contentDescription = "logo",
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            Text(
                text = company.name.first().toString().uppercase(Locale.getDefault()),
                modifier = Modifier.align(Alignment.Center),
                style = typography.h5
            )
        }
    }
}
