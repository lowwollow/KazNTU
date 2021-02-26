package kz.almaty.satbayevuniversity.ui.references.school

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kz.almaty.satbayevuniversity.R
import kz.almaty.satbayevuniversity.databinding.JobReferenseActivityBinding
import kz.almaty.satbayevuniversity.databinding.SchoolReferenseActivityBinding
import kz.almaty.satbayevuniversity.ui.references.ReferencesListItem
import kz.almaty.satbayevuniversity.ui.references.ReferenseWebView
import kz.almaty.satbayevuniversity.ui.references.history.TestData
import kz.almaty.satbayevuniversity.ui.references.job.JobActivity
import java.util.zip.Inflater

class SchoolActivity : AppCompatActivity() {

    private lateinit var binding: SchoolReferenseActivityBinding
    lateinit var btn_save: Button
    lateinit var btn_show: Button

    companion object {
        fun genInstance() = SchoolActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.school_referense_activity)
        btn_save = binding.btnSave
        btn_show = binding.btnShow
        setUpButtons(btn_save, btn_show)
    }

    private fun setUpButtons(btn_save: Button, btn_show: Button) {
        save(btn_save)
        preview(btn_show)
    }


    private fun save(btn_save: Button) {
        btn_save.setOnClickListener {
            ReferencesListItem.addItem(TestData("123","25.02.21","Школа",0,"0",false), this)
            finish()
        }
    }

    private fun preview(btn_show: Button) {
        btn_show.setOnClickListener {
            val intent = Intent(this, ReferenseWebView.newInstanse()::class.java)
            startActivity(intent)
        }
    }
}
