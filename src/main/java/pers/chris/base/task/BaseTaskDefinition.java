package pers.chris.base.task;

import pers.chris.base.Configuration;
import pers.chris.base.job.JobDefinition;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */

public abstract class BaseTaskDefinition<Input, Output> {

    protected String taskId;
    protected JobDefinition jobDefinition;
    protected Configuration configuration;
    private static final String DEFAULT_TASK_TYPE = Configuration.Constant.TaskType.SINGLE;

    protected BaseTaskDefinition() {
        configuration = Configuration.getInstance();
        this.setTaskType(DEFAULT_TASK_TYPE);
    }

    public abstract Output execute(Input input);

    protected void setTaskType(String taskType) {
        configuration.set(Configuration.Constant.TASK_TYPE, taskType);
    }
}
