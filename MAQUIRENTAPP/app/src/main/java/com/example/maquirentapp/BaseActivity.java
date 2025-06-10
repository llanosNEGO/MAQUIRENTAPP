package com.example.maquirentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;

public abstract class BaseActivity extends AppCompatActivity {
    protected ImageView headerIcon;
    protected TextView headerTitle;
    protected FrameLayout contentContainer;
    protected NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ly_base_activity);

        initViews();
        setupHeader();
        inflateContent();
    }
    private void initViews() {
        headerIcon = findViewById(R.id.header_icon);
        headerTitle = findViewById(R.id.header_title);
        contentContainer = findViewById(R.id.fragment_container);
        scrollView = findViewById(R.id.content_scroll_view);
    }

    private void setupHeader() {
        headerIcon.setImageResource(getHeaderIcon());
        headerTitle.setText(getHeaderTitle());
    }

    private void inflateContent() {
        int contentLayoutId = getContentLayoutId();
        if (contentLayoutId != 0) {
            LayoutInflater inflater = LayoutInflater.from(this);
            inflater.inflate(contentLayoutId, contentContainer, true);
        }
    }

    protected int getHeaderIcon() {
        return 0;
    }

    protected String getHeaderTitle() {
        return "";
    }

    protected abstract int getContentLayoutId();

    protected void setHeaderIcon(int iconResId) {
        if (headerIcon != null) {
            headerIcon.setImageResource(iconResId);
        }
    }

    protected void setHeaderTitle(String title) {
        if (headerTitle != null) {
            headerTitle.setText(title);
        }
    }
    protected FrameLayout getContentContainer() {
        return contentContainer;
    }

    protected NestedScrollView getScrollView() {
        return scrollView;
    }
}