package com.jtvr.fragment.My_Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.avtivity.MyActivity.MemberCatogory;
import com.jtvr.avtivity.MyActivity.PersonInfo;
import com.jtvr.avtivity.MyFragmentToActivity.AboutActivity;
import com.jtvr.avtivity.MyFragmentToActivity.CollectionActivity;
import com.jtvr.avtivity.MyFragmentToActivity.DownloadActivity;
import com.jtvr.avtivity.MyFragmentToActivity.HistoryActivity;
import com.jtvr.avtivity.MyFragmentToActivity.LoginActivity;
import com.jtvr.avtivity.MyFragmentToActivity.MessageActivity;
import com.jtvr.avtivity.MyFragmentToActivity.SettingActivity;
import com.jtvr.avtivity.MyFragmentToActivity.SignActivity;
import com.jtvr.avtivity.MyFragmentToActivity.VIPActivity;
import com.jtvr.base.BaseFragment;
import com.jtvr.utils.SharedPreferencesUtils;
import com.jtvr.utils.UIUtils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我Fragment
 */
public class MyFragment extends BaseFragment {


    @BindView(R.id.my_ImageView)
    CircleImageView myImageView;
    @BindView(R.id.my_cache)
    TextView myCache;
    @BindView(R.id.my_movie)
    TextView myMovie;
    @BindView(R.id.my_history)
    TextView myHistory;
    @BindView(R.id.my_collection)
    TextView myCollection;
    @BindView(R.id.my_vip)
    TextView myVip;
    @BindView(R.id.my_setting)
    TextView mySetting;
    @BindView(R.id.my_suggest)
    TextView mySuggest;
    @BindView(R.id.my_about)
    TextView myAbout;
    @BindView(R.id.my_dengLu)
    TextView myDengLu;
    @BindView(R.id.my_num)
    TextView myNum;//火币数量
    @BindView(R.id.my_lv)
    TextView myLv;//会员等级
    @BindView(R.id.my_Name)
    TextView myName;
    @BindView(R.id.my_login_parent)
    RelativeLayout myLoginParent;
    @BindView(R.id.my_huozhong)
    TextView myHuozhong;

    @Override
    public int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        //注册EventBus
        EventBus.getDefault().register(this);
        if (SharedPreferencesUtils.contains(getContext(), "name")) {
            myDengLu.setVisibility(View.INVISIBLE);
            myName.setVisibility(View.VISIBLE);
            myNum.setVisibility(View.VISIBLE);
            myLv.setVisibility(View.VISIBLE);
            setBitmap((String) SharedPreferencesUtils.get(getContext(), "img", ""));
            myName.setText((String) SharedPreferencesUtils.get(getContext(), "nickName", "默认"));
            myNum.setText((String) SharedPreferencesUtils.get(getContext(), "fires", "0"));
            myLv.setText((String) SharedPreferencesUtils.get(getContext(), "vip", "0"));
        }
    }


    private void setBitmap(String img) {
        if ("".equals(img)) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.my_user_photo_login);
            myImageView.setImageBitmap(bitmap);
        } else {
            Picasso.with(getContext()).load((String) SharedPreferencesUtils.get(getContext(), "img", "")).into(myImageView);
        }

    }


    @OnClick({R.id.my_cache, R.id.my_movie, R.id.my_history, R.id.my_collection, R.id.my_vip
            , R.id.my_setting, R.id.my_suggest, R.id.my_about, R.id.my_login_parent
            , R.id.my_huozhong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_login_parent:
                if (SharedPreferencesUtils.contains(getContext(), "name")) {
                    startActivityForResult(new Intent(getContext(), PersonInfo.class), 1);
                } else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.my_cache:
                startActivity(new Intent(getContext(), DownloadActivity.class));
                break;
            case R.id.my_movie:
                startActivity(new Intent(getContext(), SignActivity.class));
                break;
            case R.id.my_history:
                startActivity(new Intent(getContext(), HistoryActivity.class));
                break;
            case R.id.my_huozhong://火种充值
                if (UIUtils.isLogin(getContext())) {
                    startActivityForResult(new Intent(getContext(), MemberCatogory.class), 3);
                } else {
                    UIUtils.startLoginActivity(getContext());
                }
                break;
            case R.id.my_collection:
                startActivity(new Intent(getContext(), CollectionActivity.class));
                break;
            case R.id.my_vip:
                startActivity(new Intent(getContext(), VIPActivity.class));
                break;
            case R.id.my_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.my_suggest:
                startActivity(new Intent(getContext(), MessageActivity.class));
                break;
            case R.id.my_about:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
        }
    }

    /**
     * 在Ui线程执行
     * 不管发送消息在那个线程  都在主线程接收
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String o) {
        if ("99".equals(o)) {
            myDengLu.setVisibility(View.INVISIBLE);
            myNum.setVisibility(View.VISIBLE);
            myLv.setVisibility(View.VISIBLE);
            myName.setVisibility(View.VISIBLE);
            setBitmap((String) SharedPreferencesUtils.get(getContext(), "img", ""));
            myName.setText((String) SharedPreferencesUtils.get(getContext(), "nickName", "默认"));
            myNum.setText((String) SharedPreferencesUtils.get(getContext(), "fires", "0"));
            myLv.setText((String) SharedPreferencesUtils.get(getContext(), "vip", "0"));
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK && data != null) {
            String string = data.getStringExtra("result");
            if ("clean".equals(string)) {
                myName.setVisibility(View.INVISIBLE);
                myNum.setVisibility(View.INVISIBLE);
                myLv.setVisibility(View.INVISIBLE);
                myDengLu.setVisibility(View.VISIBLE);
                //设置默认图像
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.my_user_photo_login);
                myImageView.setImageBitmap(bitmap);
            }
            if ("revise".equals(string)) {
                Picasso.with(getContext()).load((String) SharedPreferencesUtils.get(getContext(), "img", "")).into(myImageView);
                myName.setText((String) SharedPreferencesUtils.get(getContext(), "nickName", ""));
            }
        }

        if (requestCode == 3 && resultCode == getActivity().RESULT_OK && data != null) {
            String string = data.getStringExtra("result");
            if ("num".equals(string)) {
                myNum.setText((String) SharedPreferencesUtils.get(getContext(), "fires", "0"));
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除注册
        EventBus.getDefault().unregister(this);
    }
}
