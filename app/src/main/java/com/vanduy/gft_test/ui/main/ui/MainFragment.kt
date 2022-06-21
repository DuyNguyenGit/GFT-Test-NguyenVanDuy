package com.vanduy.gft_test.ui.main.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanduy.gft_test.R
import com.vanduy.gft_test.databinding.FragmentMainBinding
import com.vanduy.gft_test.ui.main.domain.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        val TAG = MainFragment::class.java.simpleName
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var itemAdapter: ItemsAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemAdapter = ItemsAdapter(arrayListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentMainBinding.bind(inflater.inflate(R.layout.fragment_main, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.fetchItems()
        viewModel.screenState.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: >>>it: $it")
            binding.swiperefresh.isRefreshing = false
            when (it) {
                is ScreenState.Success -> {
                    with(binding) {
                        listView.visibility = View.VISIBLE
                        error.visibility = View.GONE
                    }
                    itemAdapter.update(it.data)
                }
                is ScreenState.Error -> {
                    with(binding) {
                        error.visibility = View.VISIBLE
                        error.text = resources.getString(R.string.error)
                        listView.visibility = View.GONE
                    }
                }
                is ScreenState.Loading -> {
                    with(binding) {
                        error.visibility = View.GONE
                        loading.visibility = if (it.isLoading) View.VISIBLE else View.GONE
                    }
                }
            }
        }
    }

    private fun initView() {
        with(binding) {
            listView.layoutManager = LinearLayoutManager(requireContext())
            listView.adapter = itemAdapter
            swiperefresh.setOnRefreshListener {
                viewModel.fetchItems()
            }
        }
    }
}