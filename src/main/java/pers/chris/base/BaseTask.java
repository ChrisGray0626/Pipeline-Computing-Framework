package pers.chris.base;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */

public abstract class BaseTask<Input, Output> {

    protected String taskId;
    protected Job job;

    public abstract Output execute(Input input);

}
