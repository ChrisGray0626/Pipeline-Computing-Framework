package pers.chris.base.job;

import pers.chris.base.job.JobDefinition;
import pers.chris.base.task.BaseTaskDefinition;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class JobInstance extends Thread {
    private final JobDefinition jobDefinition;

    public JobInstance(JobDefinition jobDefinition) {
        this.jobDefinition = jobDefinition;
    }

    @Override
    public void run() {
        Object context;
        context = jobDefinition.getReader().read();

        try {
            for (String taskClassName : jobDefinition.getTaskClassNames()) {
                Class<? extends BaseTaskDefinition> clazz = (Class<? extends BaseTaskDefinition>) Class.forName(taskClassName);
                BaseTaskDefinition task = clazz.newInstance();
                context = task.execute(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jobDefinition.getWriter().write(context);
        this.interrupt();
    }
}
