package navigation.navigation_a

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import screens.ScreenA
import screens.ScreenB

@Composable
fun ARootsChildrens(aRootComponent: ARootComponent) {

    val childStack by aRootComponent.childStack.subscribeAsState()

    Children(
        stack = childStack
    ) { child ->
        when (val instance = child.instance) {
            is ARootComponent.ChildA.ScreenChildA -> ScreenA(instance.component)

            is ARootComponent.ChildA.ScreenChildB -> ScreenB(instance.component.text, instance.component)
        }

    }
}