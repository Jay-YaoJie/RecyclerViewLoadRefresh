package cn.rvlr.recyclerviewloadrefresh.server.constants;

/**
 * 默认约定返回 格式 ： {"status":0,"message":"提示消息","content":{}
 *
 * @param <T>
 */
public class HttpResult<T> {

    /**
     * 默认约定返回 格式 ： {"status":0,"msg":"提示消息","content":{}}
     * status : 0
     * msg : 提示消息
     * content : {} 或 {[{},{},{}]}
     */
    private int status;
    private T content;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
