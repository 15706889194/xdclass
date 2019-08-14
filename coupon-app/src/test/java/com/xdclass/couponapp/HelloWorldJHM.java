package com.xdclass.couponapp;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class HelloWorldJHM {
    public static void main(String[] args) throws RunnerException {
        Options options=new OptionsBuilder().warmupBatchSize(2).measurementBatchSize(20).forks(3).build();
        new Runner(options).run();
    }

    @Benchmark
    public void HelloWorlsJMH(){
        String s="";
        for (int i = 0; i <10 ; i++) {
            s+=i;
        }

    }
    @Benchmark
    public void HelloWorlsJMH1(){
        StringBuilder b=new StringBuilder();
        for (int i = 0; i <10 ; i++) {
            b.append(i);
        }
        b.toString();
    }
}
