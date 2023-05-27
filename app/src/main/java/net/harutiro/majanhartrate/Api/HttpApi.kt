package net.harutiro.majanhartrate.Api

import android.util.Log
import net.harutiro.majanhartrate.Entity.HartRatePostData
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class HttpApi {

    val TAG = "HttpApi"

    fun sendPostRequest(url: String, jsonData: String) {
        val client = OkHttpClient()

        val mediaType = "application/json".toMediaType()
        val requestBody = RequestBody.create(mediaType, jsonData)
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // リクエスト失敗時の処理
                // エラーハンドリングを行ってください
                Log.d(TAG,e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    // リクエスト成功
                    val responseBody = response.body?.string()
                    Log.d(TAG,responseBody.toString())

                    // レスポンスの処理
                    // ここで必要なデータを取得するなどの処理を追加してください
                } else {
                    Log.d(TAG,response.toString())
                    // リクエスト失敗
                    // エラーハンドリングを行ってください
                }
            }
        })
    }
}