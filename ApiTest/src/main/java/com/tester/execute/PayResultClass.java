package com.tester.execute;

import com.tester.config.TestConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.IOException;

public class PayResultClass {

    public static void payResultClass(String param) throws IOException {

        //创建post请求用以访问接口
        HttpPost post = new HttpPost(TestConfig.payResultUrl);
        System.out.println("url ="+TestConfig.payResultUrl);

        //设置header信息
        post = SetHeader.setHeader(post);
        post.setHeader("Authorization",TestConfig.token);

        StringEntity entity = new StringEntity(param,"utf-8");
        post.setEntity(entity);

        //返回结果
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("result ="+result);

        //断言
        JSONObject jsonObject = new JSONObject(result);
        Boolean resultValue = jsonObject.getBoolean("result");
        Assert.assertTrue(resultValue);
    }
}
