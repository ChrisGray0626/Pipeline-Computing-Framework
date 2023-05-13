package pers.chris.base.reader;

import pers.chris.base.Configuration;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public abstract class BaseReader {

    protected Configuration configuration;

    public BaseReader() {
        configuration = Configuration.getInstance();
    }

    public abstract Object read();

}
