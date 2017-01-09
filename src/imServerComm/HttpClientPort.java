package imServerComm;

import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.json.JSONException;
import dataBase.DBDemo;
public class HttpClientPort {
	
	public static void main(String arg[])throws Exception{
		
		//UUID uuid = UUID.randomUUID();
		//String newuuid = uuid.toString();
		//System.out.println(createId(newuuid.substring(10),"5689"));
		//String subuuid = DBDemo.queryUUid("shaoyizhe1");
		//String token = HttpClientPort.updateToken(subuuid);
		//System.out.println(token);
	}
   
    public static String createId(String id, String name) throws Exception{
    	HttpClient httpClient = HttpClientBuilder.create().build();
        String url = "https://api.netease.im/nimserver/user/create.action";
        HttpPost httpPost = new HttpPost(url);
        String appKey = "645e1db844114f5153150902cbecf44a";
        String appSecret = "74819ba617d2";
        String nonce =  "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码
        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accid", id));
        nvps.add(new BasicNameValuePair("name",name));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        
        String strResult = EntityUtils.toString(response.getEntity()); 
        JSONObject jsonObject = getJSON(strResult); 
        String data=jsonObject.getString("info");  
        JSONObject jToken =getJSON(data);  
        String strToken = jToken.getString("token");
        return strToken;
        //System.out.println(strToken);
        // 打印执行结果
        //System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
    }
 
    public static void updateId(String id) throws Exception{
        HttpClient httpClient = HttpClientBuilder.create().build();
        String url = "https://api.netease.im/nimserver/user/update.action";
        HttpPost httpPost = new HttpPost(url);

        String appKey = "645e1db844114f5153150902cbecf44a";
        String appSecret = "74819ba617d2";
        String nonce =  "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accid", id));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        // 打印执行结果
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));

    }
    public static JSONObject getJSON(String sb) throws JSONException {    
    	JSONObject res = JSONObject.fromObject(sb);
    	return res;   
    } 
    public static String updateToken(String id) throws Exception{
        HttpClient httpClient = HttpClientBuilder.create().build();
        String url = "https://api.netease.im/nimserver/user/refreshToken.action";
        HttpPost httpPost = new HttpPost(url);

        String appKey = "645e1db844114f5153150902cbecf44a";
        String appSecret = "74819ba617d2";
        String nonce =  "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accid", id));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        
        String strResult = EntityUtils.toString(response.getEntity()); 
        JSONObject jsonObject = getJSON(strResult); 
        String data=jsonObject.getString("info");  
        JSONObject jToken =getJSON(data);  
        String strToken = jToken.getString("token");
        return strToken;
        // 打印执行结果
        //System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        //System.out.println(jToken.getString("token"));
    }
}