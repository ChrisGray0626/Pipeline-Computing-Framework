package pers.chris.base.writer;

import pers.chris.base.Configuration;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
public abstract class BaseWriter {

    protected Configuration configuration;

    protected BaseWriter() {
        configuration = Configuration.getInstance();
    }

    public abstract void write(Object data);
}
