package net.harutiro.campingsensingwear.Repository.Sensor

import net.harutiro.majanhartrate.Entity.HartRatePostData

interface SensorBaseInterface {
    val sensorName: String
    val queue: ArrayDeque<HartRatePostData>
    fun start() {}
    fun stop() {}
}