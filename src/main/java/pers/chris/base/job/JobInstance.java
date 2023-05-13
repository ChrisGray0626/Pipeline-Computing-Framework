package pers.chris.base.job;

import pers.chris.base.task.BaseTaskDefinition;
import pers.chris.base.task.TaskInstance;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class JobInstance extends Thread {
    private final JobDefinition job;

    public JobInstance(JobDefinition job) {
        this.job = job;
    }

    @Override
    public void run() {
        Object context;
        context = job.getReader().read();

        try {
            for (String taskClassName : job.getTaskClassNames()) {
                // TODO 泛型
                Class<? extends BaseTaskDefinition> clazz = (Class<? extends BaseTaskDefinition>) Class.forName(taskClassName);
                BaseTaskDefinition taskDefinition = clazz.newInstance();
                TaskInstance task = new TaskInstance(taskDefinition, context);
                context = task.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        job.getWriter().write(context);
        this.interrupt();
    }
}
