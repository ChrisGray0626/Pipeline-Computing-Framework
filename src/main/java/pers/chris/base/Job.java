package pers.chris.base;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class Job {

    private final List<String> taskClassNames;
    private String id;
    private String name;
    private Object originalData;

    public Job() {
        taskClassNames = new ArrayList<>();
    }

    public void setTask(Class<?>... taskClass) {
        for (Class<?> task : taskClass) {
            taskClassNames.add(task.getName());
        }
    }

    public void submit() {
        id = UUID.randomUUID().toString();
        JobManager.getInstance().addJob(this);
    }

    public String getId() {
        return id;
    }

    public List<String> getTaskClasses() {
        return taskClassNames;
    }

    public Object getOriginalData() {
        return originalData;
    }

    public void setOriginalData(Object originalData) {
        this.originalData = originalData;
    }
}
