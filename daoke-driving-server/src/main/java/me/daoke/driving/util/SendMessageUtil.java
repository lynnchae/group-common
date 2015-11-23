package me.daoke.driving.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import me.daoke.driving.common.model.CommonJsonResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: chenlong
 * Date: 2015/5/12
 * Time: 9:44
 */
public class SendMessageUtil {


    private static JPushClient jpush = new JPushClient(ConstantsUtil.JPush.MASTER_SECRET, ConstantsUtil.JPush.APP_KEY);


    /**
     *  推送消息到手机端
     * @param accountID
     * @param alert
     * @param title
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    public static void sendToMobile(String accountID,String alert,String title) throws APIConnectionException, APIRequestException {
        PushPayload payload =     buildPushObject_android_and_ios(accountID,alert,title);
        PushResult pushResult =jpush.sendPush(payload);
    }



    public static PushPayload buildPushObject_android_and_ios(String accountID,String alert,String title) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(accountID))
                .setNotification(Notification.newBuilder()
                        .setAlert(alert)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(title).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1).build())
                        .build())
                .build();
    }

    /**
     * 推送消息到硬件终端
     * @param sendPersonalWeibo
     * @param callbackUrl
     * @param recordID
     * @param accountID
     * @return
     * @throws IOException
     */
    public static String sendToTerminal(String sendPersonalWeibo,String callbackUrl,Integer recordID,String accountID) throws IOException {
        Map<String, String> paramWeibo = new HashMap<String, String>();
        paramWeibo.put("appKey", ConstantsUtil.Sign.APP_KEY);
        paramWeibo.put("secret",  ConstantsUtil.Sign.SECRET);
        paramWeibo.put("multimediaURL", "");
        paramWeibo.put("senderAccountID","");
        paramWeibo.put("receiverAccountID",accountID);
        paramWeibo.put("level","18");
        paramWeibo.put("interval", "90");
        StringBuilder callbackBuf = new StringBuilder();
        callbackBuf.append(callbackUrl)
                //  .append("?messageID=").append(messageID)
                .append("?recordID=").append(recordID).append("&accountID=").append(accountID);

        paramWeibo.put("callbackURL", callbackUrl);
        String signWeibo = SHASignature.sign(ParameterUtil.getSignData(paramWeibo));
        paramWeibo.put("sign", signWeibo);

        HttpRequester requesterWeibo = new HttpRequester();
        requesterWeibo.setContentType("multipart/form-data;boundary=--abc");
        HttpRespons responsWeibo = requesterWeibo.sendPost(sendPersonalWeibo, paramWeibo);
        String bizid = "";
        if (responsWeibo.getCode() == ConstantsUtil.HttpStatusCode.OK) {
            String content = responsWeibo.getContent();
            CommonJsonResult jsonResult2 = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            if (jsonResult2.getERRORCODE().equals(ConstantsUtil.DaoKeStatusCode.OK)) {
                Map<String, String> resParam = (Map<String, String>) jsonResult2.getRESULT();
                bizid = resParam.get("bizid");
            }
        }

        return  bizid;

    }
}
