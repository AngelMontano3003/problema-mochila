package com.itcm.mochilap

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.itcm.mochilap.view.MainView

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Problema de la Mochila") {
        MainView()
    }
}