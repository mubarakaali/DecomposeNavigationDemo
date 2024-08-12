package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable
import navigation.bottomnav.BottomNavEvents

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()
    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.BottomNavA,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {

        return when (config) {
            Configuration.BottomNavA -> Child.ScreenA(
                ScreenAComponent(
                    componentContext = context,
                    onNavigateToScreenB = { text ->
                        navigatePushNew(Configuration.BottomNavB(text))
                    }
                )
            )

            is Configuration.BottomNavB -> Child.ScreenB(
                ScreenBComponent(
                    text = config.text,
                    componentContext = context,
                    onGoBack = {
                        navigation.pop()
                    }
                )
            )

            is Configuration.BottomNavC -> Child.ScreenC
            is Configuration.BottomNavD -> TODO()
            is Configuration.BottomNavE -> TODO()
        }
    }


    sealed class Child {
        data class ScreenA(val component: ScreenAComponent) : Child()
        data class ScreenB(val component: ScreenBComponent) : Child()
        data object ScreenC : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object BottomNavA : Configuration()

        @Serializable
        data class BottomNavB(val text: String) : Configuration()

        data object BottomNavC : Configuration()
        data object BottomNavD : Configuration()
        data object BottomNavE : Configuration()
    }

    fun onNavItemClick(item: BottomNavEvents) {
        when (item) {
            is BottomNavEvents.NavAClick -> navigatePushNew(Configuration.BottomNavA)
            is BottomNavEvents.NavBClick -> navigatePushNew(Configuration.BottomNavB("hehehe"))
            is BottomNavEvents.NavCClick -> navigatePushNew(Configuration.BottomNavC)
            is BottomNavEvents.NavDClick -> navigatePushNew(Configuration.BottomNavD)
            is BottomNavEvents.NavEClick -> navigatePushNew(Configuration.BottomNavE)
        }
    }

    private fun navigatePushNew(configuration: Configuration) {
        navigation.bringToFront(configuration)
    }
}