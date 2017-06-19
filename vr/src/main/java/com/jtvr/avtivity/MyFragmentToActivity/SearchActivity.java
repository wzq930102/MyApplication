package com.jtvr.avtivity.MyFragmentToActivity;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.base.BaseActivity;
import com.jtvr.utils.UIUtils;
import com.jtvr.view.widget.FlowLayoutView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.search_image)
    ImageView searchImage;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_back)
    TextView searchBack;
    @BindView(R.id.search_parent)
    RelativeLayout searchParent;
    @BindView(R.id.search_edit_parent)
    RelativeLayout search_edit_parent;
    @BindView(R.id.search_flowLayout)
    FlowLayoutView searchFlowLayout;
    private String[] titles = {"美女", "冒险", "风景", "美女与野兽", "极限", "生化危机"};

    @Override
    public void initView() {
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        for (int i = 0; i < titles.length; i++) {
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.search_tv, searchFlowLayout, false);
            tv.setText(titles[i]);
            tv.setTag(titles[i].toString());
            searchFlowLayout.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchEdit.setText((String) v.getTag());
                    UIUtils.showToast("敬请期待");
                }
            });
        }
    }

    @Override
    public void initClick() {
        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    UIUtils.showToast("敬请期待");
                }
                return true;
            }
        });
    }

    @OnClick({R.id.search_back, R.id.search_edit_parent})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_edit_parent:

                break;
        }
    }
}
