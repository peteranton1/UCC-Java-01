package org.example.refactor.ex01;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FileProcessor {

    /*
    Ex01 : File Processor

    This class offers the following behaviour.

    input: List of filenames, parent path
    output: FileResult containing code and message.

    Task: Refactor processToCsvMethod2 so that the implementation is:

        * cleaner
        * easier to understand
        * less places for bugs to hide
     */
    public enum Result {
        SUCCESS,
        BAD_ARGS,
        NOT_FOUND,
        IO_ERROR;

        public boolean notIsSuccess() {
            return this != SUCCESS;
        }
    }

    public record FileResult(Result code, String message) {
    }

    public record FilePair(File input, File output) {
    }

    public FileResult processToCsvMethod1(String[] filenames, String parentPath) {

        if (filenames == null || parentPath == null || filenames.length == 0) {
            return new FileResult(Result.BAD_ARGS, "Input args were null");
        }

        List<FilePair> filePairs = new ArrayList<>();
        for (String filename : filenames) {
            filePairs.add(
                new FilePair(
                    Path.of(parentPath, filename).toFile(),
                    Path.of(parentPath, filename + ".csv").toFile()
                ));
        }

        for (FilePair filePair : filePairs) {
            FileResult fileResult = getFileResult(filePair);
            if(fileResult.code().notIsSuccess()){
                return fileResult;
            }
        }

        return new FileResult(
            Result.SUCCESS, "Complete. Files Written: " + filePairs.size());
    }

    public FileResult processToCsvMethod2(String[] filenames, String parentPath) {

        if (filenames == null || parentPath == null || filenames.length == 0) {
            return new FileResult(Result.BAD_ARGS, "Input args were null");
        }

        return Stream.of(filenames)
            .map(filename -> new FilePair(
                Path.of(parentPath, filename).toFile(),
                Path.of(parentPath, filename + ".csv").toFile()
            ))
            .map(this::getFileResult)
            .filter(r -> r.code().notIsSuccess())
            .findAny()
            .orElse(new FileResult(
                Result.SUCCESS, "Complete. Files Written: " + filenames.length));
    }

    private FileResult getFileResult(FilePair filePair) {
        try (
            BufferedReader br = new BufferedReader(new FileReader(
                filePair.input()));
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                filePair.output()));
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = getFieldsFromLine(line);
                String lineOut = convertFields(fields);
                bw.write(lineOut);
                bw.write(System.lineSeparator());
            }
            return new FileResult(
                Result.SUCCESS, "File Written: " + filePair.output());
        } catch (FileNotFoundException e) {
            return new FileResult(
                Result.NOT_FOUND, "FileNotFound: " + e.getMessage());
        } catch (IOException e) {
            return new FileResult(
                Result.IO_ERROR, "FileIOError: " + e.getMessage());
        }
    }

    private String convertFields(String[] fields) {
        // trivial implementation
        // because it is not the focus of this exercise.
        return Arrays.stream(fields)
            .reduce("", (s1, s2) -> String.join(",", s1, s2));
    }

    private String[] getFieldsFromLine(String line) {
        // trivial implementation
        // because it is not the focus of this exercise.
        if (line.trim().isEmpty()) {
            return new String[]{""};
        }
        return line.split(" ");
    }
}
