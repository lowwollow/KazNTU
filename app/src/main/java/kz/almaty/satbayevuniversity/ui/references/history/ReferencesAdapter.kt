package kz.almaty.satbayevuniversity.ui.references.history

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.almaty.satbayevuniversity.R
import kotlinx.android.synthetic.main.reference_list_item.view.*
import kz.almaty.satbayevuniversity.ui.references.ReferencesListItem


//TODO change to actual data later

class ReferencesAdapter (var items: MutableList<TestData>): RecyclerView.Adapter<ReferencesAdapter.ViewHolder>(){
    lateinit var item: TestData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.reference_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        item = items[position]
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var testData: TestData
        fun bind(item: TestData){
            this.testData = item
            itemView.reference_id.text = testData.reference_id
            itemView.reference_date.text = testData.reference_date
            itemView.reference_response.text = testData.reference_responce
            itemView.reference_status.text = testData.reference_status.toString()
            if (itemView.reference_status.text == "1")
                itemView.reference_status.setBackgroundColor(Color.parseColor("#66bb6a"))
            else
                itemView.reference_status.setBackgroundColor(Color.parseColor("#ef5350"))
            itemView.reference_type.text = testData.reference_type
        }

        override fun onClick(v: View?) {
            v?.let{
                val context = v.context
                ReferencesListItem.removeItem(item,context)
            }
        }
    }

    fun updateItems(items: List<TestData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}
