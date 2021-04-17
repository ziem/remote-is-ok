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
import androidx.compose.ui.unit.dp
import com.github.ziem.remoteisok.ui.typography
import dev.chrisbanes.accompanist.flowlayout.FlowRow

@Composable
fun Tags(tags: List<String>) {
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