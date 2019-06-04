<?php
/**
 * 文本检测服务token 方式请求的示例
 */
require "./moderation_sdk/gettoken.php";
require "./moderation_sdk/moderation_text.php";
require "./moderation_sdk/utils.php";

// region目前支持华北-北京一(cn-north-1)、华北-北京四(cn-north-4)、亚太-香港(ap-southeast-1)
init_region($region = 'cn-north-1');

$username = "********";      // 配置用户名
$password = "********";      // 密码
$domainName = "*********";   // 配置用户名

$categories = array(
    array(
        "text" => "666666luo聊请+110亚砷酸钾六位qq，fuck666666666666666",
        "type" => "content"
    )
);

$items = array("ad", "politics", "politics", "politics", "contraband", "contraband");

$token = get_token($username, $password, $domainName);

$result = moderation_text($token, $categories, $items);
echo $result;

