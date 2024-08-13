package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import kotlinx.serialization.Serializable
import navigation.bottomnav.BottomNavEvents
import navigation.navigation_a.ARootComponent

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<RootConfiguration>()
    val childStack = childStack(
        source = navigation,
        serializer = RootConfiguration.serializer(),
        initialConfiguration = RootConfiguration.BottomNavA,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: RootConfiguration,
        context: ComponentContext
    ): Child {

        return when (config) {
            RootConfiguration.BottomNavA -> Child.ScreenARoot(
                ARootComponent(
                    componentContext = context,
//                    onNavigateToScreenB = { text ->
//                        navigatePushNew(RootConfiguration.BottomNavB(text))
//                    }
                )
            )

            is RootConfiguration.BottomNavB -> Child.ScreenB(
                ScreenBComponent(
                    text = config.text,
                    componentContext = context,
                    onGoBack = navigation::pop
                )
            )

            is RootConfiguration.BottomNavC -> Child.ScreenC
            is RootConfiguration.BottomNavD -> TODO()
            is RootConfiguration.BottomNavE -> TODO()
        }
    }


    sealed class Child {
        data class ScreenARoot(val component: ARootComponent) : Child()
//        data class ScreenA(val component: ScreenAComponent) : Child()
        data class ScreenB(val component: ScreenBComponent) : Child()
        data object ScreenC : Child()
    }

    @Serializable
    sealed class RootConfiguration {
        @Serializable
        data object BottomNavA : RootConfiguration()

        @Serializable
        data class BottomNavB(val text: String) : RootConfiguration()
        @Serializable
        data object BottomNavC : RootConfiguration()
        data object BottomNavD : RootConfiguration()
        data object BottomNavE : RootConfiguration()
    }

    fun onNavItemClick(item: BottomNavEvents) {
        when (item) {
            is BottomNavEvents.NavAClick -> navigatePushNew(RootConfiguration.BottomNavA)
            is BottomNavEvents.NavBClick -> navigatePushNew(RootConfiguration.BottomNavB("hehehe"))
            is BottomNavEvents.NavCClick -> navigatePushNew(RootConfiguration.BottomNavC)
            is BottomNavEvents.NavDClick -> navigatePushNew(RootConfiguration.BottomNavD)
            is BottomNavEvents.NavEClick -> navigatePushNew(RootConfiguration.BottomNavE)
        }
    }

    private fun navigatePushNew(rootConfiguration: RootConfiguration) {
        navigation.bringToFront(rootConfiguration)
    }
}