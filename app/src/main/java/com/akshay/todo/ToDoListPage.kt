package com.akshay.todo

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ToDoListPage(viewModel: ToDoViewModel, modifier: Modifier = Modifier) {

    val toDoList by viewModel.toDoList.observeAsState()
    var inputText by rememberSaveable { mutableStateOf("") }
    var selectedTask by remember { mutableStateOf<ToDoModel?>(null) }
    var editTask by remember { mutableStateOf<ToDoModel?>(null) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Add a new task") },
                placeholder = { Text("e.g. Walk the dog") },
                trailingIcon = {
                    if (inputText.isNotBlank()) {
                        IconButton(onClick = { inputText = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear text")
                        }
                    }
                },
                maxLines = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Button(
                onClick = {
                    if (inputText.isNotEmpty()) {
                        viewModel.addToDo(inputText)
                        inputText = ""
                    } else {
                        Toast.makeText(context, "Task cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) { Text("Add") }
        }

        if (toDoList.isNullOrEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                text = "No tasks found",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        } else {
            LazyColumn {
                itemsIndexed(toDoList!!) { _, item ->
                    TodoItem(
                        item = item,
                        onDelete = { viewModel.deleteToDo(item.id) },
                        onClick = { selectedTask = it }, // Show dialog on click
                        onEdit = { editTask = it }
                    )
                }
            }
        }

        selectedTask?.let { task ->
            TaskDetailsDialog(task = task, onDismiss = { selectedTask = null })
        }

        editTask?.let { task ->
            EditTaskDialog(
                task = task,
                onDismiss = { editTask = null },
                onUpdate = { viewModel.updateToDo(it) }
            )
        }
    }
}