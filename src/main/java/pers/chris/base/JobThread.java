package pers.chris.base;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class JobThread extends Thread {
    private final Job job;

    public JobThread(Job job) {
        this.job = job;
    }

    @Override
    public void run() {
        // TODO Read
        Object data = job.getOriginalData();
        try {
            for (String taskClass : job.getTaskClasses()) {
                Class<? extends BaseTask> clazz = (Class<? extends BaseTask>) Class.forName(taskClass);
                BaseTask task = clazz.newInstance();
                data = task.execute(data);
                System.out.println(data);
            }
            // TODO Write
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }
}
