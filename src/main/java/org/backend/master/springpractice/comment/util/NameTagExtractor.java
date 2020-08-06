package org.backend.master.springpractice.comment.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameTagExtractor {

    public static List<String> extractNameTags(String content) {
        Pattern p = Pattern.compile("#([0-9a-zA-Z가-힣]*)");
        Matcher m = p.matcher(content);
        List<String> nameTags = new ArrayList<>();

        while(m.find()) {
            String nameTag = findNameTag(m.group());

            if( nameTag != null) {
                nameTags.add(nameTag);
                System.out.println("이름태그 : " + nameTag);
            }
        }

        return nameTags;
    }

    public static String findNameTag(String str) {
       str = StringUtils.replaceChars(str, "-_+=!@#$%^&*()[]{}|\\;:'\"<>,.?/~`） ","");

        if(str.length() < 1) {
            return null;
        }

        return str;
    }
}
