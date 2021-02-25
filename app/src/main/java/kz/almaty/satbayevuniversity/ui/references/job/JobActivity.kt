package kz.almaty.satbayevuniversity.ui.references.job

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kz.almaty.satbayevuniversity.R
import kz.almaty.satbayevuniversity.databinding.JobReferenseActivityBinding
import kz.almaty.satbayevuniversity.ui.references.ReferencesListItem
import kz.almaty.satbayevuniversity.ui.references.history.TestData

class JobActivity : AppCompatActivity() {

    lateinit var img: ImageView
    lateinit var btn_save: Button
    lateinit var btn_show: Button

    private lateinit var binding: JobReferenseActivityBinding

    companion object {
        fun genInstance() = JobActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.job_referense_activity)
        img = binding.bottomImg
        btn_save = binding.buttonSave
        btn_show = binding.buttonShow

        setUpButtons(btn_save, btn_show)
    }

    private fun setUpButtons(btn_save: Button, btn_show: Button) {
        save(btn_save)
        preview(btn_show)
    }


    private fun save(btn_save: Button) {
        btn_save.setOnClickListener {
            ReferencesListItem.addItem(TestData("123","25.02.21","Учеба",0,"0",false), this)
            finish()
        }
    }

    private fun preview(btn_show: Button) {
        btn_show.setOnClickListener {

        }
    }

}
