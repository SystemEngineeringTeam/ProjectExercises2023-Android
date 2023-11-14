package net.harutiro.majanhartrate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import net.harutiro.majanhartrate.Usecase.SensorUsecase
import net.harutiro.majanhartrate.Utils.DirectionUtils
import net.harutiro.majanhartrate.databinding.ActivityMainBinding
import net.harutiro.majanhartrate.databinding.ActivitySensingBinding

class SensingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySensingBinding
    val sensorUsecase = SensorUsecase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySensingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // アプリバーを非表示にする
        supportActionBar?.hide()

        //スリープにさせないコード
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        binding.textViewDirection.text = DirectionUtils.direction2String(SensorUsecase.userId!!)

        //センサーの値を表示する
        sensorUsecase.addSensor(this,
            function = { item ->
                runOnUiThread {
                    binding.textViewHeartRate.text = item.toString()
                    binding.textViewHeartRate.invalidate()
                }
            },
            toastFunction = {
                runOnUiThread{
                    Toast.makeText(this, "送信に失敗しました", Toast.LENGTH_SHORT).show()
                }
            },
        )

        //戻るボタンを押したらセンサーを止める
        binding.backButton.setOnClickListener {
            sensorUsecase.stop()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        if(!sensorUsecase.sensorStartFlag){
            sensorUsecase.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if(sensorUsecase.sensorStartFlag){
            sensorUsecase.stop()
        }
    }
}