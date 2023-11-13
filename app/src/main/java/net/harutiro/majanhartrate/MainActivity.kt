package net.harutiro.majanhartrate

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import net.harutiro.majanhartrate.Entity.Direction
import net.harutiro.majanhartrate.Usecase.PermissionUsecase
import net.harutiro.majanhartrate.Usecase.SensorUsecase
import net.harutiro.majanhartrate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        val buttons = listOf(binding.buttonEast, binding.buttonNorth, binding.buttonSouth, binding.buttonWest)
        for (button in buttons) {
            button.setOnClickListener {
                if(button.text.toString() == "北"){
                    SensorUsecase.userId = Direction.NORTH
                }else if(button.text.toString() == "東"){
                    SensorUsecase.userId = Direction.EAST
                }else if(button.text.toString() == "南"){
                    SensorUsecase.userId = Direction.SOUTH
                }else if(button.text.toString() == "西"){
                    SensorUsecase.userId = Direction.WEST
                }

                val intent = Intent(this, SensingActivity::class.java)
                startActivity(intent)
            }
        }
    }


}