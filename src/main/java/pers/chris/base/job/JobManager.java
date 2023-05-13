package pers.chris.base.job;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class JobManager {

    private static final int MAX_JOB_NUM = 3;
    private static JobManager instance;
    private final Map<String, JobDefinition> jobs;

    private JobManager() {
        jobs = new ConcurrentHashMap<>();
    }

    public static JobManager getInstance() {
        if (instance == null) {
            instance = new JobManager();
        }
        return instance;
    }

    public void addJob(JobDefinition... jobDefinitions) {
        for (JobDefinition jobDefinition : jobDefinitions) {
            if (this.jobs.size() > MAX_JOB_NUM) {
                throw new RuntimeException("Jobs are full");
            }
            this.jobs.put(jobDefinition.getId(), jobDefinition);
        }

    }

    public void run() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(MAX_JOB_NUM);
        for (JobDefinition jobDefinition : jobs.values()) {
            threadPool.execute(new JobInstance(jobDefinition));
        }

        threadPool.shutdown();
        while (!threadPool.awaitTermination(500, TimeUnit.MILLISECONDS)) {
            System.out.println("Jobs are running...");
        }
        System.out.println("Jobs are finished");
    }
}
