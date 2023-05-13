package pers.chris.base.reader;

import pers.chris.base.Configuration;
import pers.chris.base.context.Context;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public abstract class BaseReader {

    protected Configuration configuration;

    protected BaseReader() {
        configuration = Configuration.getInstance();
    }

    public abstract void read(Context context);

}
