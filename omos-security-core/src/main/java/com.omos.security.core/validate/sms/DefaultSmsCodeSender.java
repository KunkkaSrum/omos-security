/**
 *
 */
package com.omos.security.core.validate.sms;

import java.util.Date;

/**
 * @author zhailiang
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    private static final String SMS_URL = "https://openapi.miaodiyun.com/distributor/sendSMS";

    /* (non-Javadoc)
     * @see com.imooc.security.core.validate.code.sms.SmsCodeSender#send(java.lang.String, java.lang.String)
     */
    @Override
    public void send(String mobile, String code) {
        long timestemp = new Date().getTime();
//		System.out.println("向手机"+mobile+"发送短信验证码"+code);
        StringBuffer param = new StringBuffer();
        param.append("accountSid=61b2c1de72c481825b85fcd25adab7fb&sig=")
                .append(MD5Util.md5Encrypt32Lower("61b2c1de72c481825b85fcd25adab7fb" + "8d5662c888499eb66b6bd0ff3dea228d" + timestemp))
                .append("&timestamp=").append(timestemp)
                .append("&templateid=").append(code)
                .append("&to=").append(mobile);

        String result = HttpClient.doPost(SMS_URL, String.valueOf(param));
        System.out.println(result);
    }

}
