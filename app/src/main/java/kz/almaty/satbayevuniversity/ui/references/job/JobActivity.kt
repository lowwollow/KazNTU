package kz.almaty.satbayevuniversity.ui.references.job

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kz.almaty.satbayevuniversity.R

class JobActivity : AppCompatActivity() {

    companion object {
        fun genInstance() = JobActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.job_referense_activity)
    }

}