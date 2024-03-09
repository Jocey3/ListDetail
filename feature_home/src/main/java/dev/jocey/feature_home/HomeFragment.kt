package dev.jocey.feature_home

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import dev.jocey.feature_home.databinding.FragmentHomeBinding
import dev.jocey.feature_home.model.NumberView
import javax.inject.Inject


class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var adapter: NumbersAdapter

    private var binding: FragmentHomeBinding? = null

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
        adapter.onNumberClickCallback = { openDetailFragment(it) }
        adapter.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                binding?.recycler?.scrollToPosition(adapter.itemCount - 1)
            }
        })
        binding?.recycler?.layoutManager =
            LinearLayoutManager(this@HomeFragment.context, RecyclerView.VERTICAL, true)
        binding?.recycler?.adapter = adapter
    }

    private fun openDetailFragment(numberView: NumberView) {
        Log.d("myLog", "Opened number ${numberView.number}")
    }

    private fun observeLiveData() {
        viewModel.numberLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UiHomeState.Success<*> -> {
                    Log.d("myLog", it.toString())
                }

                is UiHomeState.Error -> {
                    Toast.makeText(
                        context,
                        "Something get wrong. Please try again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        viewModel.allNumbers.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }
    }


    private fun validateInputtedNumber() {
        hideKeyboard()
        val number = binding?.searchInputField?.text.toString().trim()
        if (number.isEmpty()) {
            binding?.searchInputField?.error = "Please input a number."
        } else if (number.length in 1..5) {
            binding?.searchInputField?.text = null
            viewModel.getNumber(number)
        } else {
            binding?.searchInputField?.error = "Please input a smaller number."
        }
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity?.currentFocus
        if (view == null) {
            view = View(activity)
        }
        view.clearFocus()
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}