package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Date;
import java.util.UUID;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import net.sf.json.JSONException;
public class DBDemo {
	/*
	public static void main(String[] args){
		updateTT("1234",convertCurrentTime(),"yuxiaohei");
	}*/
	/*
	public static void main(String[] args)throws JSONException{
		
		String user = "yuxiaohei";
		queryUser(user);
		updateTT("1234",convertCurrentTime(),"yuxiaohei");
		
		String json = "{'name':'reiz'}";
		JSONObject jsonObj = new JSONObject(json);
		String name = jsonObj.getString("name");
		jsonObj.put("initial", name.substring(0, 3).toUpperCase());
		String[] likes = new String[] {"JavaScript", "Skiing", "Apple Pie"};
		jsonObj.put("likes", likes); 
		System.out.println(jsonObj);
		Map <String, String> ingredients = new HashMap <String, String>();
		ingredients.put("apples", "3kg");
		ingredients.put("sugar", "1kg");
		ingredients.put("pastry", "2.4kg");
		ingredients.put("bestEaten", "outdoors");
		jsonObj.put("ingredients", ingredients);
		System.out.println(jsonObj);
		System.out.println(queryInfo("shaoyizhe1"));
		
	}
	*/
	public static JSONObject queryInfo(String name){
		DBHelper db4 = null;
		ResultSet ret4 = null;
		JSONObject info = new JSONObject();
		String sql1 = "select *from code where name = ?";
		db4 = new DBHelper(sql1);
		try{
			db4.pst.setString(1,name);
			ret4 = db4.pst.executeQuery();
			while(ret4.next()){
				info.put("uuid",ret4.getString(2));
				info.put("nickname",ret4.getString(5));
				info.put("sex",ret4.getString(6));
				if(ret4.getString(9)!=null){
					info.put("partner", ret4.getString(9));
				}
			}
			ret4.close();  
	        db4.close();//关闭连接 
		}catch (SQLException e) {  
            e.printStackTrace();  
        }  
	return info;
	}
	public static String queryUser(String newUser){
		String sql = null;
		DBHelper db1 = null;
		ResultSet ret = null;
		sql = "select *from code";
		db1 = new DBHelper(sql);
		String stateCode = "10000";
		try{
			ret = db1.pst.executeQuery();
			while(ret.next()){
				 String name = ret.getString(3);  
	             if(name!=null&&name.equals(newUser)){
	            	 stateCode = "40000";
	            	 System.out.println(stateCode);
	            	 break;
	             }
	        }
			ret.close();  
	        db1.close();//关闭连接 
		}catch (SQLException e) {  
            e.printStackTrace();  
        }  
		return stateCode;
	}
	
