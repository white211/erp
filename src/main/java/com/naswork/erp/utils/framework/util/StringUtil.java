package com.naswork.erp.utils.framework.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Program: StringUtil
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 11:10:30
 **/

public class StringUtil {
    private static final char UNDERLINE = '_';
    private static final Pattern PATTERN = Pattern.compile("\t|\r|\n");

    public StringUtil(){

    }

    public static String underlineToCamel(String param) {
        if (param != null && param.trim().length() != 0) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);

            for(int i = 0; i < len; ++i) {
                char c = param.charAt(i);
                if (c == '_') {
                    ++i;
                    if (i < len) {
                        sb.append(Character.toUpperCase(param.charAt(i)));
                    }
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return param;
        }
    }

    public static String firstChatToUpperCase(String param) {
        if (param != null && param.trim().length() != 0) {
            String fistChar = param.substring(0, 1).toUpperCase();
            return fistChar + param.substring(1, param.length());
        } else {
            return param;
        }
    }

    public static String camelToUnderline(String param) {
        if (param != null && param.trim().length() != 0) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);

            for(int i = 0; i < len; ++i) {
                char c = param.charAt(i);
                if (Character.isUpperCase(c)) {
                    if (i != 0) {
                        sb.append('_');
                    }

                    sb.append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return param;
        }
    }

    public static Map<String, String> strVar2Map(String str, String separator) {
        Map<String, String> varMap = new HashMap();
        if (str != null && !"".equalsIgnoreCase(str)) {
            String[] variablesArr = str.split(separator);
            if (variablesArr.length > 0) {
                String[] var4 = variablesArr;
                int var5 = variablesArr.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    String varStr = var4[var6];
                    if (varStr.contains("=")) {
                        String key = varStr.substring(0, varStr.indexOf("="));
                        String value = varStr.substring(varStr.indexOf("=") + 1, varStr.length());
                        varMap.put(key, value);
                    }
                }
            }

            return varMap;
        } else {
            return varMap;
        }
    }

    public static String check2LowerCase(String str, String toLower) {
        if (str != null && toLower != null && str.trim().length() != 0 && toLower.trim().length() != 0) {
            int index = str.indexOf(toLower);
            if (index < 0) {
                return str;
            } else {
                String lower = str.substring(index, index + toLower.length());
                str = str.substring(0, index) + lower.toLowerCase() + str.substring(index + toLower.length(), str.length());
                return check2LowerCase(str, toLower);
            }
        } else {
            return str;
        }
    }

    public static String removeSpecialCharacters(String str) {
        String dest = "";
        if (str != null) {
            Matcher m = PATTERN.matcher(str);
            dest = m.replaceAll(" ");
            dest = dest.replaceAll("\\s+", " ");
        }

        return dest;
    }

    public static void main(String[] args) {
        String xx = "p.id desc, a.merchantId ASC,          r.merchantId DESC, q.customerId DESC";
        xx = check2LowerCase(xx, "ASC");
        xx = check2LowerCase(xx, "DESC");
        System.out.println(xx);
    }

}



