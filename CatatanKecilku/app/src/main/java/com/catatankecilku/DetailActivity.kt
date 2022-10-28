package com.catatankecilku

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.catatankecilku.databinding.DetailActivityBinding
import com.catatankecilku.ui.main.DetailFragment
import com.catatankecilku.ui.main.DetailViewModel

class DetailActivity : AppCompatActivity() {

    lateinit var list: TaskList
    lateinit var binding: DetailActivityBinding
    lateinit var viewModel: DetailViewModel
    lateinit var fragment: DetailFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!

        binding.addTaskButton.setOnClickListener {
            tampilDialog()
        }

        title = viewModel.list.nama

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_container, DetailFragment.newInstance())
                .commitNow()
        }
    }
    private fun tampilDialog(){
        val dialogEditText = EditText(this)
        dialogEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle(R.string.to_add)
            .setView(dialogEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->
                // 3
                val task = dialogEditText.text.toString()
                // 4
                viewModel.addTask(task)
                //5
                dialog.dismiss()
            }
            //6
            .create()
            .show()

    }
    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, viewModel.list)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }
}