package dev.vladleesi.braindanceapp.ui.components.giveaways

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.vladleesi.braindanceapp.ui.style.Dimens
import dev.vladleesi.braindanceapp.ui.style.background
import dev.vladleesi.braindanceapp.ui.style.overlayBlackWith70Alpha
import dev.vladleesi.braindanceapp.ui.style.overlayWhiteWith80Alpha
import dev.vladleesi.braindanceapp.ui.style.secondaryText
import dev.vladleesi.braindanceapp.ui.style.white
import dev.vladleesi.braindanceapp.ui.viewmodels.HomeStateEntity
import dev.vladleesi.braindanceapp.utils.ParentPlatformType
import dev.vladleesi.braindanceapp.utils.toContentDescription
import org.jetbrains.compose.resources.painterResource

@Composable
fun GiveawayItem(
    item: GiveawayItemModel,
    modifier: Modifier = Modifier,
    onCardClicked: (id: Int) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        GiveawayItemCard(
            item = item,
            onCardClicked = onCardClicked,
        )
        Spacer(modifier = Modifier.height(Dimens.small))
        Text(
            text = item.title,
            style = MaterialTheme.typography.subtitle1,
            color = white,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(Dimens.giveAwayCardWidth),
        )
        Spacer(modifier = Modifier.height(Dimens.micro))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "FREE",
                color = white,
                style = MaterialTheme.typography.caption,
            )
            Spacer(modifier = Modifier.width(Dimens.tiny))
            Text(
                text = item.worth,
                style = MaterialTheme.typography.subtitle1,
                color = secondaryText,
                textDecoration = TextDecoration.LineThrough,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun GiveawayItemCard(
    modifier: Modifier = Modifier,
    item: GiveawayItemModel,
    onCardClicked: (id: Int) -> Unit,
) {
    Card(
        modifier =
            modifier
                .height(Dimens.giveAwayCardHeight)
                .width(Dimens.giveAwayCardWidth),
        onClick = { onCardClicked(item.id) },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = item.image,
                contentScale = ContentScale.Crop,
                contentDescription = item.title.toContentDescription(),
            )
            if (item.endDate.isNotEmpty()) {
                Text(
                    text = item.endDate,
                    style = MaterialTheme.typography.caption,
                    color = white,
                    modifier =
                        Modifier
                            .align(Alignment.TopStart)
                            .padding(Dimens.tiny)
                            .background(
                                color = overlayBlackWith70Alpha,
                                shape = RoundedCornerShape(Dimens.small),
                            )
                            .padding(horizontal = Dimens.tiny, vertical = Dimens.micro),
                )
            }
            Row(
                modifier =
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(Dimens.tiny)
                        .background(
                            color = overlayBlackWith70Alpha,
                            shape = RoundedCornerShape(Dimens.small),
                        )
                        .padding(Dimens.tiny),
                horizontalArrangement = Arrangement.spacedBy(Dimens.micro),
            ) {
                item.platforms.forEach { platform ->
                    if (platform.iconRes == null) return@forEach
                    Image(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(platform.iconRes),
                        colorFilter = ColorFilter.tint(white),
                        contentDescription = platform.name.toContentDescription(),
                    )
                }
            }
            Text(
                text = item.type,
                style = MaterialTheme.typography.caption,
                color = background,
                modifier =
                    Modifier
                        .align(Alignment.BottomEnd)
                        .padding(Dimens.tiny)
                        .background(
                            color = overlayWhiteWith80Alpha,
                            shape = RoundedCornerShape(Dimens.small),
                        )
                        .padding(horizontal = Dimens.tiny),
            )
        }
    }
}

data class GiveawayItemModel(
    val id: Int,
    val image: String,
    val title: String,
    val worth: String,
    val endDate: String,
    val platforms: List<ParentPlatformType>,
    val type: String,
) : HomeStateEntity
