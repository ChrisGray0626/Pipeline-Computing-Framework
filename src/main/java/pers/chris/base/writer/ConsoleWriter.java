package pers.chris.base.writer;

import pers.chris.base.Configuration;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
public class ConsoleWriter extends BaseWriter{

    public ConsoleWriter() {
        configuration.set(Configuration.Constant.WRITER_TYPE, Configuration.Constant.WriterType.CONSOLE_WRITER);
    }
    @Override
    public void write(Object data) {
        System.out.println(data);
    }
}
