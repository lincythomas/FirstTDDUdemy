package com.example.firsttdd.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.firsttdd.R
import com.example.firsttdd.databinding.FragmentPlayListBinding
import com.example.firsttdd.recylerviewdata.PlayListItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */

@AndroidEntryPoint
class PlayListFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel
    lateinit var binding: FragmentPlayListBinding

    @Inject
    lateinit var viewModelFactory: PlayListViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play_list, container, false)


        setUpViewModel()

        viewModel.loader.observe(viewLifecycleOwner) { isLoading ->
            // Set the adapter
            if (isLoading) {
                binding.loader.visibility = View.VISIBLE
            } else {
                binding.loader.visibility = View.GONE

            }

        }

        viewModel.playLists.observe(viewLifecycleOwner) { item ->
            // Set the adapter
            if (item.getOrNull() != null) {
                setUpRecyclerView(item.getOrNull()!!)
            } else {
                // TODO handle empty list here
            }

        }
        return binding.root
    }

    private fun setUpRecyclerView(
        it: List<PlayListItem>
    ) {

        with(binding.playlist) {
            adapter = PlayListRecyclerViewAdapter(it)
        }

    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayListViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PlayListFragment().apply {
            }
    }
}