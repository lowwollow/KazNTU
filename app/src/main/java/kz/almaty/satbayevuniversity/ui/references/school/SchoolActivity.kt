package kz.almaty.satbayevuniversity.ui.references.school

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kz.almaty.satbayevuniversity.R
import kz.almaty.satbayevuniversity.ui.references.job.JobActivity

class SchoolActivity : AppCompatActivity() {

    companion object {
        fun genInstance() = SchoolActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.school_referense_activity)
    }
}