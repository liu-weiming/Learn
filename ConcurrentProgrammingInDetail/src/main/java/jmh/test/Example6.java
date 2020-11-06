package jmh.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 三大State：Benchmark、Thread、Group
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(5)//设置5个线程运行基准测试方法
public class Example6 {

    /**
     * 线程独享的State指每一个运行基准测试方法的线程都会持有一个独立的对象实例，
     * 该实例既可能是作为基准测试方法参数传入的，也可能是运行基准测试方法所在的宿主class
     */
    @State(Scope.Thread)
    public static class Test {
        public Test() {
            System.out.println("create instance");
        }

        public void method() {}
    }

    //通过基准测试将State引用传入
    @Benchmark
    public void test(Test test) {
        test.method();
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(Example6.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
