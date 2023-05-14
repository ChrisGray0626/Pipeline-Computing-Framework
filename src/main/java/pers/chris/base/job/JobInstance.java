package pers.chris.base.job;

import pers.chris.base.datatype.Context;
import pers.chris.base.task.TaskManager;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class JobInstance extends Thread {
    private final JobDefinition job;
    private final Context context;

    public JobInstance(JobDefinition job) {
        this.job = job;
        this.context = new Context();
    }

    @Override
    public void run() {
        // TODO Reader Writer Instance
        context.set(job.getReader().read());

        for (String taskClassName : job.getTaskClassNames()) {
            TaskManager taskManager = new TaskManager(taskClassName, context);
            taskManager.run();
        }

        job.getWriter().write(context.get());
        this.interrupt();
    }
}
