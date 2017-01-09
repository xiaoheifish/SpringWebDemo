package Vo;

public class MessageVo {
	private String token;
	private String uuid;
	private String nickname;
	private String sex;
	private String partner;
    public void setToken(String token) {
        this.token = token;
    }

	public String getToken() {
		return token;
	}
	
	public void setUUid(String uuid){
		this.uuid = uuid;
	}
	
	public String getUUid(){
		return uuid;
	}
	
	public void setNickname(String nickname) {
	    this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setPartner(String partner){
		this.partner = partner;
	}

	public String getPartner() {
		return partner;
	}
}
