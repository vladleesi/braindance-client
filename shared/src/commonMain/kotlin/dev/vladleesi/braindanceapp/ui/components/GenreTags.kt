package dev.vladleesi.braindanceapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import dev.vladleesi.braindanceapp.ui.style.Dimens
import dev.vladleesi.braindanceapp.ui.style.secondaryVariant
import dev.vladleesi.braindanceapp.ui.style.white
import dev.vladleesi.braindanceapp.ui.viewmodels.GenreTag

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenreTags(
    genres: List<GenreTag>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth().padding(start = Dimens.medium, end = Dimens.medium),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(Dimens.tiny),
    backgroundColor: Color = secondaryVariant,
    textStyle: TextStyle = MaterialTheme.typography.caption,
    textColor: Color = white,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
    ) {
        genres.forEach { genre ->
            Chip(
                colors = ChipDefaults.chipColors(backgroundColor = backgroundColor),
                onClick = {
                    onClick.invoke(genre.id)
                },
            ) {
                Text(
                    style = textStyle,
                    text = genre.name,
                    color = textColor,
                )
            }
        }
    }
}
