package com.example.expensetracker

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.room.Room

class ChecklistActivity : AppCompatActivity() {
    private var listOfChecklists = ArrayList<Checklist>()
    private lateinit var adapter: ChecklistAdapter
    private lateinit var checklistDao: ChecklistDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklists)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "listingdatabase"
        ).allowMainThreadQueries().enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration().build()

        checklistDao = db.checklistDao()

        listOfChecklists = checklistDao.getAll() as ArrayList<Checklist>

        val listingList = findViewById<ListView>(R.id.listing_list)
        adapter = ChecklistAdapter(this, listOfChecklists, db)
        listingList.adapter = adapter

        val createButton = findViewById<Button>(R.id.button_create_checklist)
        createButton.setOnClickListener {
            toListCreation(adapter, checklistDao, listOfChecklists)
        }
    }

    override fun onResume() {
        super.onResume()
        listOfChecklists.clear()
        listOfChecklists.addAll(checklistDao.getAll() as ArrayList<Checklist>)
        adapter.notifyDataSetChanged()
    }

    fun toListCreation(
        adapter: ChecklistAdapter,
        checklistDao: ChecklistDao,
        checklists: ArrayList<Checklist>
    ) {
        val dialog = Dialog(this)
        dialog.setTitle("Create new checklist")
        dialog.setContentView(R.layout.checklist_input_box)
        val createButton = dialog.findViewById<Button>(R.id.create_checklist_button)
        createButton.setOnClickListener {
            val checklistNameInput = dialog.findViewById<EditText>(R.id.checklist_name_input)
            val newChecklist = Checklist(id = 0, name = checklistNameInput.text.toString())
            checklistDao.insertAll(newChecklist)
            checklists.add(newChecklist)
            adapter.notifyDataSetChanged()

            dialog.dismiss()
        }
        dialog.show()
    }
}
