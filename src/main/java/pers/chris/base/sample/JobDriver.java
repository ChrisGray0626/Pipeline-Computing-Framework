package pers.chris.base.sample;


import pers.chris.base.job.JobDefinition;
import pers.chris.base.reader.FileReader;
import pers.chris.base.writer.ConsoleWriter;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class JobDriver {

    public static void main(String[] args) throws InterruptedException {
        JobDefinition jobDefinition1 = new JobDefinition();
        jobDefinition1.setReader(new FileReader("data/input1.txt"));
        jobDefinition1.addTask(Task1Definition.class, Task2Definition.class);
        jobDefinition1.setWriter(new ConsoleWriter());
        jobDefinition1.submit();

        JobDefinition jobDefinition2 = new JobDefinition();
        jobDefinition2.setReader(new FileReader("data/input1.txt"));
        jobDefinition2.addTask(Task1_1Definition.class, Task2Definition.class);
        jobDefinition2.setWriter(new ConsoleWriter());
        jobDefinition2.submit();

        JobDefinition jobDefinition3 = new JobDefinition();
        jobDefinition3.setReader(new FileReader("data/input2.txt"));
        jobDefinition3.addTask(Task1_1Definition.class, Task2Definition.class);
        jobDefinition3.setWriter(new ConsoleWriter());
        jobDefinition3.submit();

        // JobManager.getInstance().shutdown();
    }
}
