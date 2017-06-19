package com.jtvr.avtivity.MyActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.classic.common.MultipleStatusView;
import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.MemberCatogoryAdapter;
import com.jtvr.base.BaseActivity;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.BuyFiresSuceed;
import com.jtvr.model.FirsConfiguration;
import com.jtvr.utils.SharedPreferencesUtils;
import com.jtvr.utils.UIUtils;
import com.jtvr.view.widget.ActionSheetDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by jt on 2017/4/6.
 */
public class MemberCatogory extends BaseActivity {
    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.title_back)
    ImageView mTitleBack;
    @BindView(R.id.title_register)
    TextView mTitleRegister;
    @BindView(R.id.membercategory_num)
    TextView num;
    @BindView(R.id.membercategory_recycler)
    RecyclerView recycler;
    private Context mContext;
    MemberCatogoryAdapter myadapter;
    FirsConfiguration firsInfo;
    MultipleStatusView multipleStatusView;
    @Override
    public void initView() {
        setContentView(R.layout.activity_membercategory);
        ButterKnife.bind(this);
        mTitleTitle.setText("火种充值");
        mTitleRegister.setText("交易记录");
        mContext = MemberCatogory.this;
        multipleStatusView = (MultipleStatusView) findViewById(R.id.main_multiplestatusview);
        multipleStatusView.showLoading();
        num.setText(SharedPreferencesUtils.get(mContext,"fires","0").toString());
        getFirsConfiguration();

    }

    private void getFirsConfiguration() {
        showProgressDialog(mContext,"正在加载");
        VrHttp.getInstance().requestForResult(RequestEnum.FIRSCONFIGURATION, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                firsInfo = new Gson().fromJson(response.toString(),FirsConfiguration.class);
                if(firsInfo!=null){
                    if("99".equals(firsInfo.getStatus())){
                        hideProgressDialog();
                        multipleStatusView.showContent();
                        myadapter = new MemberCatogoryAdapter(mContext,firsInfo.getBusness().getList());
                        recycler.setLayoutManager(new LinearLayoutManager(mContext));
                        recycler.setAdapter(myadapter);
                        myadapter.setOnClickItemListen(new MemberCatogoryAdapter.ItemClickListen() {
                            @Override
                            public void ClickItem(int i) {
                                showActionSheet( firsInfo.getBusness().getList().get(i).getMoney()+"",firsInfo.getBusness().getList().get(i).getBuyFire()+"",firsInfo.getBusness().getList().get(i).getGiveFire()+"" );
                            }
                        });
                    }
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {
            }
        },RequestEnum.FIRSCONFIGURATION.addIntent());

    }

    @Override
    public void initClick() {
        mTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("result", "num");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mTitleRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MemberCatogory.this,tradingrecord.class);
                startActivity(i);
            }
        });
    }
    private void showActionSheet(final String s1, final String s2, final String s3) {
        new ActionSheetDialog(MemberCatogory.this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("微信支付", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                buyFires(s1,s2,s3);
                            }
                        })
                .addSheetItem("支付宝", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Toast.makeText(MemberCatogory.this, "支付宝", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
    }
    private void buyFires(String s1,String s2,String s3) {
        VrHttp.getInstance().requestForResult(RequestEnum.BUTFIRS, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {

                BuyFiresSuceed si = new Gson().fromJson(response.toString(),BuyFiresSuceed.class);
                if(si.getStatus()!=null){
                    if(si.getStatus().equals("99")){
                        UIUtils.showToast(si.getMsg());
                        num.setText(si.getFires());
                        SharedPreferencesUtils.put(mContext,"fires",si.getFires());
                    }
                }else{
                    UIUtils.showToast("数据请求异常，支付失败！");
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {
            }
        },RequestEnum.BUTFIRS.addIntent(s1,s2,s3));
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", "num");
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
