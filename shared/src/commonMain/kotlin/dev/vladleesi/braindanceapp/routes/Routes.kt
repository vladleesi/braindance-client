package dev.vladleesi.braindanceapp.routes

import androidx.compose.runtime.Composable
import androidx.core.bundle.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.composable
import io.github.aakira.napier.Napier

sealed class Route {
    open val name: String =
        this::class.simpleName.orEmpty()

    @Composable
    abstract fun renderContent(bundle: Bundle?)
}

inline fun <reified T : Route> NavGraphBuilder.registerRoute(
    route: T,
    arguments: List<NamedNavArgument> = emptyList(),
) {
    composable(route = route.name, arguments = arguments) { entry ->
        route.renderContent(entry.arguments)
    }
}

fun NavHostController.navigate(
    route: Route,
    arguments: Map<String, String> = emptyMap(),
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    if (arguments.isEmpty()) {
        navigate(route.name, navOptions, navigatorExtras)
        return
    }
    val routeWithArgs =
        arguments.entries.fold(route.name) { acc, (key, value) ->
            acc.replace("{$key}", value)
        }
    Napier.d("Navigating to $routeWithArgs")
    runCatching {
        navigate(routeWithArgs, navOptions, navigatorExtras)
    }
}