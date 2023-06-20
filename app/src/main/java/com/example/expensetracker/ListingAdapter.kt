package com.example.expensetracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListingAdapter: BaseAdapter {
    private var context: Context
    private val listingNames: ArrayList<String?>

    constructor(
        context: Context,
        listingNames: ArrayList<String?>
    ) : super() {
        this.context = context
        this.listingNames = listingNames
    }

    override fun getCount(): Int {
        return listingNames.size
    }

    override fun getItem(position: Int): String? {
        return listingNames[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = layoutInflater.inflate(R.layout.listing_list_item, null, true)
        val listingTextView = itemView.findViewById<TextView>(R.id.list_name)
        val evaluationTextView = itemView.findViewById<TextView>(R.id.current_balance)
        listingTextView.text = getItem(position).toString()
        evaluationTextView.text = "+test"
        return itemView
    }

}