# -*- coding:utf-8 -*-
from moderation_sdk.moderation_video import moderation_video_aksk
from moderation_sdk.utils import init_global_env

if __name__ == '__main__':
    # Services currently support North China-Beijing(cn-north-4), Asia Pacific-Hong Kong(ap-southeast-1)
    init_global_env('cn-north-4')

    #
    # access asr, long_sentence，post data by ak,sk
    #
    app_key = '*************'
    app_secret = '************'

    demo_data_url = 'https://sdk-obs-source-save.obs.cn-north-4.myhuaweicloud.com/video_moderation.mp4'

    # call interface use the url
    result = moderation_video_aksk(app_key, app_secret, demo_data_url, 1, ['porn', 'politics', 'terrorism'])
    print(result)
