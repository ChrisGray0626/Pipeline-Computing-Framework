package pers.chris.base.sample;

import java.util.List;
import pers.chris.base.task.BaseParallelTaskDefinition;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
public class Task1_1Definition extends BaseParallelTaskDefinition<String, Integer> {
    @Override
    public List<String> split(String input) {
        String[] splits = input.split("\n");
        return List.of(splits);
    }

    @Override
    public Integer merge(Integer output1, Integer output2) {
        return output1 + output2;
    }

    @Override
    public Integer execute(String input) {
        return input.split(" ").length;
    }
}
