package com.ayat.candyapp.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.ayat.candyapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/*
 * Created by Ayat khraisat  on 4/17/2019.
 */
public class CustomRecyclerView extends LinearLayout {

    private RecyclerViewEmptySupport statusRecyclerView;
    private TextView textView;
    private LinearLayout emptyView;
    private ImageView imageView;
    private FloatingActionButton fabReturnToTop;
    private SwipeRefreshLayout swipeRefreshLayout;

    public CustomRecyclerView(Context context) {
        this(context, null);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View root = LayoutInflater.from(context).inflate(R.layout.layout_custom_recycler, null);
        final LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        root.setLayoutParams(lp);

        textView = root.findViewById(R.id.tv_no_data);
        imageView = root.findViewById(R.id.iv_not_data);
        emptyView = root.findViewById(R.id.ll_empty_view_container);
        statusRecyclerView = root.findViewById(R.id.rv);
        statusRecyclerView.setHasFixedSize(true);
        statusRecyclerView.setEmptyView(emptyView);
         LinearLayoutManager layoutManager =new LinearLayoutManager(getContext());
        statusRecyclerView.setLayoutManager(layoutManager);
        statusRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findFirstCompletelyVisibleItemPosition() != -1)
                    if (layoutManager.findFirstCompletelyVisibleItemPosition() > 3)
                        fabReturnToTop.show();
                    else
                        fabReturnToTop.hide();
            }
        });

        fabReturnToTop = root.findViewById(R.id.fab_return_to_top);
        fabReturnToTop.setOnClickListener(v -> {
            statusRecyclerView.smoothScrollToPosition(0);
            fabReturnToTop.hide();
        });

        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(
                getContext().getResources().getColor(R.color.colorPrimaryDark),
                getContext().getResources().getColor(R.color.colorAccent));

        this.addView(root);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public <E extends RecyclerView.Adapter> void setAdapter(E e) {
        statusRecyclerView.setAdapter(e);

    }

    public void showItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        statusRecyclerView.addItemDecoration(itemDecoration);
    }
    public void showItemDecoration() {
        DividerItemDecoration divider = new
                DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider));

        statusRecyclerView.addItemDecoration(divider);
    }

    public void setEmptyViewText(String text) {
        textView.setText(text);
    }

    public void setEmptyViewIcon(@DrawableRes int resource) {

        imageView.setImageDrawable(getResources().getDrawable(resource));
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        swipeRefreshLayout.setOnRefreshListener(listener);
    }

    public void startRefreshing() {
        swipeRefreshLayout.setRefreshing(true);
    }

    public void stopRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void setRefreshEnabled(boolean isEnabled) {

        swipeRefreshLayout.setEnabled(isEnabled);

    }
}