	public  static int insertReg(String uuid, String name, String password, String token, String timestamp){
		DBHelper db2 = null;
		int i = 0;
		String sql1 = "insert into `code`(`uuid`,`name`,`password`,`token`,`timestamp`) values (?,?,?,?,?)";
		db2 = new DBHelper(sql1);
		try{
			db2.pst.setString(1, uuid);
			db2.pst.setString(2, name);
			db2.pst.setString(3, password);
			db2.pst.setString(4, token);
			db2.pst.setString(5, timestamp);
			i = db2.pst.executeUpdate();
			
		}catch (SQLException e) {  
            e.printStackTrace();  
        }  
	return i;
	}
	public static int updateTT(String token, String time, String name){
		DBHelper db2 = null;
		int i = 0;
		String sq2 = "update `code` set `token` = ?,`timestamp` = ? where `name` = ?";
		db2 = new DBHelper(sq2);
		try{
			db2.pst.setString(1, token);
			db2.pst.setString(2, time);
			db2.pst.setString(3,name);
			i = db2.pst.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
	public static int update(String item, String value, String uuid){
		DBHelper db3 = null;
		int i =0;
		String sq3 = "update `code` set " + item + " = '" + value + "' where `uuid`= '" + uuid + "'";
		db3 = new DBHelper(sq3);
		try{
			//db3.pst.setString(1,item);  
	        //db3.pst.setString(2,value);//或者：preStmt.setInt(1,值);  
	        //db3.pst.setString(3,name);  
	        i=db3.pst.executeUpdate();  
		}catch (SQLException e){
			e.printStackTrace();
		}
	return i;
	}
	public static String queryTime(String name){
		DBHelper db4 = null;
		ResultSet ret4 = null;
		String time = "";
		String sql1 = "select *from code where name = ?";
		db4 = new DBHelper(sql1);
		try{
			db4.pst.setString(1,name);
			ret4 = db4.pst.executeQuery();
			while(ret4.next()){
				time = ret4.getString(8);
			}
			ret4.close();  
	        db4.close();//关闭连接 
		}catch (SQLException e) {  
            e.printStackTrace();  
        }  
	return time;
	}
	public  static String queryTimeNew(String uuid){
		DBHelper db4 = null;
		ResultSet ret4 = null;
		String time = "";
		String sql1 = "select *from code where uuid = ?";
		db4 = new DBHelper(sql1);
		try{
			db4.pst.setString(1,uuid);
			ret4 = db4.pst.executeQuery();
			while(ret4.next()){
				time = ret4.getString(8);
			}
			ret4.close();  
	        db4.close();//关闭连接 
		}catch (SQLException e) {  
            e.printStackTrace();  
        }  
	return time;
	}
	public  static String queryPassword(String name){
		DBHelper db4 = null;
		ResultSet ret4 = null;
		String password = "";
		String sql1 = "select *from code where name = ?";
		db4 = new DBHelper(sql1);
		try{
			db4.pst.setString(1,name);
			ret4 = db4.pst.executeQuery();
			while(ret4.next()){
				password = ret4.getString(4);
			}
			ret4.close();  
	        db4.close();//关闭连接 
		}catch (SQLException e) {  
            e.printStackTrace();  
        }  
		return password;
	}
	public static String queryUUid(String name){
		DBHelper db5 = null;
		ResultSet ret5 = null;
		String uuid = "";
		String sql5 = "select *from code where name = ?";
		db5 = new DBHelper(sql5);
		try{
			db5.pst.setString(1, name);
			ret5 = db5.pst.executeQuery();
			while(ret5.next()){
				uuid = ret5.getString(2);
			}
			ret5.close();
			db5.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return uuid;
	}
	public static String convertCurrentTime(){
		 Date date = new Date();//获得系统时间.
		 Timestamp nousedate = new Timestamp(date.getTime());
		 return nousedate.toString();    
	}
	public static long computeTimeDiff(String beginTime, String endTime) {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		long between = 0;
		try{
			Date begin = dfs.parse(beginTime);
			Date end = dfs.parse(endTime);
			between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
		}catch (Exception ex) {
            ex.printStackTrace();
        }
		return between;  
	}
	public static boolean tokenValid(String name){
		if(computeTimeDiff(queryTime(name), convertCurrentTime())<2 * 60 * 60 * 1000L){
			return true;
		}
		else
			return false;
	}
	public static boolean tokenValidNew(String uuid){
		if(computeTimeDiff(queryTimeNew(uuid), convertCurrentTime())<2 * 60 * 60 * 1000L){
			return true;
		}
		else
			return false;
	}
    public static String MD5Encode(String origin) {  
        String resultString = null;  
        try {  
            resultString = new String(origin);  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));  
        } catch (Exception ex) {  
        }  
        return resultString;  
    }
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };  
	public static String byteArrayToHexString(byte[] b) {  
        StringBuffer resultSb = new StringBuffer();  
        for (int i = 0; i < b.length; i++) {  
            resultSb.append(byteToHexString(b[i]));  
        }  
        return resultSb.toString();  
    } 
	private static String byteToHexString(byte b) {  
	        int n = b;  
	        if (n < 0)  
	            n = 256 + n;  
	        int d1 = n / 16;  
	        int d2 = n % 16;  
	        return hexDigits[d1] + hexDigits[d2];  
	}       
}

