package com.magicmover.com.magicmover.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by avismarahl on 28/06/17.
 */
public class RegexParser {
    private static String REGEX = "(\" (?:[^\"\\\\]+ | \\\\.)* \")";
    public List<String> parseSourceCode(String sourceCode) {
        List<String> codeStrings = new ArrayList<>();
        Pattern pattern = Pattern.compile(RegexParser.REGEX);
        Matcher matcher = pattern.matcher(sourceCode);
        while (matcher.find()) {
            String token = matcher.group(1);
            codeStrings.add(token);
        }
        return codeStrings;
    }
}
