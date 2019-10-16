package cc.xiaoquer.exception.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nicholas on 2019/10/14.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, timeUnit = TimeUnit.NANOSECONDS)
@Measurement(iterations = 5, timeUnit = TimeUnit.NANOSECONDS)
@Fork(value = 1, warmups = 1)
@State(Scope.Benchmark)
public class ExceptionPerformanceTestJmh {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ExceptionPerformanceTestJmh.class.getName())
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public Exception newException() {
        return new MyException("Normal Exception");
    }

    @Benchmark
    public Exception newExceptionNoStackTrace() {
        return new MyExceptionNoStackTrace("Exception Without Stack Trace");
    }

    @Benchmark
    public StringObj newStringObj() {
        return new StringObj("New String Object");
    }

    @Benchmark
    public void throwException() {
        try {
            throw new MyException("Throw Exception");
        } catch (MyException ignore) {}
    }

    @Benchmark
    public void throwExceptionNoStackTrace() {
        try {
            throw new MyExceptionNoStackTrace("Throw Exception Without Stack Trace");
        } catch (MyExceptionNoStackTrace ignore) {}
    }

    @Benchmark
    public StackTraceElement[] getStackTrace() {
        return Thread.currentThread().getStackTrace();
    }

    private class StringObj
    {
        private final String m_str;

        private StringObj(String str) {
            this.m_str = str;
        }

        public String getStr() {
            return m_str;
        }
    }
}
