package kz.almaty.satbayevuniversity.ui.references.history

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.referense_fragment.*
import kz.almaty.satbayevuniversity.R
import kz.almaty.satbayevuniversity.ui.HomeActivity
import kz.almaty.satbayevuniversity.ui.references.ReferencesListItem
import kz.almaty.satbayevuniversity.ui.references.benefit.BenefitActivity
import kz.almaty.satbayevuniversity.ui.references.job.JobActivity
import kz.almaty.satbayevuniversity.ui.references.school.SchoolActivity

class ReferencesFragment : Fragment() {

    lateinit var toolbar: Toolbar
    lateinit var adapter: ReferencesAdapter
    lateinit var viewModel: ReferencesListItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.referense_fragment, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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
        reference_list.layoutManager = LinearLayoutManager(context)
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
        setupRecyclerView()
    }

    private fun stratNewActivity(activity: Activity) {
        val intent = Intent(context, activity::class.java)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        adapter = ReferencesAdapter(mutableListOf())
        val mutableList = context?.let { ReferencesListItem.getListOfItems(it) }!!
        adapter.updateItems(mutableList)
        reference_list.adapter = adapter
    }
}
