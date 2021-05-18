package co.id.kadaluarsa.search.ui

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.id.kadaluarsa.core.AppExecutors
import co.id.kadaluarsa.navigation.DeepLinkDestination
import co.id.kadaluarsa.navigation.deepLinkNavigateTo
import co.id.kadaluarsa.search.R
import co.id.kadaluarsa.search.databinding.FragmentSearchBinding
import co.id.kadaluarsa.search.ui.adapter.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    lateinit var adapter: UserListAdapter
    private val searchViewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        queryListener()
        initRecyclerView()
        val rvAdapter = UserListAdapter(
            appExecutors = appExecutors
        ) { user ->
            findNavController().deepLinkNavigateTo(DeepLinkDestination.UserDetail(username = user.login))
        }
        binding.rvSearchResult.adapter = rvAdapter
        adapter = rvAdapter
    }

    private fun initRecyclerView() {
        binding.rvSearchResult.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapter.itemCount - 1) {
                    //searchViewModel.loadNextPage()
                }
            }
        })
        searchViewModel.listuser.observe(viewLifecycleOwner, { result ->
            adapter.submitList(result?.data)
        })
    }

    private fun queryListener() {
        binding.searchBox.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchViewModel.setUsername(binding.searchBox.text.toString())
                true
            } else {
                false
            }
        }
    }
}