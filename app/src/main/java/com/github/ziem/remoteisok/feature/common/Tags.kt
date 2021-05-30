package com.github.ziem.remoteisok.feature.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.github.ziem.remoteisok.ui.typography

@Composable
fun Tag(tag: String, fontSize: TextUnit = typography.overline.fontSize) {
    Text(
        tag,
        color = MaterialTheme.colors.onSecondary,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
            .padding(4.dp),
        style = typography.overline.copy(fontSize = fontSize),
    )
}