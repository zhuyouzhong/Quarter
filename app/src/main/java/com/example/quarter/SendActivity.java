package com.example.quarter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.quarter.adapter.MySendRecycleView;
import com.example.quarter.adapter.MySendVideoXRecycle;
import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.SendBean;
import com.example.quarter.presenter.SendPersenter;
import com.example.quarter.public_class.GlideLoader;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.SendView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class SendActivity extends BaseActivity<SendPersenter> implements SendView {


    private TextView tv_quxiao;
    private TextView tv_send;
    private EditText et_center;
    private RecyclerView rlv_iv;
    private ImageView iv_shangchuan;
    private ArrayList<String> path = new ArrayList<>();
    private MySendRecycleView mySendRecycleView;
    private ArrayList<LocalMedia> selectList = new ArrayList<>();
    private MySendVideoXRecycle mySendVideoXRecycle;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String longitude;
    private String latitude;

    @Override
    public SendPersenter initPresenter() {
        SendPersenter sendPersenter=new SendPersenter(this);
        return sendPersenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_send;
    }

    @Override
    public void initView() {

        locationClient = new AMapLocationClient(getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        locationClient.startLocation();

        rlv_iv = findViewById(R.id.rlv_iv);
        iv_shangchuan = findViewById(R.id.iv_shangchuan);
        tv_quxiao = findViewById(R.id.tv_quxiao);
        tv_send = findViewById(R.id.tv_send);
        et_center = findViewById(R.id.et_center);

        initOkClick();
    }

    private void initOkClick() {
        final SharedPreferences jz = getSharedPreferences("JZ", MODE_PRIVATE);
        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean jz1 = jz.getBoolean("jz", true);
                if(jz1==true)
                {
                    String center = et_center.getText().toString();
                    if(TextUtils.isEmpty(center))
                    {
                        Toast.makeText(SendActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(path.size()==0)
                    {
                        presenter.SendPresenterSuccess(MyInterceptor.id,center,null,null,null,null,null,null);
                    }
                    else
                    {
                        presenter.SendPresenterSuccess(MyInterceptor.id,center,path,null,null,null,null,null);
                    }

                }
               else
                {

                    String center = et_center.getText().toString();
                    if(selectList.size()==0)
                    {
                        Toast.makeText(SendActivity.this, "视频不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        ArrayList<String> list=new ArrayList<>();
                        list.add("/storage/sdcard/tu/-1061169778");
                        //list.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2639088341,2223755776&fm=27&gp=0.jpg");
                        System.out.println("封面的长度"+list.size());
                        System.out.println("纬度"+latitude + "+++++经度" +longitude);
                        /*for (int i = 0; i <selectList.size() ; i++) {
                            LocalMedia localMedia = selectList.get(i);
                            String path = localMedia.getPath();
                            File file=new File(path);
                        }*/
                        File file=new File(selectList.get(0).getPath());
                        System.out.println("====================视频路径"+selectList.get(0).getPath());
                        File fileImage=new File("/mnt/sdcard/mv.jpg");
                        presenter.SendPresenterSuccess(MyInterceptor.id,null,null,file,fileImage,center,latitude,longitude);
                    }
                }
                ProgressDialog mypDialog=new ProgressDialog(SendActivity.this);
                      //实例化
                          mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        //设置进度条风格，风格为圆形，旋转的
                          mypDialog.setTitle("提示");
                            //设置ProgressDialog 标题
                           mypDialog.setMessage("正在上传...");
                          //设置ProgressDialog 提示信息
                           mypDialog.setIcon(R.drawable.ic_launcher_background);
                           //设置ProgressDialog 标题图标
                            mypDialog.setIndeterminate(false);
                           //设置ProgressDialog 的进度条是否不明确
                           mypDialog.setCancelable(true);
                            //设置ProgressDialog 是否可以按退回按键取消
                             mypDialog.show();
                            //让ProgressDialog显示

            }
        });
        iv_shangchuan.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                SharedPreferences jz = getSharedPreferences("JZ", MODE_PRIVATE);
                boolean jz1 = jz.getBoolean("jz", true);
                if(jz1==true)
                {
                    ImageConfig imageConfig = new ImageConfig.Builder(
                            // GlideLoader 可用自己用的缓存库
                            new GlideLoader())
                            // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                            .steepToolBarColor(getResources().getColor(R.color.blue))
                            // 标题的背景颜色 （默认黑色）
                            .titleBgColor(getResources().getColor(R.color.blue))
                            // 提交按钮字体的颜色  （默认白色）
                            .titleSubmitTextColor(getResources().getColor(R.color.white))
                            // 标题颜色 （默认白色）
                            .titleTextColor(getResources().getColor(R.color.white))
                            // 开启多选   （默认为多选）  (单选 为 singleSelect)
                            .mutiSelect()
                            //剪切
                            //.crop()
                            // 多选时的最大数量   （默认 9 张）
                            .mutiSelectMaxSize(9)
                            // 已选择的图片路径
                            .pathList(path)
                            // 拍照后存放的图片路径（默认 /temp/picture）
                            .filePath("/ImageSelector/Pictures")
                            // 开启拍照功能 （默认开启）
                            .showCamera()
                            .requestCode(REQUEST_CODE)
                            .build();
                    ImageSelector.open(SendActivity.this, imageConfig);   // 开启图片选择器
                    mySendRecycleView = new MySendRecycleView(path,SendActivity.this);
                    rlv_iv.setLayoutManager(new GridLayoutManager(SendActivity.this,3));
                    rlv_iv.setAdapter(mySendRecycleView);
                    Toast.makeText(SendActivity.this, "段子", Toast.LENGTH_SHORT).show();
                }
                if(jz1==false)
                {
                    Toast.makeText(SendActivity.this, "视频", Toast.LENGTH_SHORT).show();
                    // 进入相册 以下是例子：用不到的api可以不写
                    PictureSelector.create(SendActivity.this)
                            .openGallery(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                           // .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .minSelectNum(0)// 最小选择数量 int
                            .imageSpanCount(4)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .previewImage(true)// 是否可预览图片 true or false
                            .previewVideo(true)// 是否可预览视频 true or false
                            .enablePreviewAudio(true) // 是否可播放音频 true or false
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                            .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                            .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                            .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                            .enableCrop(true)// 是否裁剪 true or false
                            .compress(true)// 是否压缩 true or false
                            //.glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                            //.withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                            .isGif(true)// 是否显示gif图片 true or false
                            //.compressSavePath(getPath())//压缩图片保存地址
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                            .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                            .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                            .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                            .openClickSound(true)// 是否开启点击声音 true or false
                            .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                           // .previewEggs()// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                            //.cropCompressQuality()// 裁剪压缩质量 默认90 int
                            .minimumCompressSize(100)// 小于100kb的图片不压缩
                            .synOrAsy(true)//同步true或异步false 压缩 默认同步
                          //  .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                            .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                            .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                            .videoQuality(0)// 视频录制质量 0 or 1 int
                            .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                            .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                            .recordVideoSecond(10)//视频秒数录制 默认60s int
                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                    mySendVideoXRecycle = new MySendVideoXRecycle(selectList,SendActivity.this);
                    rlv_iv.setLayoutManager(new GridLayoutManager(SendActivity.this,3));
                    rlv_iv.setAdapter(mySendVideoXRecycle);
                }
            }

        });
    }

    @Override
    public void SendSuccess(SendBean value) {
            Toast.makeText(this, value.getMsg(), Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(SendActivity.this,SendSuccessActivity.class);
            startActivity(intent);
            finish();
    }

    @Override
    public void SendFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(SendActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void SendError(Throwable e) {

    }

    public static final int REQUEST_CODE = 1000;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                Log.i("ImagePathList", path);
            }
            path.clear();
            path.addAll(pathList);
            mySendRecycleView.notifyDataSetChanged();
        }

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selec = PictureSelector.obtainMultipleResult(data);
                   /* for (int i = 0; i <selec.size() ; i++) {
                        LocalMedia localMedia = selec.get(i);
                        path1 = localMedia.getPath();
                    }*/

                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                   /* adapter.setList(selectList);
                    adapter.notifyDataSetChanged();*/
                    selectList.clear();
                    selectList.addAll(selec);
                    mySendVideoXRecycle.notifyDataSetChanged();
                    break;
            }
        }
    }


    //经纬度的获取
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true

        return mOption;
    }

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(final AMapLocation aMapLocation) {
            runOnUiThread(new Runnable() {


                @Override
                public void run() {
                   // System.out.println("纬度"+aMapLocation.getLatitude() + "+++++经度" + aMapLocation.getLongitude());
                    latitude = aMapLocation.getLatitude()+"";

                    longitude = aMapLocation.getLongitude()+"";
                    System.out.println("纬度"+latitude + "+++++经度" +longitude);
                    locationClient.stopLocation();
                }
            });

        }
    };
}
