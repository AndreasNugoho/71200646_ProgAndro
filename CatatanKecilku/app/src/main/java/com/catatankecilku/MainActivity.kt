package com.catatankecilku

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.catatankecilku.databinding.ActivityMainBinding
import com.catatankecilku.databinding.MainFragmentBinding


class MainActivity : AppCompatActivity(),MainRunning.MainRunningInteractionListener {
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
        Log.i("Main Activity",viewModel.toString())

        if(savedInstanceState == null){
            val mainRunning = MainRunning.newInstance(this)
            supportFragmentManager.beginTransaction().replace(R.id.container,mainRunning).commitNow()
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
            val taskList = TaskList(listEditText.text.toString())
            viewModel.saveList(taskList)
            tampilDetail(taskList)
        }

        alertBuild.create().show()

    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            if (data != null) {
                viewModel.updateList(data.getParcelableExtra(INTENT_LIST_KEY)!!)
                viewModel.refreshLists()
            }
        }
    }
    private fun tampilDetail(list: TaskList){
        val detailIntent = Intent(this, DetailActivity::class.java)
        detailIntent.putExtra(INTENT_LIST_KEY, list)
        resultLauncher.launch(detailIntent)
    }
    override fun listItemTapped(list: TaskList) {
        tampilDetail(list)
    }
    companion object{
        const val INTENT_LIST_KEY = "list"
        const val REQ_CODE = 123
    }

}