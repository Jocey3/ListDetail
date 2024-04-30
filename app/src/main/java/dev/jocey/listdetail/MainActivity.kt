package dev.jocey.listdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dev.jocey.feature_home.HomeFragment
import dev.jocey.listdetail.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    val languageName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportFragmentManager.commit {
            supportFragmentManager.findFragmentByTag(HomeFragment::class.java.name)?.let {
                replace(R.id.fragment_container, it, HomeFragment::class.java.name)
            } ?: run {
                add(R.id.fragment_container, HomeFragment(), HomeFragment::class.java.name)
            }
        }

    }
}

