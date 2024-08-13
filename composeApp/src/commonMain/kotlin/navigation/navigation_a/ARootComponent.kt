package navigation.navigation_a

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import kotlinx.serialization.Serializable
import navigation.ScreenAComponent
import navigation.ScreenBComponent

class ARootComponent(componentContext: ComponentContext):ComponentContext by componentContext{

    val navigationA = StackNavigation<ARootConfiguration>()

    val childStack = childStack(
        source = navigationA,
        serializer = ARootConfiguration.serializer(),
        initialConfiguration = ARootConfiguration.RootConfigA,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: ARootConfiguration,
        context: ComponentContext
    ): ChildA {
        return when(config){
            is ARootConfiguration.RootConfigA ->
                ChildA.ScreenChildA(
                    ScreenAComponent(
                        componentContext = context
                        ,onNavigateToScreenB = {
                            navigationA.bringToFront(ARootConfiguration.ScreenBFromA(it))
                        }
                    )
                )

           is  ARootConfiguration.ScreenBFromA ->
               ChildA.ScreenChildB(ScreenBComponent(text = config.text,
                componentContext = context,
                onGoBack = {
                    navigationA.pop()
                }))
            is ARootConfiguration.ScreenCFromA -> TODO()
            is ARootConfiguration.ScreenDFromA -> TODO()
        }
    }

    sealed class ChildA{
        data class ScreenChildA(val component: ScreenAComponent):ChildA()
        data class ScreenChildB(val component: ScreenBComponent):ChildA()
    }

    @Serializable
    sealed class ARootConfiguration{
        object RootConfigA:ARootConfiguration()
        data class ScreenBFromA(val text:String):ARootConfiguration()
        object ScreenCFromA:ARootConfiguration()
        object ScreenDFromA:ARootConfiguration()
    }
}