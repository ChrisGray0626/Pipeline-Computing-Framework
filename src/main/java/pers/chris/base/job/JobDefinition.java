package pers.chris.base.job;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import pers.chris.base.reader.BaseReader;
import pers.chris.base.writer.BaseWriter;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
@Getter
public class JobDefinition {

    private final List<String> taskClassNames;
    private String id;
    private String name;
    // TODO Reader and Writer
    private BaseReader reader;
    private BaseWriter writer;

    public JobDefinition() {
        taskClassNames = new ArrayList<>();
    }

    public void addTask(Class<?>... taskClass) {
        for (Class<?> task : taskClass) {
            taskClassNames.add(task.getName());
        }
    }

    public void submit() {
        id = UUID.randomUUID().toString();
        JobManager.getInstance().addJob(this);
    }

    public void setReader(BaseReader reader) {
        this.reader = reader;
    }

    public void setWriter(BaseWriter writer) {
        this.writer = writer;
    }
}
