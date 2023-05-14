package pers.chris.base.datatype;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/14
 */
public class Context {

    private Object data;

    public Context(Object data) {
        this.data = data;
    }

    public Context() {
    }

    public Object get() {
        return data;
    }

    public void set(Object data) {
        this.data = data;
    }

    public void clear() {
        this.data = null;
    }
}
