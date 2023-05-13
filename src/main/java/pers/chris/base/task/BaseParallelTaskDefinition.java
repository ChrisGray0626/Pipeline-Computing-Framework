package pers.chris.base.task;

import java.util.List;
import org.apache.spark.internal.config.R;
import pers.chris.base.Configuration;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
public abstract class BaseParallelTaskDefinition<Input, Output> extends BaseTaskDefinition<Input, Output>{

    public BaseParallelTaskDefinition() {
        super();
        this.setTaskType(Configuration.Constant.TaskType.PARALLEL);
    }

    public abstract List<Input> split(Input input);

    public abstract Output merge(Output output1, Output output2);
}
