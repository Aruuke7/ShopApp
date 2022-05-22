package kg.geektech.shopapp.presentation.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kg.geektech.shopapp.databinding.ActivityRecyclerBinding
import kg.geektech.shopapp.domain.ShopItem
import kg.geektech.shopapp.presentation.MainViewModel

class RecyclerActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRecyclerBinding
    private val adapter = RecyclerViewAdapter()
    private lateinit var registerForActivityResult: ActivityResultLauncher<Intent>
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initContract()
        initViewModel()
        initRecycler()
        initObservers()
        initClick()
    }

    private fun initContract() {
        registerForActivityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val name = result.data?.getStringExtra("name").toString()
                    val count = result.data?.getStringExtra("count").toString().toInt()
                    viewModel.addShopItem(ShopItem(name, count, enabled = true))
                }
            }
    }

    private fun initObservers() {
        viewModel.shopListLD.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }


    private fun initClick() {
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            registerForActivityResult.launch(intent)

        }

        adapter.shopItemClickListener = { item ->
            viewModel.deleteShopItem(item)
        }
        adapter.shopItemOnLongClickListener = {
            viewModel.editShopItem(it)
            Toast.makeText(this, "context $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecycler() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}