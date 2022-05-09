package kg.geektech.shopapp.presentation.first

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kg.geektech.shopapp.databinding.ActivityMainBinding
import kg.geektech.shopapp.domain.ShopItem
import kg.geektech.shopapp.presentation.MainViewModel
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initObserves()
        initListeners()
    }

    private fun initListeners() {

        binding.btnAdd.setOnClickListener {
            viewModel.addShopItem(ShopItem(binding.editText.text.toString(), enabled = true))
            Toast.makeText(applicationContext, binding.editText.text.toString(), Toast.LENGTH_SHORT).show()
            Log.d("huh", "initObserves: ${binding.editText.text}")
        }

        binding.btnDelete.setOnClickListener {
            viewModel.shopListLD.value?.first()?.let { it1 -> viewModel.deleteShopItem(it1) }
            Toast.makeText(applicationContext, viewModel.shopListLD.value?.first().toString(), Toast.LENGTH_SHORT).show()
            Log.d("huh", "initObserves: ${viewModel.shopListLD.value?.first().toString()}")

        }

        binding.btnEdit.setOnClickListener {
            val id = viewModel.getShopItem(binding.editText.text.toString().toInt())
            viewModel.editShopItem(id)
            Toast.makeText(applicationContext, id.toString(), Toast.LENGTH_SHORT).show()
            Log.d("huh", "initObserves: $id")

        }

        binding.btnGetItem.setOnClickListener {
            try {
                val id = viewModel.getShopItem(binding.editText.text.toString().toInt())
                Toast.makeText(applicationContext, id.toString(), Toast.LENGTH_SHORT).show()
                Log.d("geek", "initObserves: $id}")
            }catch (e:RuntimeException){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnGetList.setOnClickListener {
            Log.d("huh", "Shoplist:${viewModel.shopListLD.value.toString()}")
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initObserves() {
            viewModel.shopListLD.observe(this) {

            }
        }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}