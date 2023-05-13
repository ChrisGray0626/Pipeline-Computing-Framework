package pers.chris.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
public class Configuration {

    private final Map<String, String> map;

    private Configuration() {
        map = new HashMap<>();
    }

    public static Configuration getInstance() {
        return new Configuration();
    }

    public void set(String key, String value) {
        map.put(key, value);
    }

    public String get(String key) {
        return map.getOrDefault(key, null);
    }

    public static final class Constant {
        public static final String READER_TYPE = "READER_TYPE";
        public static final String WRITER_TYPE = "WRITER_TYPE";
        public static final String TASK_TYPE = "TASK_TYPE";
        public static final String INPUT_PATH = "INPUT_PATH";
        public static final String OUTPUT_PATH = "OUTPUT_PATH";

        public static final class ReaderType {
            public static final String FILE = "FILE";
        }

        public static final class WriterType {
            public static final String CONSOLE = "CONSOLE";
            public static final String FILE = "FILE";
        }

        public static final class TaskType {
            public static final String SINGLE = "SINGLE";
            public static final String PARALLEL = "PARALLEL";
        }
    }

}
