package kg.geektech.shopapp.presentation.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kg.geektech.shopapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(View.OnClickListener {
            setResult(RESULT_OK, Intent().putExtra("name",binding.editText.text.toString()))
            setResult(RESULT_OK, Intent().putExtra("count",binding.editTextNum.text.toString()))
            finish()
        })
    }
}