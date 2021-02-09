package kz.almaty.satbayevuniversity.ui.references.history

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kz.almaty.satbayevuniversity.R
import kz.almaty.satbayevuniversity.ui.HomeActivity
import kz.almaty.satbayevuniversity.ui.references.benefit.BenefitActivity
import kz.almaty.satbayevuniversity.ui.references.job.JobActivity
import kz.almaty.satbayevuniversity.ui.references.school.SchoolActivity
import kotlin.math.log

class ReferencesFragment : Fragment() {

    lateinit var toolbar: Toolbar
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.referense_fragment, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.item_job -> stratNewActivity(JobActivity.genInstance())
            R.id.item_school -> stratNewActivity(SchoolActivity.genInstance())
            R.id.item_benefit -> stratNewActivity(BenefitActivity.genInstance())
        }
        return true
    }

    companion object {
        fun newInstanse() = ReferencesFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar = view.findViewById(R.id.references_toolbar) as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.references)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onResume() {
        super.onResume()
        toolbar.setNavigationOnClickListener() {
            (activity as HomeActivity).OpenToggleNavMenu()
        }
    }

    private fun stratNewActivity(activity: Activity){
        val intent = Intent(context, activity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}