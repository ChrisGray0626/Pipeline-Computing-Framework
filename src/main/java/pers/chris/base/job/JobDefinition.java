package pers.chris.base.job;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import pers.chris.base.reader.BaseReader;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
@Getter
public class JobDefinition {

    private final List<String> taskClassNames;
    private BaseReader reader;
    private String id;
    private String name;

    public JobDefinition() {
        taskClassNames = new ArrayList<>();
    }

    public void setTask(Class<?>... taskClass) {
        for (Class<?> task : taskClass) {
            taskClassNames.add(task.getName());
        }
    }

    public void setReader(BaseReader reader) {
        this.reader = reader;
    }

    public void submit() {
        id = UUID.randomUUID().toString();
        JobManager.getInstance().addJob(this);
    }
}
