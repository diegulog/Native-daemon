package com.diegulog.nativeservice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.File
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rt = Runtime.getRuntime()
        val file = File(filesDir.path + "/watchdog")
        file.setExecutable(true)
        val command = String.format(
            "prog </dev/null &> /data/adb/service.d/watchdog.sh"
        )
        Log.d("MainActivity", command)
        val commads = listOf<String>(command)
        RunAsRoot(commads)

       // Runtime.getRuntime().exec(command)

    }

    fun RunAsRoot(cmds: List<String>) {
        val process = Runtime.getRuntime().exec("su")
        val os = DataOutputStream(process.outputStream)
        for (tmpCmd in cmds) {
            os.writeBytes(tmpCmd+"\n");
        }
        os.writeBytes("exit\n")
        os.flush()
    }
}