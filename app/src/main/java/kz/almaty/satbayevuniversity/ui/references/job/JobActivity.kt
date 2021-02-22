package kz.almaty.satbayevuniversity.ui.references.job

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kz.almaty.satbayevuniversity.R
import kz.almaty.satbayevuniversity.databinding.JobReferenseActivityBinding

class JobActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var img: ImageView
    private lateinit var binding: JobReferenseActivityBinding

    companion object {
        fun genInstance() = JobActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.job_referense_activity)
        img = binding.bottomImg

    }

    private fun setUpImageView(imageView: ImageView){
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive){
            imageView.visibility = View.GONE
        }else{
            imageView.visibility = View.VISIBLE
        }
    }

}