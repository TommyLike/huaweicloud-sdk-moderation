package com.huawei.moderation;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.huawei.moderation.utils.AccessService;
import com.huawei.moderation.utils.HttpClientUtils;

import okhttp3.Call;
import okhttp3.Callback;

public class ModerationDistortionCorrect {
    private AccessService service;

    public ModerationDistortionCorrect(AccessService service) {
        this.service = service;
    }

    public void moderationDistortionCorrectDemo(Bitmap bit, Callback callback) throws Exception {
        // 构建访问需要的参数
        String uri = "/v1.0/moderation/image/distortion-correct";
        String fileBase64Str = HttpClientUtils.BitmapStrByBase64(bit);

        // api请求参数说明可参考：https://support.huaweicloud.com/api-moderation/moderation_03_0014.html
        JSONObject json = new JSONObject();
        json.put("image", fileBase64Str); //检测图片
        json.put("correction", true); //判断是否进行扭曲矫正

        // 传入清晰度检测服务对应的uri参数, 传入清晰度检测服务需要的参数，
        Call call = service.post(uri, json.toJSONString());
        call.enqueue(callback);
    }

    public void ModerationDistortionCorrectDemo(String url, Callback callback) throws Exception {
        // 2.构建访问图像内容检测服务需要的参数
        String uri = "/v1.0/moderation/image/distortion-correct";
        // api请求参数说明可参考：https://support.huaweicloud.com/api-moderation/moderation_03_0014.html
        JSONObject json = new JSONObject();
        json.put("url", url); //检测图片
        json.put("correction", true); //判断是否进行扭曲矫正

        // 3.传入清晰度检测服务对应的uri参数, 传入清晰度检测服务需要的参数，
        // 该参数主要通过JSON对象的方式传入, 使用POST方法调用服务
        Call call = service.post(uri, json.toJSONString());
        call.enqueue(callback);

    }
}
