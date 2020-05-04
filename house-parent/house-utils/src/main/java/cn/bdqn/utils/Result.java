package cn.bdqn.utils;


/**
 * 统一返回结果
 * @param <T>
 */
public class Result<T> {

    //返回结果
    private T data;

    //提示消息
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
