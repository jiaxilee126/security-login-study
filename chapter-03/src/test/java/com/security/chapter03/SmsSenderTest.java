package com.security.chapter03;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;
import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName SmsSenderTest
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/24 16:30
 */
public class SmsSenderTest extends Chapter03ApplicationTests {
    int appid = 1400203496; // 1400开头

    // 短信应用 SDK AppKey
    String appkey = "ec3fab15747b760a17cfdbac8438873c";

    // 需要发送短信的手机号码
    String[] phoneNumbers = {"15920294465", "13168923723","15999586003"};
    //String[] phoneNumbers = {"15999586003"};
    // 短信模板 ID，需要在短信应用中申请
    int templateId = 319260; // NOTE: 这里的模板 ID`7839`只是一个示例，真实的模板 ID 需要在短信控制台中申请

    String smsSign = "我最好";
    @Test
    public void smsMsg() {
        // 短信应用 SDK AppID
        int appid = 1400203496; // 1400开头

        // 短信应用 SDK AppKey
        String appkey = "ec3fab15747b760a17cfdbac8438873c";

        // 需要发送短信的手机号码
        String[] phoneNumbers = {"15920294465", "13168923723"};
        //String[] phoneNumbers = {"15999586003"};
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 319260; // NOTE: 这里的模板 ID`7839`只是一个示例，真实的模板 ID 需要在短信控制台中申请
        // templateId7839对应的内容是"您的验证码是: {1}"
        // 签名
        //String smsSign = "开源博客";


        try {
            String[] params = {"5678","abcd"};// 数组具体的元素个数和模板中变量个数必须一致，例如示例中 templateId:5678 对应一个变量，参数数组中元素个数也必须是一个
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, "", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            //SmsSingleSenderResult result = ssender.send(0, "86", phoneNumbers,
             //       "今天大跌"+"为您的登录验证码，请于"+100+"分钟内填写。如非本人操作，请忽略本短信！", "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
    }


    @Test
    public void whenSend2Many() {

        try {
            String[] params = {"今天大涨","500"};// 数组具体的元素个数和模板中变量个数必须一致，例如示例中 templateId:5678 对应一个变量，参数数组中元素个数也必须是一个
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
                    templateId, params, "", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
    }
}
