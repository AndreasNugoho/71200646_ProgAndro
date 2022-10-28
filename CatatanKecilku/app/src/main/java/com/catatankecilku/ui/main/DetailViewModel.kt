package com.catatankecilku.ui.main

import androidx.lifecycle.ViewModel
import com.catatankecilku.TaskList

class DetailViewModel : ViewModel() {
    lateinit var onTaskAdded: (() -> Unit)

    lateinit var list: TaskList

    fun addTask(task: String) {
        list.task.add(task)
        onTaskAdded.invoke()
    }
}