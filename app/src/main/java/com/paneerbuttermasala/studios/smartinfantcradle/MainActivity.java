package com.paneerbuttermasala.studios.smartinfantcradle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public ViewPager mSlideViewPager;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private LinearLayout mDotlayout;
    private Button mNext, mBack;
    private int mCurrentpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideViewPager = (ViewPager) findViewById(R.id.SlideViewPager);
        mDotlayout = (LinearLayout) findViewById(R.id.dotsLayout);
        mNext = findViewById(R.id.btnNext);
        mBack = findViewById(R.id.btnBack);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);
        addDotIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListner);
    }

    public void addDotIndicator(int position ) {
        mDots = new TextView[2];
        mDotlayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.template_white));
            mDotlayout.addView(mDots[i]);
        }

        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.black));
        }

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentpage==mDots.length-1){
                    Intent i = new Intent(MainActivity.this,Login.class);
                    startActivity(i);
                }
                else {
                    mSlideViewPager.setCurrentItem(mCurrentpage + 1);
                }
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentpage-1);
            }
        });
    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotIndicator(position);
            mCurrentpage = position;

            if(position == 0){
                mNext.setEnabled(true);
                mBack.setEnabled(false);
                mBack.setVisibility(View.INVISIBLE);
                mNext.setVisibility(View.VISIBLE);

                mNext.setText("Next");
                mBack.setText("");

            }
            else if(position == mDots.length - 1){
                mNext.setEnabled(true);
                mBack.setEnabled(true);
                mBack.setVisibility(View.VISIBLE);
                mNext.setVisibility(View.VISIBLE);

                mNext.setText("Finish");
                mBack.setText("Back");
            }

            else {
                mNext.setEnabled(true);
                mBack.setEnabled(true);
                mBack.setVisibility(View.VISIBLE);
                mNext.setVisibility(View.VISIBLE);

                mNext.setText("Next");
                mBack.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}