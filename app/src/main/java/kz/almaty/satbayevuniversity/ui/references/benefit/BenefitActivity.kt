package kz.almaty.satbayevuniversity.ui.references.benefit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kz.almaty.satbayevuniversity.R
import kz.almaty.satbayevuniversity.databinding.BenefitReferenseActivityBinding
import kz.almaty.satbayevuniversity.ui.references.ReferencesListItem
import kz.almaty.satbayevuniversity.ui.references.ReferenseWebView
import kz.almaty.satbayevuniversity.ui.references.history.TestData
import kz.almaty.satbayevuniversity.ui.references.job.JobActivity
import java.text.SimpleDateFormat
import java.util.*

class BenefitActivity : AppCompatActivity() {

    lateinit var binding: BenefitReferenseActivityBinding
    lateinit var btn_save: Button
    lateinit var btn_show: Button

    companion object {
        fun genInstance() = BenefitActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.benefit_referense_activity)
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
            val uniqueID = UUID.randomUUID().toString()
            val sdf = SimpleDateFormat("dd.M.yyyy")
            val currentDate = sdf.format(Date())
            ReferencesListItem.addItem(TestData(uniqueID,currentDate,"Пособие",1,"0",false), this)
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
