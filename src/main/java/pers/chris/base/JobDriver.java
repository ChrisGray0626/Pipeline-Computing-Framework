package pers.chris.base;


import pers.chris.base.job.JobDefinition;
import pers.chris.base.job.JobManager;
import pers.chris.base.reader.FileReader;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class JobDriver {

    public static void main(String[] args) throws InterruptedException {
        // TODO Stream
        JobManager jobManager = JobManager.getInstance();

        JobDefinition jobDefinition1 = new JobDefinition();
        jobDefinition1.setReader(new FileReader("data/input1.txt"));
        jobDefinition1.setTask(Task1Definition.class, Task2Definition.class);
        jobDefinition1.submit();

        JobDefinition jobDefinition2 = new JobDefinition();
        jobDefinition2.setReader(new FileReader("data/input2.txt"));
        jobDefinition2.setTask(Task1Definition.class, Task2Definition.class);
        jobDefinition2.submit();

        jobManager.run();

    }
}
