package com.demo.neoveticare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnskip,btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewPager=(ViewPager) findViewById(R.id.view_pager);
        dotsLayout=(LinearLayout) findViewById(R.id.layout_dots);
        btnnext=(Button) findViewById(R.id.btn_next);
        btnskip=(Button) findViewById(R.id.btn_skip);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        layouts=new int[]{
                R.layout.activity_layout1,
                R.layout.activity_layout2,
                R.layout.activity_layout3,
                R.layout.activity_layout4,
        };

        // adding bottom dots
        addBottomDots(0);
        // making notification bar transparent
        changeStatusBarColor();


        myViewPagerAdapter=new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        //   viewPager.addOnAdapterChangeListener((ViewPager.OnAdapterChangeListener) viewPagerPageChangeListener);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current=getItem(+1);
                if (current<layouts.length){
                    // move to next screen
                    Intent intent= new Intent(Welcome.this,MainActivity.class);
                    startActivity(intent);
                    // viewPager.setCurrentItem(current);
                }
                else {
                    Intent intent= new Intent(Welcome.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem()+i;
    }
    private void addBottomDots(int currentPage){
        dots=new TextView[layouts.length];

        int[] colorsActive=getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive=getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length>0){
            dots[currentPage].setTextColor(colorsActive[currentPage]);
        }
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnnext.setText("Start");
                btnskip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnnext.setText("Skip");
                btnskip.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter(){

        }
        @Override
        public Object instantiateItem(ViewGroup container, int position){
            layoutInflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            if(position==2)
            {

            }
            else if(position==3)
            {

            }
            else if(position==4)
            {

            }
            else if(position==5)
            {

            }
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject( View view,  Object object) {
            return view == object;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}
