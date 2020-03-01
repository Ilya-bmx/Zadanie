package com.project.utils;

import org.springframework.stereotype.Component;

@Component
public class BusinnessStrings {

    public static String getCity(String str) {

        char[] chars = str.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i] == ',') {
                for (int j = i - 1; j >= 0; j--) {
                    if (chars[j] != ',') {
                        result.append(chars[j]);
                    } else break;
                }
                break;
            }
        }
        return result.reverse()
                        .toString()
                        .replace(",", "")
                        .replace(" ", "");
    }

}
