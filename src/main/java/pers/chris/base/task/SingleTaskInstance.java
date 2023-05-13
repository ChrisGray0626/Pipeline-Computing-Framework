package pers.chris.base.task;

import pers.chris.base.context.Context;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/14
 */
public class SingleTaskInstance<Input, Output> extends BaseTaskInstance<Input, Output> {

    private final BaseTaskDefinition<Input, Output> taskDefinition;

    public SingleTaskInstance(BaseTaskDefinition<Input, Output> taskDefinition, Context context) {
        super(context);
        this.taskDefinition = taskDefinition;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        Input input = (Input) context.get();

        Output output = taskDefinition.execute(input);

        context.set(output);
    }
}
