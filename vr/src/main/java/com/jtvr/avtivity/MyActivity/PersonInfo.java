package com.jtvr.avtivity.MyActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.classic.common.MultipleStatusView;
import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.avtivity.MyFragmentToActivity.ForgetActivity;
import com.jtvr.base.BaseActivity;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.ImgBean;
import com.jtvr.model.JsonBean;
import com.jtvr.model.PersonInfoBean;
import com.jtvr.model.SuccedInfo;
import com.jtvr.utils.IMGUtils;
import com.jtvr.utils.SharedPreferencesUtils;
import com.jtvr.utils.UIUtils;
import com.jtvr.utils.pickAddressView;
import com.jtvr.view.widget.ActionSheetDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * Created by jt on 2017/4/7.
 */
public class PersonInfo extends BaseActivity {

    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.title_back)
    ImageView mTitleBack;
    @BindView(R.id.title_register)
    TextView mTitleRegister;
    @BindView(R.id.personinfo_tx_iv)
    CircleImageView mPersoninfoTxIv;
    @BindView(R.id.personinfo_tx_re)
    RelativeLayout mPersoninfoTxRe;
    @BindView(R.id.personinfo_nc)
    TextView mPersoninfoNc;
    @BindView(R.id.personinfo_ncce)
    TextView mPersoninfoNcce;
    @BindView(R.id.personinfo_nc_right)
    TextView mPersoninfoNcRight;
    @BindView(R.id.personinfo_nc_re)
    RelativeLayout mPersoninfoNcRe;
    @BindView(R.id.personinfo_mm)
    TextView mPersoninfoMm;
    @BindView(R.id.personinfo_mm_cen)
    TextView mPersoninfoMmCen;
    @BindView(R.id.personinfo_mm_right)
    TextView mPersoninfoMmRight;
    @BindView(R.id.personinfo_mm_re)
    RelativeLayout mPersoninfoMmRe;
    @BindView(R.id.personinfo_xb)
    TextView mPersoninfoXb;
    @BindView(R.id.personinfo_xb_right)
    TextView mPersoninfoXbRight;
    @BindView(R.id.personinfo_xb_re)
    RelativeLayout mPersoninfoXbRe;
    @BindView(R.id.personinfo_xb_ce)
    TextView mPersoninfoXbce;
    @BindView(R.id.personinfo_sr)
    TextView mPersoninfoSr;
    @BindView(R.id.personinfo_sr_ce)
    TextView mPersoninfoSrcn;
    @BindView(R.id.personinfo_sr_right)
    TextView mPersoninfoSrRight;
    @BindView(R.id.personinfo_sr_re)
    RelativeLayout mPersoninfoSrRe;
    @BindView(R.id.personinfo_zy)
    TextView mPersoninfoZy;
    @BindView(R.id.personinfo_zy_cn)
    TextView mPersoninfoZyce;
    @BindView(R.id.personinfo_zy_right)
    TextView mPersoninfoZyRight;
    @BindView(R.id.personinfo_zy_re)
    RelativeLayout mPersoninfoZyRe;
    @BindView(R.id.personinfo_dz)
    TextView mPersoninfoDz;
    @BindView(R.id.personinfo_dz_right)
    TextView mPersoninfoDzRight;
    @BindView(R.id.personinfo_dz_ce)
    TextView mPersoninfoDzce;
    @BindView(R.id.personinfo_dz_re)
    RelativeLayout mPersoninfoDzRe;
    @BindView(R.id.personinfo_yx)
    TextView mPersoninfoYx;
    @BindView(R.id.personinfo_yx_ce)
    TextView mPersoninfoYxce;
    @BindView(R.id.personinfo_yx_right)
    TextView mPersoninfoYxRight;
    @BindView(R.id.personinfo_yx_re)
    RelativeLayout mPersoninfoYxRe;
    @BindView(R.id.personinfo_leaver)
    TextView mPersoninfoLeaver;
    @BindView(R.id.personinfo_phonece)
    TextView mPersoninfoPhonece;
    @BindView(R.id.personinfo_phone_re)
    RelativeLayout mPersoninfoPhoneRe;
    private Context mContext;
    String img = "";
    private Bitmap bitmap;
    private File tempFile;
    MultipleStatusView multipleStatusView;
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final int NICKNAME = 4;// 结果
    private static final int PROFESSION = 5;// 结果
    private static final int EMAIL = 6;// 结果
    private static final String PHOTO_FILE_NAME = "head.jpg";

    @Override
    public void initView() {
        setContentView(R.layout.activity_personinfo);
        ButterKnife.bind(this);
        mContext = PersonInfo.this;
        multipleStatusView = (MultipleStatusView) findViewById(R.id.main_multiplestatusview);
        multipleStatusView.showLoading();
        mTitleTitle.setText("我的个人信息");
        mTitleRegister.setVisibility(View.GONE);
        getPersonInfo();
    }

    private void getPersonInfo() {
        VrHttp.getInstance().requestForResult(RequestEnum.GETPERSON, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                PersonInfoBean pib = new Gson().fromJson(response.toString(), PersonInfoBean.class);
                if (pib.getStatus() != null) {
                    if (pib.getStatus().equals("99")) {
                        multipleStatusView.showContent();
                        setPersonInfo(pib);
                    }
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
            }
        }, RequestEnum.GETPERSON.addIntent());
    }

    private void setPersonInfo(PersonInfoBean pib) {
        SharedPreferencesUtils.put(mContext, "fires", pib.getBusiness().getFires());
        UIUtils.setImgeView(pib.getBusiness().getImg(), mPersoninfoTxIv);
        img = pib.getBusiness().getImg();
        if (pib.getBusiness().getAddress() != null) {
            mPersoninfoPhonece.setText(pib.getBusiness().getPhone());
            mPersoninfoNcce.setText(pib.getBusiness().getNick());
            mPersoninfoYxce.setText(pib.getBusiness().getEmail());
            mPersoninfoSrcn.setText(pib.getBusiness().getDateOfBirty());
            mPersoninfoDzce.setText(pib.getBusiness().getAddress());
            mPersoninfoXbce.setText(pib.getBusiness().getSexMen());
            mPersoninfoZyce.setText(pib.getBusiness().getProfession());
        }
    }

    @Override
    public void initClick() {
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", "revise");
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    @OnClick({R.id.personinfo_phone_re, R.id.title_back, R.id.title_register, R.id.personinfo_tx_re, R.id.personinfo_nc_re, R.id.personinfo_mm_re, R.id.personinfo_xb_re, R.id.personinfo_sr_re, R.id.personinfo_zy_re, R.id.personinfo_dz_re, R.id.personinfo_yx_re, R.id.personinfo_leaver})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personinfo_phone_re://手机号
                UIUtils.showToast("暂不支持更换手机号！");
                break;
            case R.id.title_back:
                Intent intent1 = new Intent();
                intent1.putExtra("result", "revise");
                setResult(RESULT_OK, intent1);
                finish();
                break;
            case R.id.title_register:
                break;
            case R.id.personinfo_tx_re:
                showActionSheet();
                break;
            case R.id.personinfo_nc_re:
                Intent i = new Intent(mContext, NickNameActivity.class);
                startActivityForResult(i, 4);
                break;
            case R.id.personinfo_mm_re:
                startActivity(new Intent(this, ForgetActivity.class));
                break;
            case R.id.personinfo_xb_re:
                showSexSheet();
                break;
            case R.id.personinfo_sr_re:
                showDatapickView();
                break;
            case R.id.personinfo_zy_re:
                Intent i2 = new Intent(mContext, ProfessionActivity.class);
                startActivityForResult(i2, 5);
                break;
            case R.id.personinfo_dz_re:
                pickAddressView pv = new pickAddressView(mContext, new pickAddressView.isParseListen() {
                    @Override
                    public void setIfsuccsed(ArrayList<JsonBean> options1Items, ArrayList<ArrayList<String>> options2Items, ArrayList<ArrayList<ArrayList<String>>> options3Items) {
                        ShowPickerView(options1Items, options2Items, options3Items);
                    }
                });
                pv.showAddressPick();
                break;
            case R.id.personinfo_yx_re:
                Intent i3 = new Intent(mContext, EmailActivity.class);
                startActivityForResult(i3, 6);
                break;
            case R.id.personinfo_leaver://退出登录
                VrHttp.getInstance().requestForResult(RequestEnum.CLEANLEAVER, new VrHttp.OnGetDataResult() {
                    @Override
                    public void Response(String response, int id) {
                        SharedPreferencesUtils.clear(mContext);
                        Intent intent = new Intent();
                        intent.putExtra("result", "clean");
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                }, RequestEnum.CLEANLEAVER.addIntent());

                break;
        }
    }

    private void showActionSheet() {
        new ActionSheetDialog(mContext)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("照相机", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                getCamera();
                            }
                        })
                .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                getGallery();

                            }
                        }).show();
    }

    /**
     * 相册
     */
    private void getGallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /**
     * 照相
     */
    private void getCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);

    }

    /**
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 时间选择
     */
    public void showDatapickView() {
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mPersoninfoSrcn.setText(getTime(date));
                savePersonInfo(img, mPersoninfoNcce.getText().toString(), mPersoninfoYxce.getText().toString(), mPersoninfoSrcn.getText().toString(), mPersoninfoDzce.getText().toString(), mPersoninfoXbce.getText().toString(), mPersoninfoZyce.getText().toString());
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .build();

        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 性别
     */
    private void showSexSheet() {
        new ActionSheetDialog(mContext)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("男", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                mPersoninfoXbce.setText("男");
                                savePersonInfo(img, mPersoninfoNcce.getText().toString(), mPersoninfoYxce.getText().toString(), mPersoninfoSrcn.getText().toString(), mPersoninfoDzce.getText().toString(), mPersoninfoXbce.getText().toString(), mPersoninfoZyce.getText().toString());
                            }
                        })
                .addSheetItem("女", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                mPersoninfoXbce.setText("女");
                                savePersonInfo(img, mPersoninfoNcce.getText().toString(), mPersoninfoYxce.getText().toString(), mPersoninfoSrcn.getText().toString(), mPersoninfoDzce.getText().toString(), mPersoninfoXbce.getText().toString(), mPersoninfoZyce.getText().toString());
                            }
                        })
                .addSheetItem(" 其他", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                mPersoninfoXbce.setText("其他");
                                savePersonInfo(img, mPersoninfoNcce.getText().toString(), mPersoninfoYxce.getText().toString(), mPersoninfoSrcn.getText().toString(), mPersoninfoDzce.getText().toString(), mPersoninfoXbce.getText().toString(), mPersoninfoZyce.getText().toString());
                            }
                        }).show();
    }

    private void ShowPickerView(final ArrayList<JsonBean> options1Items, final ArrayList<ArrayList<String>> options2Items, final ArrayList<ArrayList<ArrayList<String>>> options3Items) {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                mPersoninfoDzce.setText(tx);
                savePersonInfo(img, mPersoninfoNcce.getText().toString(), mPersoninfoYxce.getText().toString(), mPersoninfoSrcn.getText().toString(), mPersoninfoDzce.getText().toString(), mPersoninfoXbce.getText().toString(), mPersoninfoZyce.getText().toString());
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case PHOTO_REQUEST_GALLERY:
                    if (data != null) {
                        // 得到图片的全路径
                        Uri uri = data.getData();
                        crop(uri);
                    }
                    break;
                case PHOTO_REQUEST_CAMERA:
                    if (hasSdcard()) {
                        tempFile = new File(Environment.getExternalStorageDirectory(),
                                PHOTO_FILE_NAME);
                        crop(Uri.fromFile(tempFile));
                    } else {
                        UIUtils.showToast("未找到存储卡，无法存储照片！");
                    }
                    break;
                case PHOTO_REQUEST_CUT:
                    try {
                        bitmap = data.getParcelableExtra("data");
                        setImageToView(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    if (data != null) {
                        String s = data.getStringExtra("nickname");
                        mPersoninfoNcce.setText(s);
                        savePersonInfo(img, mPersoninfoNcce.getText().toString(), mPersoninfoYxce.getText().toString(), mPersoninfoSrcn.getText().toString(), mPersoninfoDzce.getText().toString(), mPersoninfoXbce.getText().toString(), mPersoninfoZyce.getText().toString());
                    }
                    break;
                case 5:
                    if (data != null) {
                        String s = data.getStringExtra("profession");
                        mPersoninfoZyce.setText(s);
                        savePersonInfo(img, mPersoninfoNcce.getText().toString(), mPersoninfoYxce.getText().toString(), mPersoninfoSrcn.getText().toString(), mPersoninfoDzce.getText().toString(), mPersoninfoXbce.getText().toString(), mPersoninfoZyce.getText().toString());
                    }
                    break;
                case 6:
                    if (data != null) {
                        String s = data.getStringExtra("email");
                        mPersoninfoYxce.setText(s);
                        savePersonInfo(img, mPersoninfoNcce.getText().toString(), mPersoninfoYxce.getText().toString(), mPersoninfoSrcn.getText().toString(), mPersoninfoDzce.getText().toString(), mPersoninfoXbce.getText().toString(), mPersoninfoZyce.getText().toString());
                    }
                    break;

            }
        }
    }

    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Bitmap bitmap) {

        Bitmap photo = bitmap;
        String imagePath = IMGUtils.savePhoto(photo, Environment
                .getExternalStorageDirectory().getAbsolutePath(), "head");
        File file = new File(imagePath);
        if (!file.exists()) {
            Toast.makeText(mContext, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgressDialog(mContext, "图片设置中...");
        uploadPic(file, photo);
    }

    private void uploadPic(File file, final Bitmap photo) {
        VrHttp.getInstance().requestForResultFils(RequestEnum.IMGCOMMIT, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                ImgBean ig = new Gson().fromJson(response.toString(), ImgBean.class);
                if (ig.getStatus().equals("99")) {
                    img = ig.getBusiness().getImg();
                    hideProgressDialog();
                    mPersoninfoTxIv.setImageBitmap(photo);
                    savePersonInfo(img, mPersoninfoNcce.getText().toString(), mPersoninfoYxce.getText().toString(), mPersoninfoSrcn.getText().toString(), mPersoninfoDzce.getText().toString(), mPersoninfoXbce.getText().toString(), mPersoninfoZyce.getText().toString());
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.IMGCOMMIT.addIntent("head.png", "user"), "file", "head.png", file);
    }

    private void savePersonInfo(final String img, final String nickName, final String email, final String dateOfBirty, final String address, final String sexMem, final String profession) {
        showProgressDialog(mContext, "正在保存数据");
        VrHttp.getInstance().requestForResult(RequestEnum.PERSONINFOCOMMIT, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                SuccedInfo si = new Gson().fromJson(response.toString(), SuccedInfo.class);
                if (si.getStatus() != null) {
                    if (si.getStatus().equals("99")) {
                        UIUtils.showToast(si.getMsg());
                        hideProgressDialog();
                        saveShareInfo(img, nickName, email, dateOfBirty, address, sexMem, profession);
                    }
                } else {
                    UIUtils.showToast("数据获取异常");
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
            }
        }, RequestEnum.PERSONINFOCOMMIT.addIntent(img, nickName, email, dateOfBirty, address, sexMem, profession));
    }

    private void saveShareInfo(String img, String nickName, String email, String dateOfBirty, String address, String sexMem, String profession) {
        if (!("".equals(img))) {
            SharedPreferencesUtils.put(mContext, "img", img);
        }
        if (!("".equals(nickName))) {
            SharedPreferencesUtils.put(mContext, "nickName", nickName);
        }
        if (!("".equals(email))) {
            SharedPreferencesUtils.put(mContext, "email", email);
        }
        if (!("".equals(address))) {
            SharedPreferencesUtils.put(mContext, "address", address);
        }
        if (!("".equals(sexMem))) {
            SharedPreferencesUtils.put(mContext, "sexMem", sexMem);
        }
        if (!("".equals(profession))) {
            SharedPreferencesUtils.put(mContext, "profession", profession);
        }
        if (!("".equals(dateOfBirty))) {
            SharedPreferencesUtils.put(mContext, "dateOfBirty", dateOfBirty);
        }
    }


}
