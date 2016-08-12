package cn.rvlr.recyclerviewloadrefresh.testrecycler;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.rvlr.recyclerviewloadrefresh.BR;
import cn.rvlr.recyclerviewloadrefresh.testrecycler.TestRecyclerViewBinding;

/**
 * Recycler在Fragment中绑定数据，，并修改局部item
 */
public class FragmentRecyclerView extends Fragment {
    private static final String TAG = "FragmentRecyclerView";
    private ViewModel viewModel;
    public  FragmentRecyclerView(){
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        viewModel = new ViewModel(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TestRecyclerViewBinding binding = TestRecyclerViewBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        //点击事件如果是单独写的类对象，则先设置点击事件的类
        binding.setListeners(new Listeners(viewModel));
        //执行
        //binding.executePendingBindings();


        return binding.getRoot();
    }
}
