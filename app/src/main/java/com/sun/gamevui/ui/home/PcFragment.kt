package com.sun.gamevui.ui.home

import android.view.View
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import com.sun.gamevui.R
import com.sun.gamevui.base.BaseFragment
import com.sun.gamevui.data.model.Game
import com.sun.gamevui.data.model.Genre
import com.sun.gamevui.data.remote.ApiConfig
import com.sun.gamevui.databinding.FragmentPlatformBinding
import com.sun.gamevui.ui.adapter.GamesAdapter
import com.sun.gamevui.ui.adapter.PopularAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PcFragment : BaseFragment<FragmentPlatformBinding>() {

    private val popularAdapter = PopularAdapter(this::clickItemPopular)
    private val gamesAdapter = GamesAdapter(this::clickItemGame)
    override val layoutId = R.layout.fragment_platform
    override val viewModel by sharedViewModel<HomeViewModel>()

    override fun initViews() {
        binding?.apply {
            recyclerPopular.apply {
                setHasFixedSize(true)
                adapter = popularAdapter
            }
        }
    }

    override fun initData() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            homeVM = viewModel
            recyclerGames.adapter = gamesAdapter
        }
    }

    override fun initActions() {
        binding?.spinnerGenre?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    (parent?.getItemAtPosition(position) as Genre).let {
                        viewModel.getGamesByGenre(it.id.toString(), ApiConfig.BASE_PC)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun clickItemPopular(game: Game) {
        val action = HomeFragmentDirections.actionHomeFragmentToGameDetailFragment(game)
        findNavController().navigate(action)
    }

    private fun clickItemGame(game: Game) {
        val action = HomeFragmentDirections.actionHomeFragmentToGameDetailFragment(game)
        findNavController().navigate(action)
    }
}
