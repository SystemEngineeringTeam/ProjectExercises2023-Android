package net.harutiro.majanhartrate.Repository

import net.harutiro.campingsensingwear.Repository.Sensor.SensorBaseInterface

class SensorRepository {
    val TAG = "SensorRepository"

    fun sensorStart(sensors: MutableList<SensorBaseInterface>) {
        for (sensor in sensors) {
            sensor.start()
        }
    }

    fun sensorStop(sensors: MutableList<SensorBaseInterface>) {
        for (sensor in sensors) {
            sensor.stop()
        }
    }
}