package com.donkingliang.consecutivescrollerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout;
import com.donkingliang.consecutivescrollerdemo.adapter.RecyclerViewAdapter;

public class StickyActivity extends AppCompatActivity {

    private ConsecutiveScrollerLayout scrollerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);

        scrollerLayout = findViewById(R.id.scrollerLayout);

        WebView webView = findViewById(R.id.webView);
        webView.loadUrl("https://github.com/donkingliang");

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                // 在webView加载的过程中，用户滚动了webView内容，可能会使webView的显示与scrollerLayout断层，
                // 需要让scrollerLayout重新检查一下所有View的显示位置
                scrollerLayout.checkLayoutChange();
            }
        });


        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter1 = new RecyclerViewAdapter(this,"RecyclerView1-");
        recyclerView1.setAdapter(adapter1);

        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter2 = new RecyclerViewAdapter(this,"RecyclerView2-");
        recyclerView2.setAdapter(adapter2);

        // 监听吸顶view
        scrollerLayout.setOnStickyChangeListener(new ConsecutiveScrollerLayout.OnStickyChangeListener() {
            @Override
            public void OnStickyChange(@Nullable View oldStickyView, @Nullable View newStickyView) {
                Log.e("eee",oldStickyView + " " + newStickyView);
            }
        });


        // 设置吸顶到顶部的距离
//        scrollerLayout.setStickyOffset(50);
    }
}
