package pers.chris.base.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import pers.chris.base.context.Context;
import pers.chris.base.util.ReflectUtil;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
@SuppressWarnings("rawtypes")
public class TaskManager {

    private static TaskManager instance;
    private final Map<String, BaseTaskDefinition> taskDefinitions;


    private TaskManager() {
        taskDefinitions = new ConcurrentHashMap<>();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public <Input, Output> void addTask(String taskDefinitionClassName, Context context) {
        BaseTaskDefinition<Input, Output> taskDefinition = getTaskDefinition(taskDefinitionClassName);
        execute(taskDefinition, context);
    }

    @SuppressWarnings("unchecked")
    private <Input, Output> BaseTaskDefinition<Input, Output> getTaskDefinition(String taskDefinitionClassName) {
        // If the task definition has not been initialized, initialize it.
        if (!taskDefinitions.containsKey(taskDefinitionClassName)) {
            taskDefinitions.put(taskDefinitionClassName, ReflectUtil.newInstance(taskDefinitionClassName));
        }

        return (BaseTaskDefinition<Input, Output>) taskDefinitions.get(taskDefinitionClassName);
    }

    public final <Input, Output> void execute(BaseTaskDefinition<Input, Output> taskDefinition, Context context) {
        BaseTaskInstance<Input, Output> taskInstance = BaseTaskInstance.getInstance(taskDefinition, context);
        taskInstance.run();
    }
}
