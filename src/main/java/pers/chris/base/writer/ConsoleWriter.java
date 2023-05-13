package pers.chris.base.writer;

import pers.chris.base.Configuration;
import pers.chris.base.context.Context;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
public class ConsoleWriter extends BaseWriter {

    public ConsoleWriter() {
        super();
        configuration.set(Configuration.Constant.WRITER_TYPE, Configuration.Constant.WriterType.CONSOLE);
    }

    @Override
    public void write(Context context) {
        System.out.println(context.get());
    }
}
