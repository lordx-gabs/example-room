package br.com.alexf.minhastarefas.ui.feature.taskslist

import br.com.alexf.minhastarefas.domain.models.Task

data class TasksListUiState(
    val tasks: List<Task> = emptyList(),
    val onTaskDoneChange: (Task) -> Unit = {},
)