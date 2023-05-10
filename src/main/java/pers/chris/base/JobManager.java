package pers.chris.base;

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
    private final Map<String, Job> jobs;

    private JobManager() {
        jobs = new ConcurrentHashMap<>();
    }

    public static JobManager getInstance() {
        if (instance == null) {
            instance = new JobManager();
        }
        return instance;
    }

    public void addJob(Job... jobs) {
        for (Job job : jobs) {
            if (this.jobs.size() > MAX_JOB_NUM) {
                throw new RuntimeException("Jobs are full");
            }
            this.jobs.put(job.getId(), job);
        }

    }

    public void run() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(MAX_JOB_NUM);
        for (Job job : jobs.values()) {
            threadPool.execute(new JobThread(job));
        }

        threadPool.shutdown();
        while (!threadPool.awaitTermination(500, TimeUnit.MILLISECONDS)) {
            System.out.println("Jobs are running...");
        }
        System.out.println("Jobs are finished");
    }
}
