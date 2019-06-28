package com.ayat.candyapp.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ayat.candyapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/*
 * Created by Ayat khraisat  on 4/17/2019.
 */
class CustomRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    private val statusRecyclerView: RecyclerViewEmptySupport
    private val textView: TextView
    private val emptyView: LinearLayout
    private val imageView: ImageView
    private val fabReturnToTop: FloatingActionButton
    private val swipeRefreshLayout: SwipeRefreshLayout

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.layout_custom_recycler, null)
        val lp = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        root.layoutParams = lp

        textView = root.findViewById(R.id.tv_no_data)
        imageView = root.findViewById(R.id.iv_not_data)
        emptyView = root.findViewById(R.id.ll_empty_view_container)
        statusRecyclerView = root.findViewById(R.id.rv)
        statusRecyclerView.setHasFixedSize(true)
        statusRecyclerView.setEmptyView(emptyView)
        val layoutManager = LinearLayoutManager(getContext())
        statusRecyclerView.layoutManager = layoutManager
        fabReturnToTop = root.findViewById(R.id.fab_return_to_top)
        fabReturnToTop.setOnClickListener { v ->
            statusRecyclerView.smoothScrollToPosition(0)
            fabReturnToTop.hide()
        }
        statusRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (layoutManager.findFirstCompletelyVisibleItemPosition() != -1)
                    if (layoutManager.findFirstCompletelyVisibleItemPosition() > 3)
                        fabReturnToTop.show()
                    else
                        fabReturnToTop.hide()
            }
        })



        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.isEnabled=false
        swipeRefreshLayout.setColorSchemeColors(
            getContext().resources.getColor(R.color.colorPrimaryDark),
            getContext().resources.getColor(R.color.colorAccent)
        )

        this.addView(root)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    fun <E : RecyclerView.Adapter<*>> setAdapter(e: E) {
        statusRecyclerView.adapter = e

    }

    fun showItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
        statusRecyclerView.addItemDecoration(itemDecoration)
    }

    fun showItemDecoration() {
        val divider = DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
        divider.setDrawable(resources.getDrawable(R.drawable.divider))

        statusRecyclerView.addItemDecoration(divider)
    }

    fun setEmptyViewText(text: String) {
        textView.text = text
    }

    fun setEmptyViewIcon(@DrawableRes resource: Int) {

        imageView.setImageDrawable(resources.getDrawable(resource))
    }

    fun setOnRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener) {
        swipeRefreshLayout.isEnabled=true
        swipeRefreshLayout.setOnRefreshListener(listener)
    }

    fun startRefreshing() {
        swipeRefreshLayout.isRefreshing = true
    }

    fun stopRefreshing() {
        swipeRefreshLayout.isRefreshing = false
    }

    fun setRefreshEnabled(isEnabled: Boolean) {

        swipeRefreshLayout.isEnabled = isEnabled

    }
}