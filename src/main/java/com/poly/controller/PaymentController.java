package com.poly.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.PaymentConfig;
import com.poly.DTO.PaymentResult;
import com.poly.bean.Payment;

@RestController
@CrossOrigin("*")
public class PaymentController {
    PaymentConfig pConfig;
    @PostMapping(value = "/create-payment/{id}")
    public ResponseEntity<?> createPayment(@RequestBody Payment req, @PathVariable("id")String orderId) throws IOException{
        Integer amount = req.getAmount() * 100;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", pConfig.Version);
        vnp_Params.put("vnp_Command", pConfig.Command);
        vnp_Params.put("vnp_TmnCode", pConfig.TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", pConfig.CurrCode);
        vnp_Params.put("vnp_BankCode", req.getBankcode());
        vnp_Params.put("vnp_TxnRef", String.valueOf(req.getIdService()));
        vnp_Params.put("vnp_OrderInfo", req.getDescription());
        vnp_Params.put("vnp_OrderType", pConfig.orderType);
        vnp_Params.put("vnp_Locale", pConfig.LocaleDefault);
        vnp_Params.put("vnp_ReturnUrl", pConfig.ReturnUrl);
        vnp_Params.put("vnp_IpAddr", pConfig.IPDEFAULT);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        //Add Params of 2.1.0 Version
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        
        //Billing
        // vnp_Params.put("vnp_Bill_Mobile", req.getParameter("txt_billing_mobile"));
        // vnp_Params.put("vnp_Bill_Email", req.getParameter("txt_billing_email"));
        // String fullName = (req.getParameter("txt_billing_fullname")).trim();
        // if (fullName != null && !fullName.isEmpty()) {
        //     int idx = fullName.indexOf(' ');
        //     String firstName = fullName.substring(0, idx);
        //     String lastName = fullName.substring(fullName.lastIndexOf(' ') + 1);
        //     vnp_Params.put("vnp_Bill_FirstName", firstName);
        //     vnp_Params.put("vnp_Bill_LastName", lastName);

        // }
        // vnp_Params.put("vnp_Bill_Address", req.getParameter("txt_inv_addr1"));
        // vnp_Params.put("vnp_Bill_City", req.getParameter("txt_bill_city"));
        // vnp_Params.put("vnp_Bill_Country", req.getParameter("txt_bill_country"));
        // if (req.getParameter("txt_bill_state") != null && !req.getParameter("txt_bill_state").isEmpty()) {
        //     vnp_Params.put("vnp_Bill_State", req.getParameter("txt_bill_state"));
        // }
        // // Invoice
        // vnp_Params.put("vnp_Inv_Phone", req.getParameter("txt_inv_mobile"));
        // vnp_Params.put("vnp_Inv_Email", req.getParameter("txt_inv_email"));
        // vnp_Params.put("vnp_Inv_Customer", req.getParameter("txt_inv_customer"));
        // vnp_Params.put("vnp_Inv_Address", req.getParameter("txt_inv_addr1"));
        // vnp_Params.put("vnp_Inv_Company", req.getParameter("txt_inv_company"));
        // vnp_Params.put("vnp_Inv_Taxcode", req.getParameter("txt_inv_taxcode"));
        // vnp_Params.put("vnp_Inv_Type", req.getParameter("cbo_inv_type"));

        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = pConfig.hmacSHA512(pConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = pConfig.PayUrl + "?" + queryUrl;

        PaymentResult result=new PaymentResult();
        result.setStatus("00");
        result.setMessage("success");
        result.setUrl(paymentUrl);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
