package message;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseMessage {
	private int code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public ResponseMessage(){
    }

    public ResponseMessage(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseMessage wrapper(Object data) {
        this.data = data;
        return this;
    }

    public static ResponseMessage SUCCESS() {
        return new ResponseMessage(200, "success", "");
    }
}
