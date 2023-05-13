package pers.chris.base;

import java.util.Map;
import java.util.HashMap;
/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
public class Configuration {

    private final Map<String, String> map;

    public static final class Constant {
        public static final String READER_TYPE = "READER_TYPE";
        public static final String WRITER_TYPE = "WRITER_TYPE";
        public static final String INPUT_PATH = "INPUT_PATH";
        public static final String OUTPUT_PATH = "OUTPUT_PATH";
        public static final class ReaderType {
            public static final String FILE_READER = "FILE_READER";
        }
    }

    public static Configuration getInstance() {
        return new Configuration();
    }

    private Configuration() {
        map = new HashMap<>();
    }

    public void set(String key, String value) {
        map.put(key, value);
    }

    public String get(String key) {
        return map.getOrDefault(key, null);
    }

}
