package com.example.hash.myandroiddemo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hash.myandroiddemo.R;
import com.example.hash.myandroiddemo.ui.homeChild.ChildActivity;

public class HomeFragment extends Fragment {
    private static String TAG = "TAG_____________";
    private HomeViewModel homeViewModel;  // 组件一
    private HomeViewModel2 homeViewModel2; // 组件二
    private Button button1; // 跳转至子界面按钮


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "组件创建fragment----1111");
    }

    // 绘制界面
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.i(TAG, "组件创建onCreateView----1111");

        // 组件一
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);


        // 组件二
        homeViewModel2 =
                ViewModelProviders.of(this).get(HomeViewModel2.class);


        // 组件的容器
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // 获取对应的组件容器
        final TextView textView = root.findViewById(R.id.text_home);
        final TextView textView2 = root.findViewById(R.id.text_home2);

        // 组件一的数据变化监听
        homeViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // 组件二的数据变化监听
        homeViewModel2.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView2.setText(s);
            }
        });
        return root;
    }


    // 界面绘制完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "组件首页被创建完成了---onViewCreated");

        // 1、给按钮绑定监听事件
        button1 = view.findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "我被点击了");
                //  Toast.makeText(getActivity(), "我被点击了", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), ChildActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "组件首页被销毁了----onDestroy");
    }
}