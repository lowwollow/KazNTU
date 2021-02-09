package kz.almaty.satbayevuniversity.ui.references.benefit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kz.almaty.satbayevuniversity.R
import kz.almaty.satbayevuniversity.ui.references.job.JobActivity

class BenefitActivity : AppCompatActivity() {
    companion object {
        fun genInstance() = BenefitActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.benefit_referense_activity)
    }
}