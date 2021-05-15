package co.id.kadaluarsa.search.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.id.kadaluarsa.core.AppExecutors
import co.id.kadaluarsa.core.di.Injectable
import co.id.kadaluarsa.navigation.DeepLinkDestination
import co.id.kadaluarsa.navigation.deepLinkNavigateTo
import co.id.kadaluarsa.search.R
import co.id.kadaluarsa.search.ui.base.BaseFragment
import co.id.kadaluarsa.search.databinding.FragmentSearchBinding
import co.id.kadaluarsa.search.ui.adapter.UserListAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchBinding>(), Injectable {

    lateinit var adapter: UserListAdapter
    private val searchViewModel: SearchViewModel by viewModels()
    @Inject
    lateinit var appExecutors: AppExecutors

    override fun layoutRes(): Int = R.layout.fragment_search

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
        val rvAdapter = UserListAdapter(
            appExecutors = appExecutors
        ){ user ->
            findNavController().deepLinkNavigateTo(DeepLinkDestination.Dashboard("From next fragment deeplink"))

        }
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
//        binding.searchResult = searchViewModel.results
        searchViewModel.listuser.observe(viewLifecycleOwner, Observer { result ->
            adapter.submitList(result?.data)
        })

//        searchViewModel.loadMoreStatus.observe(viewLifecycleOwner, Observer { loadingMore ->
//            if (loadingMore == null) {
//                binding.loadingMore = false
//            } else {
//                binding.loadingMore = loadingMore.isRunning
//                val error = loadingMore.errorMessageIfNotHandled
//                if (error != null) {
//                    Snackbar.make(binding.loadMoreBar, error, Snackbar.LENGTH_LONG).show()
//                }
//            }
//        })
    }
}