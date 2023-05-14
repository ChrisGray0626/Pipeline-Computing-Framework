package pers.chris.base.task;

import java.util.concurrent.TimeUnit;
import pers.chris.base.util.ReflectUtil;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import pers.chris.base.datatype.Context;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
public class TaskManager {

    private final String taskDefinitionClassName;
    private final Context context;

    public TaskManager(String taskDefinitionClassName, Context context) {
        this.taskDefinitionClassName = taskDefinitionClassName;
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public <Input, Output> void run() {
        BaseTaskDefinition<Input, Output> taskDefinition = ReflectUtil.newInstance(taskDefinitionClassName);
        BaseTaskInstance<Input, Output> taskInstance = BaseTaskInstance.getInstance(taskDefinition, context);
        taskInstance.run();
    }
}
