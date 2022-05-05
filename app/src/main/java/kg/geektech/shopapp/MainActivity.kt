package kg.geektech.shopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kg.geektech.shopapp.databinding.ActivityMainBinding
import kg.geektech.shopapp.domain.ShopItem
import kg.geektech.shopapp.presentation.MainViewModel

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
            viewModel.getShopList()
            viewModel.addShopItem(ShopItem(binding.editText.text.toString(), enabled = true))
            Toast.makeText(applicationContext, binding.editText.text.toString(), Toast.LENGTH_SHORT)
                .show()
            Log.d("huh", "initObserves: ${binding.editText.text}")
        }

        binding.btnDelete.setOnClickListener {
            viewModel.getShopList()
            viewModel.deleteShopItem()
            Toast.makeText(
                applicationContext,
                viewModel.shopListLD.value?.first().toString(),
                Toast.LENGTH_SHORT
            ).show()
            Log.d("huh", "initObserves: ${viewModel.shopListLD.value?.first().toString()}")

        }

        binding.btnEdit.setOnClickListener {
            viewModel.getShopList()
            viewModel.editShopItem(ShopItem(binding.editText.text.toString(), enabled = true))
            Toast.makeText(
                applicationContext,
                viewModel.shopListLD.value?.first().toString(),
                Toast.LENGTH_SHORT
            ).show()
            Log.d("huh", "initObserves: ${viewModel.shopListLD.value?.first().toString()}")

        }

        binding.btnGetItem.setOnClickListener {
            viewModel.getShopList()
            viewModel.getShopItem(binding.editText.text.toString().toInt())
            Toast.makeText(
                applicationContext,
                viewModel.shopListLD.value?.get(binding.editText.text.toString().toInt())
                    .toString(),
                Toast.LENGTH_SHORT
            ).show()
            Log.d(
                "geek",
                "initObserves: ${
                    viewModel.shopListLD.value?.get(
                        binding.editText.text.toString().toInt()
                    )?.name.toString()
                }"
            )
        }
        binding.btnGetList.setOnClickListener {
            viewModel.getShopList()
            Log.d("huh", "Shoplist:${viewModel.shopListLD.value?.size}")
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