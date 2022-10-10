package io.micronaut.advocacy;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public static String[] splitEqually(String src, int len) {
        String[] result = new String[(int)Math.ceil((double)src.length()/(double)len)];
        for (int i=0; i< result.length; i++) {
            result[i] = src.substring(i * len, Math.min(src.length(), (i + 1) * len));
        }
        return result;
    }
}
