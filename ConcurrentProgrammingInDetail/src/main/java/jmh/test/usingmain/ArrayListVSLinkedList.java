package jmh.test.usingmain;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 虽然能一定程度上体现性能的差异但是
 * 使用main方法进行测试不够规范，两个方法执行次序的不同有可能造成运行环境的差异影响性能
 */
public class ArrayListVSLinkedList {
    private final static String DATA = "DUMMY DATA";
    private final static int MAX_CAPACITY = 1000000;
    private final static int MAX_ITERATIONS = 10;

    private static void test(List<String> list) {
        for(int i=0; i<MAX_CAPACITY; i++) {
            list.add(DATA);
        }
    }

    private static void arrayListPerformanceTest(int iterations) {
        for(int i=0; i<iterations; i++) {
            final List<String> list = new ArrayList<String>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            test(list);
            System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        }
    }

    private static void linkedListPerformanceTest(int iterations) {
        for(int i=0; i<iterations; i++) {
            final List<String> list = new LinkedList<String>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            test(list);
            System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        }
    }

    public static void main(String[] args) {
        arrayListPerformanceTest(MAX_ITERATIONS);
        System.out.println(Strings.repeat("#", 100));
        linkedListPerformanceTest(MAX_ITERATIONS);
    }
}
