package com.example.expensetracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ExpenseItemAdapter: BaseAdapter {
    private var context: Context
    private var expenseItems: ArrayList<ExpenseItem>

    constructor(
        context: Context,
        expenseItems: ArrayList<ExpenseItem>) : super() {
            this.context = context
            this.expenseItems = expenseItems
        }

    override fun getCount(): Int {
        return expenseItems.size
    }

    override fun getItem(position: Int): Any {
        return expenseItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = layoutInflater.inflate(R.layout.expense_item, null, true)
        val expenseNameTextView = itemView.findViewById<TextView>(R.id.expense_name) as TextView
        val balanceTextView =
            itemView.findViewById<TextView>(R.id.current_expense_balance) as TextView
        expenseNameTextView.text = expenseItems[position].name + " - " + expenseItems[position].category
        balanceTextView.text = expenseItems[position].balance.toString()

        return itemView
    }

}