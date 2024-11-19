package vn.edu.hust.studentman

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

  private val students = mutableListOf(
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

  private lateinit var studentAdapter: StudentAdapter
  private var recentlyDeletedStudent: StudentModel? = null
  private var recentlyDeletedPosition: Int = -1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    studentAdapter = StudentAdapter(students)

    findViewById<RecyclerView>(R.id.recycler_view_students).run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }

    findViewById<Button>(R.id.btn_add_new).setOnClickListener {
      showAddStudentDialog()
    }

    setupStudentItemEvents()
  }

  private fun setupStudentItemEvents() {
    studentAdapter.setOnEditClickListener { position ->
      onEditClicked(position)
    }

    studentAdapter.setOnDeleteClickListener { position ->
      onDeleteClicked(position)
    }
  }

  private fun showAddStudentDialog() {
    val dialogView = layoutInflater.inflate(R.layout.dialog_student_form, null)
    val dialog = AlertDialog.Builder(this)
      .setTitle("Add Student")
      .setView(dialogView)
      .setPositiveButton("Add") { _, _ ->
        val nameInput = dialogView.findViewById<TextInputEditText>(R.id.input_student_name).text.toString()
        val idInput = dialogView.findViewById<TextInputEditText>(R.id.input_student_id).text.toString()
        if (nameInput.isNotBlank() && idInput.isNotBlank()) {
          students.add(StudentModel(nameInput, idInput))
          studentAdapter.notifyItemInserted(students.size - 1)
        }
      }
      .setNegativeButton("Cancel", null)
      .create()
    dialog.show()
  }

  private fun onEditClicked(position: Int) {
    val student = students[position]

    val dialogView = layoutInflater.inflate(R.layout.dialog_student_form, null).apply {
      findViewById<TextInputEditText>(R.id.input_student_name).setText(student.studentName)
      findViewById<TextInputEditText>(R.id.input_student_id).setText(student.studentId)
    }

    AlertDialog.Builder(this)
      .setTitle("Edit Student")
      .setView(dialogView)
      .setPositiveButton("Save") { _, _ ->
        val updatedName = dialogView.findViewById<TextInputEditText>(R.id.input_student_name).text.toString()
        val updatedId = dialogView.findViewById<TextInputEditText>(R.id.input_student_id).text.toString()
        if (updatedName.isNotBlank() && updatedId.isNotBlank()) {
          students[position] = StudentModel(updatedName, updatedId)
          studentAdapter.notifyDataSetChanged() // Làm mới toàn bộ danh sách
        }
      }
      .setNegativeButton("Cancel", null)
      .create()
      .show()
  }

  private fun onDeleteClicked(position: Int) {
    recentlyDeletedStudent = students[position]
    recentlyDeletedPosition = position

    students.removeAt(position)

    studentAdapter.notifyDataSetChanged()

    Snackbar.make(findViewById(R.id.main), "Student deleted", Snackbar.LENGTH_LONG)
      .setAction("Undo") {
        students.add(recentlyDeletedPosition, recentlyDeletedStudent!!)
        studentAdapter.notifyDataSetChanged()
      }
      .show()
  }

}
