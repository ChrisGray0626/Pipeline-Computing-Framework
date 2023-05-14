package pers.chris.base.sample;

import pers.chris.base.task.BaseTaskDefinition;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class Task2Definition extends BaseTaskDefinition<Integer, Integer> {

    @Override
    public Integer execute(Integer input) {
        return input * 2;
    }
}

