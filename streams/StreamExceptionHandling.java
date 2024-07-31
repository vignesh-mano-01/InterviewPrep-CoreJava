package com.interview.streams;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class StreamExceptionHandling {
    public static void main(String[] args) {
        List<String> paths = Arrays.asList("file1.txt", "file2.txt", "invalid_file.txt");

        List<String> result = paths.stream()
                .map(rethrowFunction(path -> readFile(path)))
                .collect(Collectors.toList());

        System.out.println(result);
    }

    static String readFile(String path) throws IOException {
        if ("invalid_file.txt".equals(path)) {
            throw new IOException("File not found: " + path);
        }
        return "Content of " + path;
    }

    static <T, R> Function<T, R> rethrowFunction(FunctionWithException<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @FunctionalInterface
    public interface FunctionWithException<T, R> {
        R apply(T t) throws Exception;
    }
}
