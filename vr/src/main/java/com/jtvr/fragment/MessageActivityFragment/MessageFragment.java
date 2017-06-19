package com.jtvr.fragment.MessageActivityFragment;


import android.app.Fragment;
import android.view.View;

import com.jt.base.R;
import com.jtvr.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment {

    @Override
    public int setLayout() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);

    }
}
