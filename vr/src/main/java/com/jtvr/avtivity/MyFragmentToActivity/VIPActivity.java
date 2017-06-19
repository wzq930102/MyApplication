package com.jtvr.avtivity.MyFragmentToActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.CustomAdapter;
import com.jtvr.avtivity.MyActivity.tradingrecord;
import com.jtvr.base.BaseActivity;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.BuyVipData;
import com.jtvr.model.SuccedInfo;
import com.jtvr.model.VIPBean;
import com.jtvr.utils.SharedPreferencesUtils;
import com.jtvr.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

public class VIPActivity extends BaseActivity {
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.my_ImageView)
    CircleImageView myImageView;
    @BindView(R.id.my_dengLu)
    TextView myDengLu;
    @BindView(R.id.my_Name)
    TextView myName;
    @BindView(R.id.my_dengLu_parent)
    RelativeLayout myDengLuParent;
    @BindView(R.id.vip_RecyclerView)
    RecyclerView vipRecyclerView;
    @BindView(R.id.login_input)
    Button loginInput;
    private CustomAdapter adapter;
    private List<VIPBean> list;
    private Context mContext;
    private  BuyVipData bvd;
    private int selectVIP=0;

    @Override
    public void initView() {
        setContentView(R.layout.activity_vip);
        ButterKnife.bind(this);
        titleTitle.setText("开通VIP");
        titleRegister.setText("开通记录");
        mContext = VIPActivity.this;
        initBeginData();
        initData();
    }
    private void setAdapterData() {
        adapter = new CustomAdapter(this, list);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        vipRecyclerView.setNestedScrollingEnabled(false);
        vipRecyclerView.setLayoutManager(manager);
        vipRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CustomAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selectVIP = position;
            }
        });
    }

    private void PayMoney(String money, int month) {
        VrHttp.getInstance().requestForResult(RequestEnum.BUYVIP, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                SuccedInfo si = new Gson().fromJson(response.toString(),SuccedInfo.class);
                if(si.getStatus()!=null){
                    if("41".equals(si.getStatus())){
                        Toast.makeText(mContext,si.getErrmsg(),Toast.LENGTH_SHORT).show();
                        UIUtils.startLoginActivity(mContext);
                    } else if("99".equals(si.getStatus())){
                        Toast.makeText(mContext,si.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {
            }
        }, RequestEnum.BUYVIP.addIntent("1","",money,"",month));


    }

    private void initBeginData() {
        if (SharedPreferencesUtils.contains(mContext, "name")) {
            UIUtils.setImgeView(SharedPreferencesUtils.get(mContext, "img", "").toString(),myImageView);
            myDengLu.setText(SharedPreferencesUtils.get(mContext, "nickName", "默认").toString());
            if("y".equals(SharedPreferencesUtils.get(mContext, "isvip", "n"))){
                myName.setText("尊敬的VIP会员");
            }
            myDengLu.setClickable(false);
        }
    }

    private void initData() {
        VrHttp.getInstance().requestForResult(RequestEnum.BUYVIPDATA, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                bvd = new Gson().fromJson(response.toString(),BuyVipData.class);
                if(bvd.getStatus()!=null){
                    if("99".equals(bvd.getStatus())){
                        list = new ArrayList<>();
                        for(int i =0;i<bvd.getBusiness().getList().size();i++){
                            VIPBean b = new VIPBean(bvd.getBusiness().getList().get(i).getMonth()+"个月会员",bvd.getBusiness().getList().get(i).getMoney());
                            list.add(b);
                        }
                        setAdapterData();
                    }
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {
            }
        }, RequestEnum.BUYVIPDATA.addIntent());
    }

    @Override
    public void initClick() {

    }

    @OnClick({R.id.title_back, R.id.title_register, R.id.login_input,R.id.my_dengLu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_register:
                Intent i =new Intent(mContext,tradingrecord.class);
                startActivity(i);
                break;
            case R.id.login_input:
                PayMoney(bvd.getBusiness().getList().get(selectVIP).getMoney(),bvd.getBusiness().getList().get(selectVIP).getMonth());
                break;
            case R.id.my_dengLu:
                UIUtils.startLoginActivity(mContext);
                break;
        }
    }
}
