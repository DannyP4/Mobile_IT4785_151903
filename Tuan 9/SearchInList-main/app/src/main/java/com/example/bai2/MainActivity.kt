package com.example.bai2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var itemList: ArrayList<ItemModel>
    private lateinit var adapter: MyCustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val avatar = arrayOf(R.drawable.avatar, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar4)
        val name = arrayOf("Nguyễn Minh Anh", "Lê Thanh Huy", "Phạm Gia Hân", "Trần Bảo Ngọc", "Đỗ Trung Hiếu",
            "Vũ Thị Mai", "Phan Quang Vinh", "Bùi Ngọc Lan", "Trịnh Minh Khang", "Lý Thùy Trang",
            "Nguyễn Hải Đăng", "Phạm Thị Thanh Hằng", "Dương Quang Minh", "Lê Bảo Châu", "Hồ Ngọc Quỳnh",
            "Tạ Đức Phúc", "Phạm Khánh Linh", "Nguyễn Hồng Sơn", "Lương Phương Anh", "Đặng Xuân Trường",
            "Trần Thị Bích Hồng", "Phan Minh Tuấn", "Lê Vân Anh", "Trần Thanh Tâm", "Phạm Thái Bình",
            "Nguyễn Mỹ Duyên", "Đỗ Hoàng Phúc")

        itemList = arrayListOf()
        for (i in 1..27) {
            if (i < 10) {
                itemList.add(ItemModel(name[i-1], "2022000$i", avatar[Random.nextInt(0, 3)]))
            } else {
                itemList.add(ItemModel(name[i-1], "202200$i", avatar[Random.nextInt(0, 3)]))
            }
        }

        adapter = MyCustomAdapter(itemList)
        val listView = findViewById<ListView>(R.id.listview)
        listView.adapter = adapter

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun filterList(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            itemList
        } else {
            itemList.filter {
                it.username.contains(query, ignoreCase = true) || it.mssv.contains(query, ignoreCase = true)
            } as ArrayList<ItemModel>
        }
        adapter.updateList(filteredList)
    }
}



data class ItemModel(val username: String,val mssv: String, val imageResource: Int)

class MyCustomAdapter(private var items: ArrayList<ItemModel>) : BaseAdapter() {
    override fun getCount(): Int = items.size
    override fun getItem(p0: Int): Any = items[p0]
    override fun getItemId(p0: Int): Long = p0.toLong()

    @SuppressLint("MissingInflatedId")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val row: View = LayoutInflater.from(p2?.context).inflate(R.layout.custom_row_icon_label, p2, false)
        val tvusername = row.findViewById<TextView>(R.id.name)
        val tvmssv = row.findViewById<TextView>(R.id.mssv)
        val imageView = row.findViewById<ImageView>(R.id.icon)

        tvusername.text = items[p0].username
        tvmssv.text = items[p0].mssv
        imageView.setImageResource(items[p0].imageResource)

        return row
    }

    fun updateList(newList: ArrayList<ItemModel>) {
        items = newList
        notifyDataSetChanged()
    }
}