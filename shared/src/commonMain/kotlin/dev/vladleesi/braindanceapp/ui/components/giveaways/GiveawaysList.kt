package dev.vladleesi.braindanceapp.ui.components.giveaways

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.vladleesi.braindanceapp.ui.components.StaticText
import dev.vladleesi.braindanceapp.ui.style.Dimens
import dev.vladleesi.braindanceapp.ui.viewmodels.HomeState

@Composable
fun GiveawaysList(
    title: String,
    state: HomeState,
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    when (state) {
        HomeState.Loading -> {
            // TODO: Add skeletons
        }

        is HomeState.Success<*> -> {
            // TODO: Move this header to standalone component for reusing
            Column(modifier = modifier) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    StaticText(
                        text = title,
                        modifier =
                            Modifier
                                .padding(horizontal = Dimens.medium)
                                .align(Alignment.CenterStart),
                        style = MaterialTheme.typography.h2,
                    )
                    Button(
                        onClick = {},
                        modifier = Modifier.align(Alignment.CenterEnd),
                    ) {
                        Text(
                            text = "See all",
                            style = MaterialTheme.typography.subtitle2,
                        )
                    }
                }
                LazyRow(
                    modifier = Modifier.padding(top = Dimens.small),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.small),
                    contentPadding = PaddingValues(horizontal = Dimens.medium),
                ) {
                    items(
                        items = state.entities.filterIsInstance<GiveawayItemModel>(),
                        key = { model -> model.id },
                        contentType = { it },
                    ) { item ->
                        GiveawayItem(
                            item = item,
                            onCardClicked = { id ->
                            },
                        )
                    }
                }
            }
        }

        is HomeState.Error -> {
            // TODO: Add skeletons
        }
    }
}
