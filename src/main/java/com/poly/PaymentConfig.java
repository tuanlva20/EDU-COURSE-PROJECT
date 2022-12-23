package com.poly;

import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PaymentConfig {
    public static final String IPDEFAULT="0:0:0:0:0:0:0:1";
    public static final String Version="2.1.0";
    public static final String Command="2.1.0";
    public static final String orderType="190000";
    public static final String TmnCode="TVJLUV6B";
    public static final String CurrCode="VND";
    public static final String LocaleDefault="vn";
    public static final String ReturnUrl="http://localhost:8080/order/detail";
    public static final String vnp_HashSecret="UFUZFYHWAQXVSIVUFPTXZSFGCEJYMLPQ";
    public static final String PayUrl="https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String ReturnPayment="http://localhost:8080/order/detail";
    
    public static String hmacSHA512(final String key, final String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }
    
}
