package pers.chris.base;


/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class JobDriver {

    public static void main(String[] args) throws InterruptedException {
        // TODO Stream
        JobManager jobManager = JobManager.getInstance();

        Job job = new Job();
        job.setOriginalData("Hello World");
        job.setTask(Task1.class, Task2.class);
        job.submit();

        Job job1 = new Job();
        job1.setOriginalData("Good");
        job1.setTask(Task1.class, Task2.class);
        job1.submit();

        jobManager.run();

    }
}
