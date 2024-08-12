
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import navigation.RootComponent
import navigation.bottomnav.navItems
import screens.ScreenA
import screens.ScreenB
import screens.ScreenC

@Composable
fun App(root: RootComponent) {
    MaterialTheme {
        val childStack by root.childStack.subscribeAsState()

        Scaffold(
            topBar = {
                Text("Top Bar")
            },
            content = {
                Children(
                    stack = childStack,
                    animation = stackAnimation(slide())
                ) { child ->

                    when (val instance = child.instance) {
                        is RootComponent.Child.ScreenA -> ScreenA(instance.component)
                        is RootComponent.Child.ScreenB -> ScreenB(
                            instance.component.text,
                            instance.component
                        )
                        is RootComponent.Child.ScreenC -> ScreenC("Alert Dialog Screen Sample")
                    }
                }
            },
            bottomBar = {
               Surface (
                   modifier = Modifier
                       .height(60.dp)
                       .fillMaxWidth(),
                   shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                   color = Color.LightGray,
               ) {
                   LazyRow(
                       modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
                       horizontalArrangement = Arrangement.SpaceBetween,
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       itemsIndexed(navItems) {index, it ->
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .clickable {
                                       if (index<3)
                                           root.onNavItemClick(it.route)
                                   },
                               text = it.title,
                               textAlign = TextAlign.Center
                           )
                       }
                   }
               }


            })


    }
}