package dev.vladleesi.braindanceapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.dp
import dev.vladleesi.braindanceapp.resources.Res
import dev.vladleesi.braindanceapp.resources.game_details_screen_where_to_buy
import dev.vladleesi.braindanceapp.ui.style.getTypography
import dev.vladleesi.braindanceapp.ui.style.medium
import dev.vladleesi.braindanceapp.ui.style.secondaryVariant
import dev.vladleesi.braindanceapp.ui.style.small
import dev.vladleesi.braindanceapp.ui.style.white
import dev.vladleesi.braindanceapp.utils.StoreTypeModel
import dev.vladleesi.braindanceapp.utils.toContentDescription
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

private const val MAX_BUTTONS_PER_ROW = 2

fun LazyListScope.storesBlockItem(stores: List<StoreTypeModel>) {
    if (stores.isNotEmpty()) {
        item {
            Text(
                text = stringResource(Res.string.game_details_screen_where_to_buy),
                style = getTypography().h3,
                modifier = Modifier.padding(start = medium, end = medium),
            )
        }
        items(stores.chunked(MAX_BUTTONS_PER_ROW)) { chunk ->
            StoreButtonsRow(chunk)
        }
    }
}

@Composable
fun StoreButton(
    store: String,
    image: DrawableResource?,
    url: String,
    modifier: Modifier = Modifier,
    uriHandler: UriHandler = LocalUriHandler.current,
) {
    Button(
        modifier = modifier.height(48.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = secondaryVariant),
        onClick = { uriHandler.openUri(url) },
    ) {
        Row(Modifier.align(Alignment.CenterVertically)) {
            Text(text = store, style = getTypography().subtitle1)
            if (image != null) {
                Spacer(Modifier.width(small))
                Image(
                    modifier = Modifier.size(22.dp),
                    colorFilter = ColorFilter.tint(white),
                    imageVector = vectorResource(image),
                    contentDescription = store.toContentDescription(),
                )
            }
        }
    }
}

@Composable
private fun StoreButtonsRow(storeList: List<StoreTypeModel>) {
    Spacer(modifier = Modifier.size(small))
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = medium),
        horizontalArrangement = Arrangement.spacedBy(small),
    ) {
        storeList.forEach { store ->
            StoreButton(
                store = store.name,
                image = store.image,
                url = store.url,
            )
        }
    }
}