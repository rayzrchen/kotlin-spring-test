package com.example.kotlinspringboot.parser.simple;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SimpleParserBase {
    List<String> singleElementList(String inString, Token repeat) {
        return Arrays.asList(
            repeat == null ? inString : ""
        );
    }

    String addQuote(String s) {
        var pattern = Pattern.compile("[ +-]");
        return pattern.matcher(s).find() ? "'" + s + "'" : s;
    }

    List<String> mergeToLeft(List<String> left, List<String> right) {
        var filter1 = left.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        var filter2 = right.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        filter1.addAll(filter2);
        return filter1;
    }

    List<String> getCombinationsOfTwoList(List<String> first, List<String> second, Token concatToken) {
        var concatSpace = concatToken == null || concatToken.image.equals("") ? "" : " ";
        var firstNotEmpty = first.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        var secondNotEmpty = second.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        if (firstNotEmpty.size() == 0) {
            return secondNotEmpty;
        }
        if (secondNotEmpty.size() == 0) {
            return firstNotEmpty;
        }
        return firstNotEmpty.stream().flatMap(f -> secondNotEmpty.stream().map(s -> f + concatSpace + s)).collect(Collectors.toList());
    }

    String getPartString(List<String> list) {
        return list.stream().map(this::addQuote).collect(Collectors.joining(" "));
    }

    List<String> groupConsiderRepeat(List<String> list, Token repeat) {
        return repeat == null ? list : Arrays.asList("");
    }

}
