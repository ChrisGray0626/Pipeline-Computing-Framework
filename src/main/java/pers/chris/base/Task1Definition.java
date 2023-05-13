package pers.chris.base;

import pers.chris.base.task.BaseTaskDefinition;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class Task1Definition extends BaseTaskDefinition<String, Integer> {

    @Override
    public Integer execute(String input) {
        return input.length();
    }
}
