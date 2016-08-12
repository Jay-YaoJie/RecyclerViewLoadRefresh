package cn.rvlr.recyclerviewloadrefresh.network;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.rvlr.recyclerviewloadrefresh.R;
import cn.rvlr.recyclerviewloadrefresh.mvvmframework.utils.OnViewModelNotifyListener;
import cn.rvlr.recyclerviewloadrefresh.network.RecycleBinding;

/**
 * 双向绑定refreshing 状态，绑定 BaseViewModel 的 加载，出错，网络异常，空数据状态（你可以通过 更改 飞行模式控制网络状态 然后下拉刷新数据 来测试例子）
 */
public class RecycleBindingActivity extends AppCompatActivity implements OnViewModelNotifyListener {
    public static final int CODE_ITEM = 0;
    public static final int CODE_HEADER_FOOTER = 1;
    private RecycleModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    RecycleBinding binding = DataBindingUtil.setContentView(this, R.layout.recycle_view);
        viewModel = new RecycleModel();
        viewModel.setOnViewModelNotifyListener(this);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onListRefresh();
    }

    //在ViewModel和activity之间的值传递
    @Override
    public void onViewModelNotify(Bundle bundle, int code) {
        switch (code){
            case CODE_ITEM:
                String  ticket =bundle.getString("model");
                Toast.makeText(this,ticket,Toast.LENGTH_SHORT).show();
                break;
            case CODE_HEADER_FOOTER:
                Toast.makeText(this,bundle.getString("msg"),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
