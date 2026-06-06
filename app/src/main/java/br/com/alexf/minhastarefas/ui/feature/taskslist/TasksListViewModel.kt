package br.com.alexf.minhastarefas.ui.feature.taskslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alexf.minhastarefas.data.repository.toTask
import br.com.alexf.minhastarefas.domain.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TasksListViewModel(
    private val repository: TaskRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<TasksListUiState> =
        MutableStateFlow(TasksListUiState())
    val uiState = _uiState
        .combine(repository.tasks) { uiState, tasks ->
            uiState.copy(tasks = tasks.map {
                it.toTask()
            })
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = TasksListUiState()
        )

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(onTaskDoneChange = { task ->
                    viewModelScope.launch {
                        if(task.isDone) {
                            repository.reopenTask(task.id)
                        } else {
                            repository.completeTask(task.id)
                        }
                    }
                })
            }
        }
    }

}