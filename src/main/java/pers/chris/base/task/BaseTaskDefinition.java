package pers.chris.base.task;

import pers.chris.base.Configuration;
import pers.chris.base.job.JobDefinition;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */

public abstract class BaseTaskDefinition<Input, Output> {

    private static final String DEFAULT_TASK_TYPE = Configuration.Constant.TaskType.SINGLE;
    protected String taskId;
    protected JobDefinition jobDefinition;
    protected Configuration configuration;

    protected BaseTaskDefinition() {
        configuration = Configuration.getInstance();
        setTaskType(DEFAULT_TASK_TYPE);
    }

    protected void setTaskType(String taskType) {
        configuration.set(Configuration.Constant.TASK_TYPE, taskType);
    }

    public abstract Output execute(Input input);
}
