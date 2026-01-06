package pairmatching.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.exception.ErrorMessage;
import pairmatching.exception.PairmatchingException;

public class ResourceReader {

    private static final String LINE_SEPARATOR = "\n";

    public static String readFile(String fileName) {
        try (InputStream inputStream = getResourceAsStream(fileName);
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(reader)) {

            return bufferedReader.lines()
                    .collect(Collectors.joining(LINE_SEPARATOR));

        } catch (IOException e) {
            throw PairmatchingException.from(ErrorMessage.FILE_READ_FAILED);
        }
    }

    public static List<String> readLines(String fileName) {
        try (InputStream inputStream = getResourceAsStream(fileName);
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(reader)) {

            return bufferedReader.lines()
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw PairmatchingException.from(ErrorMessage.FILE_READ_FAILED);
        }
    }

    private static boolean isEmptyLine(String line) {
        return line.trim().isEmpty();
    }

    private static InputStream getResourceAsStream(String fileName) {
        InputStream inputStream = ResourceReader.class
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (inputStream == null) {
            throw PairmatchingException.from(ErrorMessage.FILE_NOT_FOUND);
        }

        return inputStream;
    }
}
