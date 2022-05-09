package kg.geektech.shopapp.presentation.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import kg.geektech.shopapp.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding
    private val adapter = RecyclerViewAdapter()
    private lateinit var  registerForActivityResult: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        initClick()

        registerForActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
            val name = result.data?.getStringExtra("name").toString()
            val count = result.data?.getStringExtra("count").toString().toInt()
            adapter.setList(name,count)
        }
    }



    private fun initClick() {
        binding.floatingActionButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            registerForActivityResult.launch(intent)

        })
    }

    private fun initRecycler() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

}