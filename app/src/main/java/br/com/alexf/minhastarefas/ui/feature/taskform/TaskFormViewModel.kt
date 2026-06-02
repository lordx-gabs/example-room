package br.com.alexf.minhastarefas.ui.feature.taskform

import androidx.lifecycle.ViewModel
import br.com.alexf.minhastarefas.data.repository.TasksRepository
import br.com.alexf.minhastarefas.domain.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskFormViewModel(
    private val repository: TasksRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<TaskFormUiState> =
        MutableStateFlow(TaskFormUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onTitleChange = { title ->
                    _uiState.update {
                        it.copy(title = title)
                    }
                },
                onDescriptionChange = { description ->
                    _uiState.update {
                        it.copy(description = description)
                    }
                }
            )
        }
    }

    fun save() {
        with(_uiState.value) {
            repository.save(
                Task(
                    title = title,
                    description = description
                )
            )
        }

    }

}