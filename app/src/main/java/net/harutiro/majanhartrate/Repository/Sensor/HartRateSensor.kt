package net.harutiro.majanhartrate.Repository.Sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import net.harutiro.campingsensingwear.Repository.Sensor.SensorBaseInterface
import net.harutiro.majanhartrate.Entity.HartRatePostData
import net.harutiro.majanhartrate.Repository.SensorSendRepository
import net.harutiro.majanhartrate.Usecase.SensorUsecase
import net.harutiro.majanhartrate.Utils.DateUtils

class HartRateSensor(private val context: Context , val listener:HartRateSensorListener): SensorEventListener, SensorBaseInterface {

    private lateinit var sensorManager: SensorManager
    private var hartRateSensor: Sensor? = null
    override val sensorName: String = "PressureSensor"
    override val queue: ArrayDeque<HartRatePostData> = ArrayDeque()

    private var sensorSendRepository: SensorSendRepository? = null

    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        hartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)
    }

    override fun start() {
        queue.clear()
        sensorManager.registerListener(this, hartRateSensor, SensorManager.SENSOR_DELAY_UI)
        sensorSendRepository = SensorSendRepository(queue)
        sensorSendRepository?.start()
    }

    override fun stop() {
        sensorManager.unregisterListener(this)
        sensorSendRepository?.stop()
        sensorSendRepository = null
    }

    override fun onSensorChanged(event: SensorEvent) {
        val hartRate = event.values[0]
        queue.add(
            HartRatePostData(
                time = DateUtils.getTimeStamp(),
                heart_rate = hartRate.toInt(),
                direction = SensorUsecase.userId!!
            )
        )
        listener.setOnSensorChangedDisplay(hartRate.toInt())
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    // RecyclerViewの要素をタップするためのもの
    interface HartRateSensorListener{
        fun setOnSensorChangedDisplay(item:Int)
    }

}