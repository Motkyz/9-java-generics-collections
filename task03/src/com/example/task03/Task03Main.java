package com.example.task03;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        TreeMap<String, TreeSet<String>> result = new TreeMap<>();
        List<String> input = new BufferedReader(new InputStreamReader(inputStream, charset)).lines()
                .map(String::toLowerCase)
                .filter(letter -> letter.matches("[а-яё]+") && letter.length() >= 3)
                .collect(Collectors.toList());
        for (String line : input) {
            char[] chars = line.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            result.computeIfAbsent(key, k -> new TreeSet<>()).add(line);
        }
        return result.values().stream().filter(list -> list.size() > 1).collect(Collectors.toList());
    }
}
