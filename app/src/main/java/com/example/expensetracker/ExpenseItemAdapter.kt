package com.example.expensetracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView

class ExpenseItemAdapter : BaseAdapter {
    private var context: Context
    private var expenseItems: ArrayList<ExpenseItem>
    private var expenseItemDao: ExpenseItemDao

    constructor(
        context: Context,
        expenseItems: ArrayList<ExpenseItem>,
        expenseItemDao: ExpenseItemDao
    ) : super() {
        this.context = context
        this.expenseItems = expenseItems
        this.expenseItemDao = expenseItemDao
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
        val expenseItem = expenseItems[position]
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = layoutInflater.inflate(R.layout.expense_item, null, true)
        val expenseNameTextView = itemView.findViewById<TextView>(R.id.expense_name) as TextView
        val balanceTextView =
            itemView.findViewById<TextView>(R.id.current_expense_balance) as TextView

        expenseNameTextView.text =
            expenseItems[position].name + " - " + expenseItems[position].category
        balanceTextView.text = expenseItems[position].balance.toString() + "€"

        if (expenseItems[position].balance > 0) {
            balanceTextView.setTextColor(context.resources.getColor(android.R.color.holo_red_dark))
        } else if (expenseItems[position].balance < 0) {
            balanceTextView.setTextColor(context.resources.getColor(android.R.color.holo_green_dark))
        }

        itemView.findViewById<ImageButton>(R.id.cross_out_button).setOnClickListener {
            val updatedExpenseItem = expenseItem.copy(isCrossedOut = !expenseItem.isCrossedOut)
            expenseItems[position] = updatedExpenseItem
            expenseItemDao.update(updatedExpenseItem)

            expenseNameTextView.paint.isStrikeThruText = updatedExpenseItem.isCrossedOut
            balanceTextView.paint.isStrikeThruText = updatedExpenseItem.isCrossedOut

            expenseNameTextView.invalidate()
            balanceTextView.invalidate()
        }

        expenseNameTextView.paint.isStrikeThruText = expenseItem.isCrossedOut
        balanceTextView.paint.isStrikeThruText = expenseItem.isCrossedOut

        return itemView
    }


}