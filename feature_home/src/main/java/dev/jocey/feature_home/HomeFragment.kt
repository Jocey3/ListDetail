package dev.jocey.feature_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import dev.jocey.feature_home.databinding.FragmentHomeBinding
import java.util.Random
import javax.inject.Inject


class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    private var binding: FragmentHomeBinding? = null
    private val random = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        observeLiveData()

    }

    private fun bindViews() {
        binding?.tvNumb?.setOnClickListener {
            viewModel.getNumber(random.nextInt(999))
        }
    }


    private fun observeLiveData() {
        viewModel.numberLiveData.observe(viewLifecycleOwner) {
            binding?.tvShowRandom?.text = it.toString()
        }
    }
}