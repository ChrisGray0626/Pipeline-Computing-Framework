package pers.chris.base.job;

import pers.chris.base.context.Context;
import pers.chris.base.task.TaskManager;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class JobInstance extends Thread {
    private final JobDefinition jobDefinition;
    private final Context context;

    public JobInstance(JobDefinition jobDefinition) {
        this.jobDefinition = jobDefinition;
        this.context = new Context();
    }

    @Override
    public void run() {
        read();

        submitTask();

        write();
    }

    private void read() {
        jobDefinition.getReader().read(context);
    }

    private void submitTask() {
        TaskManager taskManager = TaskManager.getInstance();
        for (String taskDefinitionClassName : jobDefinition.getTaskClassNames()) {
            taskManager.addTask(taskDefinitionClassName, context);
        }
    }

    private void write() {
        jobDefinition.getWriter().write(context);
    }
}
