package br.com.droidchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.droidchat.navigation.ChatNavHost
import br.com.droidchat.ui.ChatApp
import br.com.droidchat.ui.theme.DroidChatTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DroidChatTheme {
                ChatApp()
            }
        }
    }
}