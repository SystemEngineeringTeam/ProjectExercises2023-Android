package net.harutiro.majanhartrate.Repository

import android.os.Handler
import android.os.Looper
import net.harutiro.majanhartrate.Api.HttpApi
import net.harutiro.majanhartrate.Entity.HartRatePostData
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.PrintWriter

class SensorSendRepository(private val queue: ArrayDeque<HartRatePostData>) {


    val domain = "https://heartbeat.sysken.net"
    val path = "/api/v1/set_bpm/"

    val httpApi = HttpApi()

    // Queueのデータをファイルに保存する周期
    val delayMillis: Long = 1000L

    // Handler のオブジェクトを生成
    val handler = Handler(Looper.getMainLooper())
    val runnable = object: Runnable {
        override fun run() {
            // 1:コピー作成
            val pressureCopy = if(queue.isNotEmpty()){
                queue[queue.lastIndex]
            }else{
                handler.postDelayed(this, delayMillis)
                return
            }
            // 2:本体をクリア
            queue.clear()
            //: 3:コピーの一番最新をPOSTする

            val url = domain + path + pressureCopy.direction.value + "?bpm=" + pressureCopy.heart_rate

            httpApi.sendPostRequest(url)
            // 指定時間毎に繰り返す
            handler.postDelayed(this, delayMillis)
        }
    }

    fun start(){
        // 別スレッドを実行
        handler.post(runnable)
    }

    fun stop(){
        // 別スレッドを停止
        handler.removeCallbacks(runnable)
    }
}