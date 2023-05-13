package pers.chris.base.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
public class TaskManager {

    private final List<String> taskClassNames;

    public TaskManager() {
        taskClassNames = new ArrayList<>();
    }

    public void addTask(String... taskClassNames) {
        this.taskClassNames.addAll(Arrays.asList(taskClassNames));
    }
}
