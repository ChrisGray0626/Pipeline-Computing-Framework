package pers.chris.base.task;

import pers.chris.base.context.Context;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/14
 */
public abstract class BaseTaskInstance<Input, Output> extends Thread {

    protected final Context context;

    public BaseTaskInstance(Context context) {
        this.context = context;
    }

    public static <Input, Output> BaseTaskInstance<Input, Output> getInstance(
            BaseTaskDefinition<Input, Output> taskDefinition, Context context) {
        if (taskDefinition instanceof BaseParallelTaskDefinition) {
            return new ParallelTaskInstance<>((BaseParallelTaskDefinition<Input, Output>) taskDefinition, context);
        }
        else {
            return new SingleTaskInstance<>(taskDefinition, context);
        }
    }
}
