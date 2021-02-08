package kz.almaty.satbayevuniversity.ui.references

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kz.almaty.satbayevuniversity.R

class ReferencesFragment : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.referense_fragment, container, false)
        val toolbar = view.findViewById(R.id.references_toolbar) as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        return view
    }

    companion object {
        fun newInstanse() = ReferencesFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        (activity as AppCompatActivity).actionBar?.setTitle(getString(R.string.references))
        Log.d("TESTING", "onActivityCreated: " + (activity as AppCompatActivity).actionBar)
    }
}