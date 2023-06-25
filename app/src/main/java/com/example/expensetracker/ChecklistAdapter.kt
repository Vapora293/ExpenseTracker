package com.example.expensetracker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView

class ChecklistAdapter(
    private val context: Context,
    private val checklists: ArrayList<Checklist>,
    db: AppDatabase
) : ArrayAdapter<Checklist>(context, 0, checklists) {
    private val checklistDao = db.checklistDao()
    private val expenseItemDao = db.expenseItemDao()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.checklist_item, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val checklistName = checklists[position].name
        val checklistBalance = expenseItemDao.getBalanceSumByChecklist(checklists[position].id.toString())
        holder.listName.text = checklistName
        holder.current_balance.text = checklistBalance.toString() + "€"

        val colorRes = if (checklistBalance >= 0) {
            context.resources.getColor(android.R.color.holo_red_dark)
        } else {
            context.resources.getColor(android.R.color.holo_green_dark)
        }
        holder.current_balance.setTextColor(colorRes)


        view?.setOnClickListener {
            val selectedChecklist = checklists[position]

            val intent = Intent(context, ExpenseItemActivity::class.java)
            intent.putExtra("listingId", selectedChecklist.id.toString())
            context.startActivity(intent)
        }

        view?.findViewById<ImageButton>(R.id.delete_checklist_button)?.setOnClickListener {
            checklistDao.delete(checklists[position])
            checklists.remove(checklists[position])
            this.notifyDataSetChanged()
        }

        return view!!
    }

    private class ViewHolder(view: View) {
        val listName: TextView = view.findViewById(R.id.list_name)
        val current_balance: TextView = view.findViewById(R.id.current_balance)
    }
}
