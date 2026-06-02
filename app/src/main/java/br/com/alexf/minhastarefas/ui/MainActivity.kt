package br.com.alexf.minhastarefas.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.alexf.minhastarefas.ui.feature.taskform.TaskFormScreen
import br.com.alexf.minhastarefas.ui.feature.taskslist.TasksListScreen
import br.com.alexf.minhastarefas.ui.feature.taskslist.TasksListUiState
import br.com.alexf.minhastarefas.ui.feature.taskform.TaskFormViewModel
import br.com.alexf.minhastarefas.ui.feature.taskslist.TasksListViewModel
import br.com.alexf.minhastarefas.ui.theme.ExampleRoomTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExampleRoomTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "tasksList"
                ) {
                    composable("tasksList") {
                        val viewModel = koinViewModel<TasksListViewModel>()
                        val uiState by viewModel.uiState
                            .collectAsState(TasksListUiState())
                        TasksListScreen(
                            uiState = uiState,
                            onNewTaskClick = {
                                navController.navigate("taskForm")
                            }
                        )
                    }
                    composable("taskForm") {
                        val viewModel = koinViewModel<TaskFormViewModel>()
                        val uiState by viewModel.uiState.collectAsState()
                        TaskFormScreen(
                            uiState = uiState,
                            onSaveClick = {
                                viewModel.save()
                                navController.popBackStack()
                            })
                    }
                }
            }
        }
    }
}