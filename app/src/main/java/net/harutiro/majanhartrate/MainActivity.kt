package net.harutiro.majanhartrate

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import net.harutiro.majanhartrate.Usecase.PermissionUsecase
import net.harutiro.majanhartrate.Usecase.SensorUsecase
import net.harutiro.majanhartrate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val sensorUsecase = SensorUsecase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // アプリバーを非表示にする
        supportActionBar?.hide()

        //スリープにさせないコード
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        //PermissionRequest
        val permissionUsecase = PermissionUsecase()
        permissionUsecase.permissionRequest(
            activity = this,
            permissions = permissionUsecase.permissionsFileWrite
        )

        sensorUsecase.addSensor(this){
            binding.hartRateText.text = it.toString()
        }

        binding.sensingSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                sensorUsecase.start()
            }else{
                sensorUsecase.stop()
            }
        }


        val selectColor = Color.rgb(53,92,79)
        val notSelectColor = Color.rgb(65,65,65)

        val buttons = listOf(binding.buttonA, binding.buttonB, binding.buttonC, binding.buttonD)

        for (button in buttons) {
            button.setOnClickListener {
                SensorUsecase.userId = button.text.toString()
                buttons.forEach { b ->
                    b.backgroundTintList = ColorStateList.valueOf(
                        if (b == button) selectColor else notSelectColor
                    )
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if(sensorUsecase.sensorStartFlag){
            sensorUsecase.stop()
        }
    }
}