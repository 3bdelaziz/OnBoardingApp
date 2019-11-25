package com.example.onboardingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;
    private SliderAdapter mSliderAdapter;
    private TextView[] mDots;
    private Button mBackBtn;
    private Button mNextBtn;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotsLayout = findViewById(R.id.dotsLayout);
        mBackBtn = findViewById(R.id.prevBtn);
        mNextBtn = findViewById(R.id.nextBtn);

        mSliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(mSliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(changeListener);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });
    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
        mDotsLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotsLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            mCurrentPage = position;
            if (position == 0) {
                mBackBtn.setEnabled(false);
                mNextBtn.setEnabled(true);
                mBackBtn.setVisibility(View.INVISIBLE);

                mBackBtn.setText("");
                mNextBtn.setText("Next");
            } else if (mDots.length - 1 == position) {
                mBackBtn.setEnabled(true);
                mNextBtn.setEnabled(true);

                mBackBtn.setVisibility(View.VISIBLE);

                mBackBtn.setText("Back");
                mNextBtn.setText("Finish");
            } else {
                mBackBtn.setEnabled(true);
                mNextBtn.setEnabled(true);

                mBackBtn.setVisibility(View.VISIBLE);

                mBackBtn.setText("Back");
                mNextBtn.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
