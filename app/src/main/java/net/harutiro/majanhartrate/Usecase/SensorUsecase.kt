package net.harutiro.majanhartrate.Usecase

import android.content.Context
import net.harutiro.campingsensingwear.Repository.Sensor.SensorBaseInterface
import net.harutiro.majanhartrate.Entity.Direction
import net.harutiro.majanhartrate.Repository.Sensor.HartRateSensor
import net.harutiro.majanhartrate.Repository.SensorRepository

class SensorUsecase {
    val TAG = "SensorUsecase"

    var targetSensors: MutableList<SensorBaseInterface> = mutableListOf()
    var sensorRepository = SensorRepository()

    companion object{
        var userId: Direction? = null
    }


    var sensorStartFlag = false

    fun addSensor(context: Context, function: (item:Int) -> Unit, toastFunction: () -> Unit){
        val hartRateSensor = HartRateSensor(
            context ,
            object: HartRateSensor.HartRateSensorListener{
                override fun setOnSensorChangedDisplay(item: Int) {
                    function(item)
                }
            },
            toastFunction
        )

        targetSensors.add(hartRateSensor)
    }

    fun start(){
        sensorRepository.sensorStart(targetSensors)
        sensorStartFlag = true
    }

    fun stop(){
        sensorRepository.sensorStop(targetSensors)
        sensorStartFlag = false
    }

    fun displayData(){

    }
}