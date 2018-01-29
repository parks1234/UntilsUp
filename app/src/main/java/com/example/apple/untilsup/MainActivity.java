package com.example.apple.untilsup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.example.apple.untilsup.base.activity.BaseCompatActivity;

import java.lang.reflect.Field;

public class MainActivity extends BaseCompatActivity {


    @Override
    protected void initView(Bundle savedInstanceState) {
//        if (savedInstanceState == null) {
//            mFragments[FIRST] = HomeRootFragment.newInstance();
//            mFragments[SECOND] = GankIoRootFragment.newInstance();
//            mFragments[THIRD] = MovieRootFragment.newInstance();
//            mFragments[FOURTH] = BookRootFragment.newInstance();
//            mFragments[FIFTH] = PersonalRootFragment.newInstance();
//
//            loadMultipleRootFragment(R.id.fl_container, FIRST,
//                    mFragments[FIRST],
//                    mFragments[SECOND],
//                    mFragments[THIRD],
//                    mFragments[FOURTH],
//                    mFragments[FIFTH]);
//        } else {
//            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
//            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()
//            // 自行进行判断查找(效率更高些),用下面的方法查找更方便些
//            mFragments[FIRST] = findFragment(HomeRootFragment.class);
//            mFragments[SECOND] = findFragment(GankIoRootFragment.class);
//            mFragments[THIRD] = findFragment(MovieRootFragment.class);
//            mFragments[FOURTH] = findFragment(BookRootFragment.class);
//            mFragments[FIFTH] = findFragment(PersonalRootFragment.class);
//        }
        BottomNavigationView bottomNavigationView=findViewById(R.id.bviv_bar);
        disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.menu_item_home:
//                        showHideFragment(mFragments[FIRST]);
//                        break;
//                    case R.id.menu_item_gank_io:
//                        showHideFragment(mFragments[SECOND]);
//                        break;
//                    case R.id.menu_item_movie:
//                        showHideFragment(mFragments[THIRD]);
//                        break;
//                    case R.id.menu_item_book:
//                        showHideFragment(mFragments[FOURTH]);
//                        break;
//                    case R.id.menu_item_personal:
//                        showHideFragment(mFragments[FIFTH]);
//                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
}
