package com.spdb.gateinfo.network

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


class MyWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    override fun doWork(): Result {
        Log.d("myTag", "doWork: ")
        return Result.success()
    }
}