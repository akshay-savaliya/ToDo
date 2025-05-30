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
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.akshay.todo.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ToDoViewModel()
        setContent {
            var isDarkTheme by rememberSaveable { mutableStateOf(false) }
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
                                IconButton(onClick = { isDarkTheme = !isDarkTheme }) {
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