package com.catatankecilku.ui.main

import android.app.LauncherActivity.ListItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catatankecilku.TaskList
import com.catatankecilku.databinding.ItemViewHolderBinding

class ItemsRecyclerViewAdapter(var list:TaskList):RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.textViewTask.text = list.task[position]
    }

    override fun getItemCount(): Int {
        return list.task.size
    }

}