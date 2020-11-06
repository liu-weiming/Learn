package jmh.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * JMH对基准测试的方法需要使用@Benchmark注解进行标记，否则方法将被视为普通方法，并且不会对其执行基准测试。
 * 如果一个类中没有任何基准测试方法（被@Benchmark标记的方法），那么对其进行基准测试则会出现异常
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class Example2 {

    /**
     * normal instance method
     */
    public void normalMethod() {

    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(Example2.class.getSimpleName())
                .forks(1)
                .measurementIterations(10)
                .warmupIterations(10)
                .build();
        new Runner(opts).run();
    }

}
