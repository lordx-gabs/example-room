package br.com.alexf.minhastarefas.ui.feature.taskslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alexf.minhastarefas.data.repository.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TasksListViewModel(
    private val repository: TasksRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<TasksListUiState> =
        MutableStateFlow(TasksListUiState())
    val uiState
        get() = _uiState
            .combine(repository.tasks) { uiState, tasks ->
                uiState.copy(tasks = tasks)
            }

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(onTaskDoneChange = { task ->
                    repository.toggleIsDone(task)
                })
            }
        }
    }

}