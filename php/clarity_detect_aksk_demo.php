<?php
/**
 * 图片清晰度检测的服务ak,sk 方式请求的使用示例
 */
require "./moderation_sdk/clarity_detect.php";
require "./moderation_sdk/utils.php";

// region目前支持华北-北京一(cn-north-1)、华北-北京四(cn-north-4)、亚太-香港(ap-southeast-1)
init_region($region = 'cn-north-1');

$app_key = "*************";
$app_secret = "*************";

$filepath = "./data/moderation-clarity-detect.jpg";
$data = file_to_base64($filepath);

$data_url = "https://ais-sample-data.obs.cn-north-1.myhuaweicloud.com/vat-invoice.jpg";

// base64 方式请求
$result = clarity_detect_aksk($app_key, $app_secret, $data, "", 0.8);
echo $result;


// obs的url方式请求
$result = clarity_detect_aksk($app_key, $app_secret, "", $data_url, 0.8);
echo $result;
