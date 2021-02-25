package kz.almaty.satbayevuniversity.ui.references.benefit

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kz.almaty.satbayevuniversity.R
import kz.almaty.satbayevuniversity.databinding.BenefitReferenseActivityBinding
import kz.almaty.satbayevuniversity.ui.references.ReferencesListItem
import kz.almaty.satbayevuniversity.ui.references.history.TestData
import kz.almaty.satbayevuniversity.ui.references.job.JobActivity

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
            ReferencesListItem.addItem(TestData("123","25.02.21","Пособие",1,"0",false), this)
            finish()
        }
    }

    private fun preview(btn_show: Button) {
        btn_show.setOnClickListener {

        }
    }

}
