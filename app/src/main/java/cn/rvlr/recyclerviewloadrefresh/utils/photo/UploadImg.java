package cn.rvlr.recyclerviewloadrefresh.utils.photo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.rvlr.recyclerviewloadrefresh.R;
import cn.rvlr.recyclerviewloadrefresh.data.callback.HttpCallback;
import cn.rvlr.recyclerviewloadrefresh.data.constants.Constant;
import cn.rvlr.recyclerviewloadrefresh.data.constants.HttpResult;
import cn.rvlr.recyclerviewloadrefresh.data.http.HpptApi;
import cn.rvlr.recyclerviewloadrefresh.utils.ToastUtil;
import id.zelory.compressor.Compressor;
import id.zelory.compressor.FileUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 图片上传工具类,,
 * 要引用包//添加图片压缩工具
 compile 'id.zelory:compressor:1.0.2'
 */
public class UploadImg {


/*    String imgStr[] = new String[]{"", ""};

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                Img img = (Img) msg.obj;
                if ("5".equals(img.getType())) {
                    binding.accomplishIV1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    Glide.with(AccomplishActivity.this).load(img.getPicPath()).centerCrop().error(R.drawable.actionbar_camera_icon).into(binding.accomplishIV1);

                    imgStr[0] = img.getSrc();
                } else if ("6".equals(img.getType())) {
                    binding.accomplishIV2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    Glide.with(AccomplishActivity.this).load(img.getPicPath()).centerCrop().error(R.drawable.actionbar_camera_icon).into(binding.accomplishIV2);
                    imgStr[1] = img.getSrc();
                }
            }
        }
    };*/


    private Activity mActivity;
    private Handler mHandler;
    /**
     * @param activity 传入上下文
     * @param handler  在上传完相片时回传值，返回Img对象
     */
    public UploadImg(Activity activity, Handler handler) {
        this.mActivity = activity;
        this.mHandler = handler;
    }

