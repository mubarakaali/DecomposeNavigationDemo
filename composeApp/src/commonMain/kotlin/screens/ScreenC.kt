package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import dialog.SaveStateDialog
import navigation.ScreenAComponent
import navigation.ScreenAEvent
import navigation.ScreenBComponent

@Composable
fun ScreenC(text: String) {

    var isDialogOpen by remember { mutableStateOf(false) }

    if (isDialogOpen) {
        SaveStateDialog(
            onSaveState = {},
            onExitApplication = {},
            onDismiss = { isDialogOpen = false },
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Screen C: $text")
        Button(onClick = {
            isDialogOpen = true
        }) {
            Text("Show Dialog")
        }
    }
}