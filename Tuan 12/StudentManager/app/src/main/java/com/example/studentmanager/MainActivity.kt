package com.example.studentmanager

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var studentListView: ListView
    private lateinit var studentAdapter: ArrayAdapter<String>
    private val students = mutableListOf<StudentModel>(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentListView = findViewById(R.id.studentListView)

        studentAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, students.map { "${it.name} - ${it.studentId}" })
        studentListView.adapter = studentAdapter

        registerForContextMenu(studentListView)

        studentListView.setOnItemClickListener { _, _, position, _ ->
            editStudent(position)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addNew -> {
                val intent = Intent(this, AddOrEditActivity::class.java)
                startActivityForResult(intent, REQUEST_ADD)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.edit -> editStudent(info.position) // Chỉnh sửa
            R.id.remove -> {
                students.removeAt(info.position) // Xóa
                updateStudentList()
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun editStudent(position: Int) {
        val intent = Intent(this, AddOrEditActivity::class.java).apply {
            putExtra("student", students[position])
            putExtra("position", position)
        }
        startActivityForResult(intent, REQUEST_EDIT)
    }

    private fun updateStudentList() {
        studentAdapter.clear()
        studentAdapter.addAll(students.map { "${it.name} - ${it.studentId}" })
        studentAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val student = data.getSerializableExtra("student") as? StudentModel
            val position = data.getIntExtra("position", -1)

            when (requestCode) {
                REQUEST_ADD -> {
                    student?.let { students.add(it) }
                }
                REQUEST_EDIT -> {
                    if (position >= 0 && student != null) {
                        students[position] = student
                    }
                }
            }
            updateStudentList()
        }
    }


    companion object {
        const val REQUEST_ADD = 1
        const val REQUEST_EDIT = 2
    }
}



