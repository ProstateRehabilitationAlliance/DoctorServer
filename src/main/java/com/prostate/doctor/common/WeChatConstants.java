package com.prostate.doctor.common;

public class WeChatConstants {
    /**
     * 公众号AppId
     */
    public static final String APP_ID = "wx081244d52d700819";

    /**
     * 公众号AppSecret
     */
    public static final String APP_SECRET = "2eb07faaf608215e7091e594a5b650a7";

    /**
     * 微信支付商户号
     */
    public static final String MCH_ID = "1492913112";

    /**
     * 微信支付API秘钥
     */
    public static final String KEY = "007c1defea634528be6f05a85acfe7b5";

    /**
     * 微信交易类型:公众号支付
     */
    public static final String TRADE_TYPE_JSAPI = "JSAPI";

    /**
     * WEB
     */
    public static final String WEB = "WEB";

    /**
     * 返回成功字符串
     */
    public static final String RETURN_SUCCESS = "SUCCESS";

    /**
     * 支付地址(包涵回调地址)
     */
    public static final String PAY_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx081244d52d700819&redirect_uri=http%3a%2f%2fwww.yilaiyiwang.com%2fFDC%2fm%2fweChat%2funifiedOrder&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

    public static final String RETURN_URL = "http://www.yilaiyiwang.com/FDC/m/weChat/unifiedOrder";
    /**
     * 微信统一下单url
     */
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 微信申请退款url
     */
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    /**
     * 微信企业付款到零钱url
     */
    public static final String TRANSFERS_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    /**
     * 微信企业付款到银行卡url
     */
    public static final String PAY_BANK_URL = "https://api.mch.weixin.qq.com/mmpaysptrans/pay_bank";

    /**
     * 微信支付通知url
     */
    public static final String NOTIFY_URL = "http://www.yilaiyiwang.com/FDC/w/0.0.1-SNAPSHOT/weChat/consultation/weChatPaySuccess";

    /**
     * 证书位置
     */
    public static final String CERT_PATH = "src/test/cert/apiclient_cert.p12";

    /**
     * 通过code获取授权access_token的URL
     */
    public static String Authtoken_URL(String code) {
        StringBuffer url = new StringBuffer();
        url.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
        url.append(WeChatConstants.APP_ID);
        url.append("&secret=");
        url.append(WeChatConstants.APP_SECRET);
        url.append("&code=");
        url.append(code);
        url.append("&grant_type=authorization_code");
        return url.toString();
    }
}




















