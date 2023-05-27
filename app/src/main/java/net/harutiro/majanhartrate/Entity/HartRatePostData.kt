package net.harutiro.majanhartrate.Entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

@Serializable
data class HartRatePostData (
    val time:Long = 0,
    val heart_rate:Int = 0,
    val user:String = ""
){
    fun toJsonString(): String {
        return Json.encodeToString(serializer(), this)
    }
}