package dev.jocey.feature_home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
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
        binding?.searchInputField?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                validateInputtedNumber()
            }
            false
        }
        binding?.btnGetNumberFact?.setOnClickListener { validateInputtedNumber() }
        binding?.btnGetRandomFact?.setOnClickListener { viewModel.getRandomFact() }
    }

    private fun observeLiveData() {
        viewModel.numberLiveData.observe(viewLifecycleOwner) {
            Log.d("myLog", it.toString())
        }
    }


    private fun validateInputtedNumber() {
        val number = binding?.searchInputField?.text.toString().trim()
        if (number.isNullOrEmpty()) {
            binding?.searchInputField?.error = "Please input a number."
        } else if (number.length in 1..5) {
            viewModel.getNumber(number)
        } else {
            binding?.searchInputField?.error = "Please input a smaller number."
        }
    }
}