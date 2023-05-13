package pers.chris.base.task;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import pers.chris.base.context.Context;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/14
 */
public class ParallelTaskInstance<Input, Output> extends BaseTaskInstance<Input, Output> {

    private final BaseParallelTaskDefinition<Input, Output> taskDefinition;

    public ParallelTaskInstance(BaseParallelTaskDefinition<Input, Output> taskDefinition, Context context) {
        super(context);
        this.taskDefinition = taskDefinition;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        // For storing and merging the output of each split
        ConcurrentLinkedQueue<Output> outputQueue = new ConcurrentLinkedQueue<>();
        // Get the input
        Input input = (Input) context.get();
        // Split the input
        List<Input> splits = taskDefinition.split(input);
        // Count the number of finished split tasks
        CountDownLatch latch = new CountDownLatch(splits.size());
        ExecutorService threadPool = Executors.newFixedThreadPool(splits.size());
        for (Input split : splits) {
            threadPool.execute(new Thread(() -> {
                Output output0 = taskDefinition.execute(split);
                outputQueue.add(output0);
                latch.countDown();
            }));
        }
        // Wait for all split tasks to finish
        threadPool.shutdown();
        // Merge the output of each split
        Thread mergerThread = new Thread(() -> {
            while (outputQueue.size() > 1 || latch.getCount() != 0) {
                Output output1 = outputQueue.poll();
                Output output2 = outputQueue.poll();
                outputQueue.offer(taskDefinition.merge(output1, output2));
            }
        });
        mergerThread.start();
        // Wait for the merger to finish
        try {
            mergerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Set the output
        context.set(outputQueue.poll());
    }
}
