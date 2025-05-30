package com.akshay.todo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.akshay.todo.ui.theme.ToDoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ToDoViewModel()
        val context = applicationContext
        setContent {
            val scope = rememberCoroutineScope()
            val isDarkThemeFlow = remember { ThemePreferenceManager.getThemePreference(context) }
            val isDarkTheme by isDarkThemeFlow.collectAsState(initial = false)
            ToDoTheme(darkTheme = isDarkTheme) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {

                        TopAppBar(
                            title = {
                                Column {
                                    Text(
                                        text = "ToDo",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Stay organized!",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(
                                            alpha = 0.7f
                                        )
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = {
                                    scope.launch {
                                        ThemePreferenceManager.saveThemePreference(
                                            context,
                                            !isDarkTheme
                                        )
                                    }
                                }) {
                                    Icon(
                                        painter = if (isDarkTheme) {
                                            painterResource(id = R.drawable.outline_light_mode_24)
                                        } else {
                                            painterResource(id = R.drawable.outline_dark_mode_24)
                                        },
                                        contentDescription = if (isDarkTheme) "Switch to Light Mode" else "Switch to Dark Mode"

                                    )
                                }
                            }
                        )

                    }) { innerPadding ->
                    ToDoListPage(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}