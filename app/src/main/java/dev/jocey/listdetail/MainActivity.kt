package dev.jocey.listdetail

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dev.jocey.listdetail.di.App
import dev.jocey.listdetail.di.ViewModelFactory
import dev.jocey.listdetail.temp.HomeViewModel
import java.util.Random
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val random = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vm   = viewModelFactory.create(HomeViewModel::class.java)
        val tv: TextView = findViewById(R.id.tv_next)
        tv.setOnClickListener {
            vm.getNumber(random.nextInt(100))
        }
        vm.numberLiveData.observe(this) {
            Log.d("myLog", it.toString())
        }

    }
}