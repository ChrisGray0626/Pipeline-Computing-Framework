package pers.chris.base.job;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class JobManager {

    private static final int MAX_JOB_NUM = 3;
    private static JobManager instance;
    private final BlockingQueue<JobDefinition> jobQueue;
    private final ExecutorService threadPool;
    private final JobDispatcher jobDispatcher;

    private JobManager() {
        threadPool = Executors.newFixedThreadPool(MAX_JOB_NUM);
        jobQueue = new LinkedBlockingQueue<>();
        jobDispatcher = new JobDispatcher();

        jobDispatcher.start();
    }

    public static JobManager getInstance() {
        if (instance == null) {
            instance = new JobManager();
        }
        return instance;
    }

    public void addJob(JobDefinition... jobDefinitions) {
        jobQueue.addAll(Arrays.asList(jobDefinitions));
    }

    private void execute(JobDefinition jobDefinition) {
        JobInstance jobInstance = new JobInstance(jobDefinition);
        threadPool.execute(jobInstance);
    }

    // TODO Refactor shutdown()
    public void shutdown() throws InterruptedException {
        while (jobQueue.size() > 0) {
            System.out.println("Jobs are running...");
            Thread.sleep(500);
        }

        threadPool.shutdown();
        while (!threadPool.awaitTermination(500, TimeUnit.MILLISECONDS)) {
            System.out.println("Jobs are running...");
        }
        System.out.println("Jobs are finished");

        jobDispatcher.interrupt();
    }

    public class JobDispatcher extends Thread {

        @Override
        public void run() {
            while (true) {
                JobDefinition jobDefinition;
                try {
                    jobDefinition = jobQueue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                execute(jobDefinition);
            }
        }
    }
}
