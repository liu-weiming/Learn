package jmh.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class Example8 {

    @State(Scope.Group)
    public static class Test {
        public Test() {
            System.out.println("create instance...");
        }

        public void write() {
            System.out.println("write");
        }

        public void read() {
            System.out.println("read");
        }
    }
    //在线程组test中将有3个线程不断对Test实例调用write方法，另外3个线程不断调用read方法
    @GroupThreads(3)
    @Group("test")
    @Benchmark
    public void testWrite(Test test) {
        test.write();
    }

    @Group("test")
    @GroupThreads(3)
    @Benchmark
    public void testRead(Test test) {
        test.read();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(Example8.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
