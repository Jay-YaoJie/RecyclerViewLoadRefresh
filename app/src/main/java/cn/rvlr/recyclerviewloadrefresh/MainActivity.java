package cn.rvlr.recyclerviewloadrefresh;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.rvlr.recyclerviewloadrefresh.network.RecycleBindingActivity;
import cn.rvlr.recyclerviewloadrefresh.testrecycler.FragmentRecyclerView;
import cn.rvlr.recyclerviewloadrefresh.MainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
       // binding.buttonOk;
        binding.buttonOk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RecycleBindingActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Fragment fragment = new FragmentRecyclerView();
        getSupportFragmentManager().beginTransaction()
                .replace(binding.content.getId(), fragment)
                .commit();
    }
}
