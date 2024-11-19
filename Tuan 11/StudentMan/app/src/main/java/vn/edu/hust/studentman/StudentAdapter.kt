package vn.edu.hust.studentman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
  private val students: List<StudentModel>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

  private var onEditClickListener: ((Int) -> Unit)? = null
  private var onDeleteClickListener: ((Int) -> Unit)? = null

  fun setOnEditClickListener(listener: (Int) -> Unit) {
    onEditClickListener = listener
  }

  fun setOnDeleteClickListener(listener: (Int) -> Unit) {
    onDeleteClickListener = listener
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(
      R.layout.layout_student_item, parent, false
    )
    return StudentViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]
    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId

    holder.imageEdit.setOnClickListener {
      onEditClickListener?.invoke(position)
    }

    holder.imageRemove.setOnClickListener {
      onDeleteClickListener?.invoke(position)
    }
  }

  override fun getItemCount(): Int = students.size

  class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
    val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)
  }
}
