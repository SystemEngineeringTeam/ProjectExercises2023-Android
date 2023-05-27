package net.harutiro.majanhartrate.Usecase

import android.Manifest
import android.app.Activity
import net.harutiro.majanhartrate.Repository.PermissionRepository

class PermissionUsecase {

    val permissionRepository = PermissionRepository()

    //パーミッション確認用のコード
    val PERMISSION_REQUEST_CODE = 1

    //どのパーミッションを許可したいかリスト化する
    val permissionsFileWrite = arrayOf(
        Manifest.permission.BODY_SENSORS
    )

    fun permissionRequest(activity: Activity, permissions:Array<String>){
        permissionRepository.requestPermissions(
            activity = activity,
            explanation = "パーミッションを許可する理由を教えてください",
            requestCode = PERMISSION_REQUEST_CODE,
            permissions = permissions
        )
    }
}