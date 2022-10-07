package com.catatankecilku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.catatankecilku.databinding.ActivityMainBinding
import com.catatankecilku.databinding.MainFragmentBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this)))
            .get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.container,MainRunning.newInstance()).commitNow()
        }
        binding.floatButton.setOnClickListener{
            tampilDialog()
        }
    }
    private fun tampilDialog(){
        val dialogTitle = getString(R.string.nama_list)
        val createButton = getString(R.string.create_list)

        val alertBuild = AlertDialog.Builder(this)
        val listEditText = EditText(this)
        listEditText.inputType = InputType.TYPE_CLASS_TEXT

        alertBuild.setTitle(dialogTitle)
        alertBuild.setView(listEditText)

        alertBuild.setPositiveButton(createButton) { dialog, _ ->
            dialog.dismiss()
            viewModel.saveList(TaskList(listEditText.text.toString()))
        }

        alertBuild.create().show()

    }
}