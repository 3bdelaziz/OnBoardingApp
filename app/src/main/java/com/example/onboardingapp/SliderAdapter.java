package com.example.onboardingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    private Context mContext;

    SliderAdapter(Context context) {
        this.mContext = context;
    }

    private int[] slideImage = {
            R.drawable.eat_icon,
            R.drawable.sleep_icon,
            R.drawable.code_icon
    };

    private String[] slideHeadings = {
            "EAT",
            "SLEEP",
            "CODE"
    };

    private String[] slideDescs = {
            "Lorem ipsum dolor sit amet, consectetur asipiscing elit, sed do elusmod tempor incididunt ut labore et dolore magna aliqua",
            "Lorem ipsum dolor sit amet, consectetur asipiscing elit, sed do elusmod tempor incididunt ut labore et dolore magna aliqua",
            "Lorem ipsum dolor sit amet, consectetur asipiscing elit, sed do elusmod tempor incididunt ut labore et dolore magna aliqua"
    };

    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert mLayoutInflater != null;
        View view = mLayoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView slideHeadingTextView = view.findViewById(R.id.slide_heading);
        TextView slideDescTextView = view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slideImage[position]);
        slideHeadingTextView.setText(slideHeadings[position]);
        slideDescTextView.setText(slideDescs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
