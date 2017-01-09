package springWebDemo.controller;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import net.sf.json.JSONObject;
import net.sf.json.JSONException;
import dataBase.DBDemo;
import dataBase.DBHelper;
import imServerComm.HttpClientPort;
import Vo.AuthorityVo;
import Vo.TokenVo;
import Vo.MessageVo;
import message.ResponseMessage;
@Controller
public class GoController {
	private final Log logger = LogFactory.getLog(GoController.class);
	@RequestMapping(value={"/"},method={RequestMethod.HEAD})
	public String head(){
		return "go.jsp";
	}
	@RequestMapping(value={"/index","/"},method={RequestMethod.GET})
	@ResponseBody
	public ResponseMessage index(Model model) throws Exception{
		logger.info("=========processed by index=========");
		model.addAttribute("msg","GO GO GO!");
		String name = "456";
		//String password = "456";
		//String name = vo.getName();
		//String password = vo.getPassword();
		int i;
		//UUID uuid = UUID.randomUUID();
		//String newuuid = uuid.toString();
		//String subuuid = newuuid.substring(10);
		JSONObject jsonObj = new JSONObject();
		//String token = HttpClientPort.createId(subuuid,name);
		//i = DBDemo.insertReg(subuuid,name,DBDemo.MD5Encode(password),token,DBDemo.convertCurrentTime());
		jsonObj = DBDemo.queryInfo(name);
		jsonObj.put("token", "yufajfl");
		return ResponseMessage.SUCCESS().wrapper(jsonObj);	
		/*
		else
			return "logger.jsp";
		*/		
	}
	
	@RequestMapping(value={"/index","/reg"},method={RequestMethod.POST})
	@ResponseBody
	public ResponseMessage userRegister(@RequestBody AuthorityVo vo)throws Exception{
		String name = vo.getName();
		String password = vo.getPassword();
		int i;
		UUID uuid = UUID.randomUUID();
		String newuuid = uuid.toString();
		String subuuid = newuuid.substring(10);
		String token = HttpClientPort.createId(subuuid,name);
		JSONObject jsonObj = new JSONObject();
		if (DBDemo.queryUser(name)=="10000"){
			i = DBDemo.insertReg(subuuid,name,DBDemo.MD5Encode(password),token,DBDemo.convertCurrentTime());
			jsonObj.put("token", token);
			jsonObj.put("uuid", subuuid);
			return ResponseMessage.SUCCESS().wrapper(jsonObj);
		}
		else
			return new ResponseMessage(201,"error","");
	}
	@RequestMapping(value={"/index","/login"},method={RequestMethod.POST})
	@ResponseBody
	public ResponseMessage userLogin(@RequestBody AuthorityVo vo)throws Exception{
		System.out.println("Login request");
		String name = vo.getName();
		String password = vo.getPassword();
		System.out.println(name);
		System.out.println(password);
		String subuuid ="";
		String token = "";
		JSONObject jsonObj = new JSONObject();
		if(DBDemo.queryPassword(name).equals(DBDemo.MD5Encode(password))){
			subuuid = DBDemo.queryUUid(name);
			token = HttpClientPort.updateToken(subuuid);
			DBDemo.updateTT(token, DBDemo.convertCurrentTime(), name);
			jsonObj = DBDemo.queryInfo(name);
			jsonObj.put("token",token);
			System.out.println(jsonObj.toString());
			return ResponseMessage.SUCCESS().wrapper(jsonObj);
		}
		else
			return new ResponseMessage(201,"error","");
	}
	
	@RequestMapping(value={"/index","/updatetoken"},method={RequestMethod.POST})
	@ResponseBody
	public ResponseMessage updateToken(@RequestBody TokenVo tokenvo)throws Exception{
		String currenttoken = tokenvo.getToken();
		String uuid = tokenvo.getUUid();
		JSONObject jsonObj = new JSONObject();
		if(DBDemo.tokenValidNew(uuid)==false){
			String token = HttpClientPort.updateToken(uuid);	
			DBDemo.updateTT(token, DBDemo.convertCurrentTime(), currenttoken);
			jsonObj.put("token", token);
		}
		else
			jsonObj.put("token",currenttoken);
		return ResponseMessage.SUCCESS().wrapper(jsonObj);
	}
	@RequestMapping(value={"/index","/updateinfo"},method={RequestMethod.POST})
	@ResponseBody
	public ResponseMessage updateInfo(@RequestBody MessageVo messagevo)throws Exception{
		String currenttoken = messagevo.getToken();
		String uuid = messagevo.getUUid();
		String nickname = messagevo.getNickname();
		String sex = messagevo.getSex();
		String partner = messagevo.getPartner();
		JSONObject jsonObj = new JSONObject();
		if(sex!=null){
			DBDemo.update("sex", sex, uuid);
		}
		if(nickname!=null){
			DBDemo.update("nickname",nickname,uuid);
		}
		if(partner!=null){
			DBDemo.update("partner",partner,uuid);
		}
		if(DBDemo.tokenValidNew(uuid)==false){
			String token = HttpClientPort.updateToken(uuid);	
			DBDemo.updateTT(token, DBDemo.convertCurrentTime(), currenttoken);
			jsonObj.put("token", token);
		}
		else
			jsonObj.put("token",currenttoken);
		return ResponseMessage.SUCCESS().wrapper(jsonObj);
	}
	/*
	@RequestMapping(value={"/index","/updateinfo"},method={RequestMethod.POST})
	@ResponseBody
	public ResponseMessage updateToken(@RequestBody AuthorityVo vo)throws Exception{
		String name = vo.getName();
		String password = vo.getPassword();
		JSONObject jsonObj = new JSONObject();
		if(DBDemo.tokenValid(name)==false){
			String subuuid = DBDemo.queryUUid(name);
			String token = HttpClientPort.updateToken(subuuid);	
			DBDemo.updateTT(token, DBDemo.convertCurrentTime(), name);
			jsonObj.put("token", token);
			return ResponseMessage.SUCCESS().wrapper(jsonObj);
		}
		else
			return new ResponseMessage(201,"error","");
	}
	*/
}
/*
public String userReg(String name, String password){
	int i;
	UUID uuid = UUID.randomUUID();
	String newuuid = uuid.toString();
	String subuuid = newuuid.substring(10);
	String token = HttpClientPort.createId(subuuid,name);
	if (DBDemo.queryUser(name)=="10000"){
		i = DBDemo.insertReg(subuuid,name,DBDemo.MD5Encode(password),token,DBDemo.convertCurrentTime());
	}
}
*/
/*
public String userLog(String name, String password){
	String subuuid ="";
	String token = "";
	if(DBDemo.queryPassword(name).equals(DBDemo.MD5Encode(password))){
		subuuid = DBDemo.queryUUid(name);
		token = HttpClientPort.updateToken(subuuid);
		DBDemo.updateTT(token, DBDemo.convertCurrentTime(), name);
	};
}
*/
/*
public String examineToken(String name) throws Exception{
	if(DBDemo.tokenValid(name)==false){
		String subuuid = DBDemo.queryUUid(name);
		String token = HttpClientPort.updateToken(subuuid);	
		DBDemo.updateTT(token, DBDemo.convertCurrentTime(), name);
		return "go.jsp";
	}
	else
		return "logger.jsp";
	
}
*/
/*
public String updateInfo(String item, String value, String name){
	DBDemo.update(item, value, name);
}*/