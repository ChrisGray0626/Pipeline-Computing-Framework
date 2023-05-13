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

    protected BaseTaskDefinition() {
        configuration = Configuration.getInstance();
    }

    public abstract Output execute(Input input);

}
