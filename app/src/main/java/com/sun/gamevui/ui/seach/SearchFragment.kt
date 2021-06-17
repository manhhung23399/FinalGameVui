package com.sun.gamevui.ui.seach

import android.content.Context
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.sun.gamevui.R
import com.sun.gamevui.base.BaseFragment
import com.sun.gamevui.data.model.Game
import com.sun.gamevui.databinding.FragmentSearchBinding
import com.sun.gamevui.ui.adapter.SearchGamesAdapter
import com.sun.gamevui.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val searchGamesAdapter = SearchGamesAdapter(this::clickItemGame)
    override val layoutId get() = R.layout.fragment_search
    override val viewModel by viewModel<SearchViewModel>()

    override fun initViews() {
    }

    override fun initData() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            searchVM = viewModel
            recyclerSearch.adapter = searchGamesAdapter
        }
    }

    override fun initActions() {
        binding?.apply {
            editTextSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE||actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (editTextSearch.text.isEmpty()){
                        hide()
                        view?.context?.showToast("Type something!!!")
                    }else{
                    viewModel.getGameByName(editTextSearch.text.toString())
                        hide()
                    }
                }
                false
            }
            imageClear.setOnClickListener {
                editTextSearch.setText("")
            }
        }
    }

    private fun clickItemGame(game: Game) {
        val action = SearchFragmentDirections.actionSearchFragmentToGameDetailFragment(game)
        findNavController().navigate(action)
    }
    private fun hide(){
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
