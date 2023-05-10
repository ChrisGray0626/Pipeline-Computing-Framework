package pers.chris.base;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/10
 */
public class Task1 extends BaseTask<String, Integer> {

    @Override
    public Integer execute(String input) {
        return input.length();
    }
}
