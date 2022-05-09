package kg.geektech.shopapp.presentation.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kg.geektech.shopapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.putExtra("name",binding.editText.text.toString())
            intent.putExtra("count",binding.editTextNum.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        })
    }
}