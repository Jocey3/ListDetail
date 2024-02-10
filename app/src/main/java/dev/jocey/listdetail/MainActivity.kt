package dev.jocey.listdetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dev.jocey.data.data_source.network.NumberApi
import dev.jocey.listdetail.di.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var api: NumberApi
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            val call = async { api.getNumber("3") }
            Log.d("myLog", call.await().toString())
        }

    }
}