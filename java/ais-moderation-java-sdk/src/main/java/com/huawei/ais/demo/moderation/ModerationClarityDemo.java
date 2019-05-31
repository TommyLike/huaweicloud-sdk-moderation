package com.huawei.ais.demo.moderation;

import com.alibaba.fastjson.JSONObject;
import com.huawei.ais.demo.ResponseProcessUtils;
import com.huawei.ais.demo.ServiceAccessBuilder;
import com.huawei.ais.sdk.AisAccess;
import com.huawei.ais.sdk.AisAccessWithProxy;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.io.File;
import java.io.IOException;

/**
 *  清晰度检测服务的使用示例类
 */
public class ModerationClarityDemo {
	//
	// 清晰度检测服务的使用示例函数
	//
	private static void moderationClarityDemo() throws IOException {

		// 1. 配置好访问清晰度检测服务的基本信息,生成对应的一个客户端连接对象
		AisAccess service = ServiceAccessBuilder.builder()
				.ak("######")                       // your ak
				.sk("######")                       // your sk
				.region("cn-north-1")               // 内容审核服务目前支持华北-北京(cn-north-1、cn-north-4)以及亚太-香港(ap-southeast-1)
				.connectionTimeout(5000)            // 连接目标url超时限制
				.connectionRequestTimeout(1000)     // 连接池获取可用连接超时限制
				.socketTimeout(20000)               // 获取服务器响应数据超时限制
				.build();
		
		try {
			//
			// 2.构建访问清晰度检测服务需要的参数
			//
			String uri = "/v1.0/moderation/image/clarity-detect";
			byte[] fileData = FileUtils.readFileToByteArray(new File("data/moderation-demo-1.jpg"));
			String fileBase64Str = Base64.encodeBase64String(fileData);

			JSONObject json = new JSONObject();
			json.put("image", fileBase64Str); //检测图片
			json.put("threshold", "0.8"); //判断是否清晰的阈值
			StringEntity stringEntity = new StringEntity(json.toJSONString(), "utf-8");

			// 3.传入清晰度检测服务对应的uri参数, 传入清晰度检测服务需要的参数，
			// 该参数主要通过JSON对象的方式传入, 使用POST方法调用服务
			HttpResponse response = service.post(uri, stringEntity);

			// 4.验证服务调用返回的状态是否成功，如果为200, 为成功, 否则失败。
			ResponseProcessUtils.processResponseStatus(response);

			// 5.处理服务返回的字符流，输出识别结果。
			ResponseProcessUtils.processResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 6.使用完毕，关闭服务的客户端连接			
			service.close();
		}
	}

	//
	// 主入口函数
	//
	public static void main(String[] args) throws IOException {

		// 测试入口函数
		moderationClarityDemo();
	}
}