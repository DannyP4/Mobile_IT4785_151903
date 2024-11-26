package vn.edu.hust.activityexamples

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // TODO: Thiet lap thuoc tinh thanh action bar
//    supportActionBar?.title = "Hello"
//    supportActionBar?.setDisplayShowHomeEnabled(true)
//    supportActionBar?.setIcon(R.mipmap.ic_launcher)
//    supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient_background, null))

    // TODO: Mo activity trong cung ung dung bang intent xac dinh
    findViewById<Button>(R.id.button_open).setOnClickListener {
      val intent = Intent(this, SecondActivity::class.java)
      intent.putExtra("param1", 123)
      intent.putExtra("param2", 3.14)
      intent.putExtra("param3", "hello")
      startActivity(intent)
    }

    // TODO: Mo activity trong ung dung khac dung intent ngam dinh
    findViewById<Button>(R.id.button_open_other).setOnClickListener {
//      val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:098764321"))
//      val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Back+Khoa"))
//      val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://hust.edu.vn"))

      val intent = Intent(Intent.ACTION_SEND)
      intent.type = "text/plain"
      intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("test1@gmail.com", "test2@gmail.com"))
      intent.putExtra(Intent.EXTRA_SUBJECT, "Email title")
      intent.putExtra(Intent.EXTRA_TEXT, "This is the email content")

      startActivity(intent)
    }

    val textResult = findViewById<TextView>(R.id.text_result)

    // TODO: Su dung launcher de mo activity va xu ly ket qua tra ve
    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
      { it: ActivityResult ->
        if (it.resultCode == RESULT_OK) {
          val hoten = it.data?.getStringExtra("hoten")
          val mssv = it.data?.getStringExtra("mssv")
          textResult.text = "$hoten - $mssv"
        } else {
          textResult.text = "CANCELLED"
        }
      })

    findViewById<Button>(R.id.button_add_student).setOnClickListener {
      val intent = Intent(this, AddStudentActivity::class.java)
      launcher.launch(intent)
    }

    // TODO: Thiet lap context menu cho doi tuong ImageView
//    val imageView = findViewById<ImageView>(R.id.imageView)
//    registerForContextMenu(imageView)


    val items = mutableListOf<String>()
    for (i in 1..50)
      items.add("Item $i")
    val listView = findViewById<ListView>(R.id.listView)
    listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

    // TODO: Thiet lap context menu cho doi tuong ListView
    registerForContextMenu(listView)
  }

  // TODO: Ham khoi tao cho context menu
  override fun onCreateContextMenu(
    menu: ContextMenu?,
    v: View?,
    menuInfo: ContextMenu.ContextMenuInfo?
  ) {
    menuInflater.inflate(R.menu.main_menu, menu)
    super.onCreateContextMenu(menu, v, menuInfo)
  }

  // TODO: Ham xu ly su kien nhan vao context menu
  override fun onContextItemSelected(item: MenuItem): Boolean {
    val pos = (item.menuInfo as AdapterContextMenuInfo).position
    when (item.itemId) {
      R.id.action_share -> {Toast.makeText(this, "Share $pos", Toast.LENGTH_LONG).show()}
      R.id.action_download -> {Toast.makeText(this, "Download $pos", Toast.LENGTH_LONG).show()}
      R.id.action_settings -> {Toast.makeText(this, "Settings $pos", Toast.LENGTH_LONG).show()}
    }
    return super.onContextItemSelected(item)
  }

  // TODO: Ham khoi tao option menu
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  // TODO: Ham xu ly su kien nhan vao option menu
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_share -> {Toast.makeText(this, "Share", Toast.LENGTH_LONG).show()}
      R.id.action_download -> {Toast.makeText(this, "Download", Toast.LENGTH_LONG).show()}
      R.id.action_settings -> {Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show()}
    }
    return super.onOptionsItemSelected(item)
  }
}