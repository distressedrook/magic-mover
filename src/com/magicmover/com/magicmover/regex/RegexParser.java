package com.magicmover.com.magicmover.regex;


import com.magicmover.models.Occurrence;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by avismarahl on 28/06/17.
 */
public class RegexParser {
    //private static String REGEX = "(\" (?:[^\"\\\\]+ | \\\\.)* \")";
    private static String REGEX = "\"(?:[^\"\\\\]+|\\\\.)*\"";
    public List<Occurrence> parseSourceCode(String sourceCode) {
        List<Occurrence> occurrences = new ArrayList<>();
        Pattern pattern = Pattern.compile(RegexParser.REGEX);
        Matcher matcher = pattern.matcher(sourceCode);

        while (matcher.find()) {
            String token = matcher.group();
            Integer startPosition = matcher.start();
            Occurrence occurrence = new Occurrence();
            occurrence.setPosition(startPosition);
            occurrence.setString(token);
            occurrences.add(occurrence);
        }
        return occurrences;
    }
}