    private Uri photoUri = null;
    private String type;//1、2、3、5、6默认裁剪，其他为自定义裁剪，，传入的类型在上传完数据会返回
    /**
     * 拍照获取图片
     * @param type 1、2、3、5、6默认裁剪，其他为自定义裁剪，，传入的类型在上传完数据会返回
     */
    public void takePhoto(String type) {
        this.type = type;
        try {
            //android.permission.CAMERA 照相的权限
            //  !android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)
            //        if (!checkPermission(Manifest.permission.CAMERA)) {
//            ToastUtil.show("请检查相册权限是否打开");
//            return;//先查看是否有权限
//        }
            // 执行拍照前，应该先判断SD卡是否存在
            String SDState = Environment.getExternalStorageState();
            if (SDState.equals(Environment.MEDIA_MOUNTED)) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                /***
                 * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的
                 * 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
                 * 如果不使用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
                 */
                ContentValues values = new ContentValues();
                photoUri = mActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                mActivity.startActivityForResult(intent, Constant.SELECT_PIC_BY_TACK_PHOTO);
            } else {
                ToastUtil.show("内存卡不存在");
            }
        } catch (Exception e) {
            ToastUtil.show("打开相机失败，请检查当前应用权限");
        }
    }

    /***
     * 从相册中取图片
     * @param type 1、2、3、5、6默认裁剪，其他为自定义裁剪，，传入的类型在上传完数据会返回
     */
    public void pickPhoto(String type) {
        this.type = type;
        try {
            //android.permission.CAMERA 照相的权限
//        if (!checkPermission(Manifest.permission.CAMERA)) {
//            ToastUtil.show("请检查相册权限是否打开");
//            return;//先查看是否有权限
//        }
            Intent intent;
            if (Build.VERSION.SDK_INT < 19) {
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

            } else {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            }
            mActivity.startActivityForResult(intent, Constant.SELECT_PIC_BY_PICK_PHOTO);

//        Intent i = new Intent(
//                Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        //i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
////        Intent intent = new Intent();
////        // 如果要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
//      //  i.setType("image/*");
//        //i.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(i, SELECT_PIC_BY_PICK_PHOTO);
        } catch (Exception e) {
            ToastUtil.show("打开相册失败，请检查当前应用权限");
        }
    }

    private File actualImage;

    public void onActivityResult(int requestCode, int resultCode, Intent data, int RESULT_OK) {
        try {
            if (resultCode==0){
                return;//点击取消
            }
            if (requestCode == Constant.SELECT_PIC_BY_PICK_PHOTO && resultCode == RESULT_OK) {

                if (data == null) {
                    ToastUtil.show("当前图片地址有误,请重新选择!");
                    return;
                }

                actualImage = FileUtil.from(mActivity, data.getData());
                if (type.equals("1") || type.equals("2") || type.equals("3") || "5".equals(type) || "6".equals(type)) {
                    //默认压缩
                    compressImage();
                } else {
                    //压缩到指定大小
                    customCompressImage();
                }

            } else if (requestCode == Constant.SELECT_PIC_BY_TACK_PHOTO && photoUri != null) {

                actualImage = FileUtil.from(mActivity, photoUri);
                if (type.equals("1") || type.equals("2") || type.equals("3") || "5".equals(type) || "6".equals(type)) {
                    //默认压缩
                    compressImage();
                } else {
                    //压缩到指定大小
                    customCompressImage();
                }
            }
        } catch (Exception e) {
            ToastUtil.show("当前图片地址有误,请重新选择!");
            // e.printStackTrace();
        }
    }


    private File compressedImage;

    /**
     * //默认压缩图片
     */
    private void compressImage() {
        if (actualImage == null) {
            ToastUtil.show("当前图片地址有误,请重新选择!");
        } else {

            // Compress image in main thread
            //compressedImage = Compressor.getDefault(this).compressToFile(actualImage);
            //setCompressedImage();

            // Compress image to bitmap in main thread
            /*compressedImageView.setImageBitmap(Compressor.getDefault(this).compressToBitmap(actualImage));*/

            // Compress image using RxJava in background thread
            Compressor.getDefault(mActivity)
                    .compressToFileAsObservable(actualImage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<File>() {
                        @Override
                        public void call(File file) {
                            compressedImage = file;
                            //  setCompressedImage();
                            //图片压缩完成
                            puloadImage();//开始上传相片
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            ToastUtil.show(throwable.getMessage());
                        }
                    });
        }
    }

    /**
     * //自定义压缩
     */
    private void customCompressImage() {
        if (actualImage == null) {
            ToastUtil.show("当前图片地址有误,请重新选择!");
        } else {
            // Compress image in main thread using custom Compressor
            compressedImage = new Compressor.Builder(mActivity)
                    .setMaxWidth(640)
                    .setMaxHeight(480)
                    .setQuality(75)
                    .setCompressFormat(Bitmap.CompressFormat.WEBP)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .build()
                    .compressToFile(actualImage);
            // setCompressedImage();//显示图片
            //图片压缩完成
            puloadImage();//开始上传相片
        }
    }


    //开始上传图片
    private void puloadImage() {
        if (compressedImage == null) {
            ToastUtil.show("请先选择图片再上传！");
            return;
        }
        showLoadingDialog("正在上图片，请不要退出！");

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), compressedImage);

        Call call = HpptApi.getImgUploading(requestBody);
        call.enqueue(new HttpCallback<Img>() {
            @Override
            public void onFailure(Call<HttpResult> call, Throwable t) {
                dismissLoadingDialog();
                ToastUtil.show();
            }

            @Override
            protected void onSuccess(Call<HttpResult> call, Img response) {
                dismissLoadingDialog();
//                Intent intent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putString("imgurl", response.getSrc());
//                bundle.putString("TYPE", type);
//                bundle.putString("picPath", actualImage.toString());
//                intent.putExtras(bundle);
//                mActivity.setResult(1001, intent);
//                mActivity.finish();
                response.setType(type);//把传入过来的类型保存并返回
                response.setPicPath(actualImage.toString());//设置本地图片路径
                mHandler.sendMessage(mHandler.obtainMessage(100, response));//上传完成把上传图片地址返回
            }

            @Override
            protected void onError(Call<HttpResult> call, String msg) {
                dismissLoadingDialog();
                ToastUtil.show(msg);
            }
        });
    }


    private SweetAlertDialog mLoadingDialog;

    private void showLoadingDialog(String text) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(mActivity.getResources().getColor(R.color.colorPrimary));
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setTitleText(text + "...");
        }
        mLoadingDialog.show();
    }

    private void dismissLoadingDialog() {
        // Dismiss the Dialog only when the parent Activity is still alive.
        try {

            if (mLoadingDialog != null) {
                mLoadingDialog.dismiss();
            }
        } catch (Exception e) {
        }

    }
}
