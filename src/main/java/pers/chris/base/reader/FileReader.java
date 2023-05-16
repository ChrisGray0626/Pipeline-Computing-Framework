package pers.chris.base.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import pers.chris.base.Configuration;
import pers.chris.base.context.Context;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
public class FileReader extends BaseReader {

    public FileReader(String filePath) {
        super();
        configuration.set(Configuration.Constant.READER_TYPE, Configuration.Constant.ReaderType.FILE);
        configuration.set(Configuration.Constant.INPUT_PATH, filePath);
    }


    @Override
    public void read(Context context) {
        Path path = Paths.get(configuration.get(Configuration.Constant.INPUT_PATH));
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        context.set(new String(bytes));
    }
}
