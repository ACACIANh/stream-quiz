package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Quiz1 {

    // 1.1 각 취미를 선호하는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();
        Map<String, Integer> hobbyCount = new LinkedHashMap<>();
        csvLines.stream()
                .flatMap(person -> Arrays.stream(person[1].split(":")))
                .map(String::trim)
                .forEach(hobby -> hobbyCount.merge(hobby, 1, (before, after) -> ++before));
        return hobbyCount;
    }

    // 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();
        Map<String, Integer> hobbyCount = new LinkedHashMap<>();
        csvLines.stream()
                .filter(person -> person[0].startsWith("정"))
                .flatMap(person -> Arrays.stream(person[1].split(":")))
                .map(String::trim)
                .forEach(hobby -> hobbyCount.merge(hobby, 1, Integer::sum));
        return hobbyCount;
    }

    // 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.
    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return csvLines.stream()
                .mapToInt(person -> countContains(person[2], "좋아"))
                .sum();
    }

    private int countContains(String input, String target) {
        int count = input.length() - input.replace(target, "").length();
        return count / target.length();
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}
