package pers.chris.base.task;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import pers.chris.base.util.ReflectUtil;
import pers.chris.base.context.Context;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
@SuppressWarnings("rawtypes")
public class TaskManager {

    private final Map<String, BaseTaskDefinition> taskDefinitions;
    private static TaskManager instance;


    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    private TaskManager() {
        taskDefinitions = new ConcurrentHashMap<>();
    }

    public <Input, Output> void addTask(String taskDefinitionClassName, Context context) {
        BaseTaskDefinition<Input, Output> taskDefinition = getTaskDefinition(taskDefinitionClassName);
        execute(taskDefinition, context);
    }

    public final <Input, Output>void execute(BaseTaskDefinition<Input, Output> taskDefinition, Context context) {
        BaseTaskInstance<Input, Output> taskInstance = BaseTaskInstance.getInstance(taskDefinition, context);
        taskInstance.run();
    }

    @SuppressWarnings("unchecked")
    private <Input, Output> BaseTaskDefinition<Input, Output> getTaskDefinition(String taskDefinitionClassName) {
        // If the task definition has not been initialized, initialize it.
        if (!taskDefinitions.containsKey(taskDefinitionClassName)) {
            taskDefinitions.put(taskDefinitionClassName, ReflectUtil.newInstance(taskDefinitionClassName));
        }

        return (BaseTaskDefinition<Input, Output>) taskDefinitions.get(taskDefinitionClassName);
    }
}
