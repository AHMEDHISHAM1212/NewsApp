package com.example.newsapp.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.api.sourcesResponse.Source
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.ui.ViewError
import com.example.newsapp.ui.showDialog
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {
    lateinit var viewBinding: FragmentNewsBinding
    lateinit var viewModel: NewsViewModel
    var pageSize = 20
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNewsBinding.inflate(inflater,
            container ,
            false
            )
        return  viewBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.getNewsSources()
        initObservers()
    }

    private fun initObservers() {
        // live data ->>> observers

        viewModel.sourcesLiveData
            .observe(viewLifecycleOwner){sources->
                bindTabs(sources)
            }
        viewModel.newsLiveData
            .observe(viewLifecycleOwner){news ->
                adapter.bindViews(news)

            }

        viewModel.errorLiveData
            .observe(viewLifecycleOwner){
                handelError(it)
            }
    }
    @Inject lateinit var adapter: NewsAdapter
    private fun initViews() {
        viewBinding.vm = viewModel
        viewBinding.lifecycleOwner = this
        viewBinding.rvNews.adapter= adapter
        onRVScrolling()
        adapterClick()

    }


    private var isLoading : Boolean = false
    private fun onRVScrolling(){

        viewBinding.rvNews
            .addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisibleItemCount = layoutManager.findLastVisibleItemPosition()
                    var totalItemCount = layoutManager.itemCount
                    val visibleThreshold = 3

                    if (!isLoading &&totalItemCount-lastVisibleItemCount <=visibleThreshold){
                        isLoading = true
                        page ++
                        getNews()
                    }

                }
            })
    }

    private fun adapterClick() {
        adapter.onRootClickListener =
            NewsAdapter.OnItemClickListener { position, news ->
                val intent = Intent(requireContext(),NewsDetailsActivity::class.java)
                intent.putExtra("news",news)
                startActivity(intent)
            }
    }

    lateinit var source: Source
    private fun getNews() {
        // invoke getNews fun from view model
        viewModel.getNews(source.id,pageSize=pageSize,page=page)
        isLoading = false
    }
    private fun bindTabs(sources: List<Source?>?) {

        if (sources == null) return
        sources.forEach { source ->
            val tab = viewBinding.tabLayout.newTab()
            tab.text = source?.name
            //using tab tag to get the id of the source
            // adding the source of the news into the tab
            tab.tag = source
            viewBinding.tabLayout.addTab(tab)
        }

        viewBinding.tabLayout
            .setOnTabSelectedListener(
                object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                         source = tab?.tag as Source
                        getNews()
                    }
                    override fun onTabReselected(tab: TabLayout.Tab?) {
                         source = tab?.tag as Source
                        getNews()
                    }
                    override fun onTabUnselected(tab: TabLayout.Tab?) {

                    }
                }
            )
        viewBinding.tabLayout.getTabAt(0)?.select()

    }
    private fun handelError(viewError: ViewError){
        showDialog(message = viewError.message?:viewError.throwable?.localizedMessage?:"Something went wrong!",
            posActionName = "Try again!" ,
            posAction = { dialog, i ->
                dialog.dismiss()
             viewError.onTryAgainClickListener?.onTryAgainClick()
            },
            negActionName = "Cancel!" ,
            negAction = {
                    dialog, _ ->
                dialog.dismiss()
            }
        )
    }
}