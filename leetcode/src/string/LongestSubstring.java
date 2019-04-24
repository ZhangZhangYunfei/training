package string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

public class LongestSubstring {
    private static String getLongestSubstring(String value) {
        char[] array = value.toCharArray();
        String result = new String(new char[]{array[0]});
        for (int index = 0; index < array.length; index++) {
            String resultTemp = new String(new char[]{array[index]});
            for (int index2 = index + 1; index2 < array.length; index2++) {
                String valueTemp = new String(new char[]{array[index2]});
                if (resultTemp.contains(valueTemp)) {
                    break;
                }
                resultTemp = resultTemp.concat(valueTemp);

                result = result.length() > resultTemp.length() ? result : resultTemp;
            }
        }
        return result;
    }

    private static int getLongestSubstring2(String value) {
        TreeSet<Character> substring = new TreeSet<Character>();
        int ans = 0;
        int left = 0;
        int right = 0;
        while (left < value.length() && right < value.length()) {
            if (!substring.contains(value.charAt(right))) {
                substring.add(value.charAt(right++));
                ans = Math.max(ans, substring.size());
            } else {
                substring.remove(value.charAt(left++));
            }
        }
        return ans;
    }

    private static String getCommonPrefix(String... values) {
        String common = values[0];
        for (String value : values) {
            common = getCommon(common, value);
            if (common.length() == 0) {
                return "";
            }
        }
        return common;
    }

    private static String getCommon(String value1, String value2) {
        int length = Math.min(value1.length(), value2.length());
        String common = "";
        int i = 0;
        while (i < length) {
            if (value1.charAt(i) == value2.charAt(i)) {
                common = common.concat(new String(new char[]{value1.charAt(i)}));
                i++;
            } else {
                break;
            }
        }
        return common;
    }

    private static boolean getSub(String value1, String value2) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        if (value1 == null || value2 == null || value1.length() > value2.length()) {
            return false;
        }

        for (int index = 0; index < value1.length(); index++) {
            count1[value1.charAt(index) - 'a'] = count1[value1.charAt(index) - 'a'] + 1;
            count2[value2.charAt(index) - 'a'] = count2[value2.charAt(index) - 'a'] + 1;
        }

        for (int left = 0, right = value1.length() - 1; right < value2.length(); ) {
            if (isSame(count1, count2)) {
                return true;
            }
            right++;
            count2[value2.charAt(right) - 'a'] = count2[value2.charAt(right) - 'a'] + 1;
            count2[value2.charAt(left) - 'a'] = count2[value2.charAt(left) - 'a'] - 1;
            left++;
        }
        return false;
    }

    private static boolean isSame(int[] count1, int[] count2) {
        int index = 0;
        while (index < 26) {
            if (count1[index] != count2[index]) {
                return false;
            }
            index++;
        }
        return true;
    }


    private static int binarySearch(int[] nums, int target) {
        int startIndex = 0;
        int endIndex = nums.length - 1;

        boolean right = false;
        int endValue = nums[endIndex];
        if (endValue >= target) {
            right = true;
        }
        while (startIndex <= endIndex) {
            int middle = startIndex + ((endIndex - startIndex) >> 1);
            if (nums[middle] > target) {
                // 左坡
                if (nums[middle] > endValue) {
                    if (right) {
                        startIndex = middle + 1;
                    } else {
                        endIndex = middle - 1;
                    }
                } else {//右坡
                    if (right) {
                        endIndex = middle - 1;
                    } else {
                        // 不存在
                        throw new UnsupportedOperationException();
                    }
                }
            } else if (nums[middle] < target) {
                // 左坡
                if (nums[middle] > endValue) {
                    if (right) {
                        // 不存在
                        throw new UnsupportedOperationException();
                    } else {
                        startIndex = middle + 1;
                    }
                } else {//右坡
                    if (right) {
                        startIndex = middle + 1;
                    } else {
                        endIndex = middle - 1;
                    }
                }
            } else {
                return middle;
            }
        }
        return -1;
    }

    private static int longestIncrement(int... nums) {
        if (nums.length == 0) {
            return 0;
        }
        int ans = 1;
        for (int i = 0, j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[j - 1]) {
                ans = Math.max(j - i + 1, ans);
            } else {
                i = j;
            }
        }
        return ans;
    }

    public static List<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<>();
        restore(s, 1, "", result);
        return result;
    }

    private static void restore(String s, int position, String resultTmp, List<String> result) {
        if (position == 4) {
            if (isLegal(s)) {
                result.add(resultTmp + "." + s);
            }
        } else {
            for (int length = 1; length <= 3 && length <= s.length(); length++) {
                String temp = s.substring(0, length);
                if (isLegal(temp)) {
                    String resultTempTemp = resultTmp.equals("") ? temp : resultTmp + "." + temp;
                    restore(s.substring(length), position + 1, resultTempTemp, result);
                }
            }
        }
    }

    private static boolean isLegal(String s) {
        return s.length() > 0
                && s.length() <= 3
                && Long.valueOf(s) <= 255
                && (s.length() == 1 || !s.startsWith("0"));

    }

    public static String simplifyPath(String path) {
        String[] values = path.split("/");
        Stack<String> stringStack = new Stack<>();
        for (String value : values) {
            if (value.equals(".") || value.isEmpty()) {
                continue;
            } else if (value.equals("..")) {
                if (!stringStack.empty()) {
                    String pop = stringStack.pop();
                }
            } else {
                stringStack.push(value);
            }
        }

        StringBuilder result = stringStack.empty() ? new StringBuilder("/") : new StringBuilder();
        while (!stringStack.empty()) {
            result.insert(0, "/" + stringStack.pop());
        }
        return result.toString();
    }

    public static String reverseWords(String s) {
        String result = "";
        String[] arr = s.split(" ");
        for (int index = arr.length - 1; index >= 0; index--) {
            if (arr[index].isEmpty()) {
                continue;
            }
            result = result.equals("") ? arr[index] : result + " " + arr[index];
        }
        return result;
    }

    public static String multiply(String num1, String num2) {
        int length1 = num1.length();
        int length2 = num2.length();
        int length3 = length1 + length2;
        String[] result = new String[length3];

        int index = 0;
        for (int index2 = length2 - 1; index2 >= 0; index2--) {
            index++;
            String value2 = num2.substring(index2, index2 + 1);
            int temp = 0;
            int internalIndex = 0;
            int realIndex = 0;
            for (int index1 = length1 - 1; index1 >= 0; index1--) {
                String value1 = num1.substring(index1, index1 + 1);
                int resultTemp = Integer.valueOf(value2) * Integer.valueOf(value1) + temp;

                realIndex = length3 - index - internalIndex++;
                resultTemp = resultTemp + (result[realIndex] == null ? 0 : Integer.valueOf(result[realIndex]));

                temp = 0;
                while (resultTemp >= 10) {
                    resultTemp = resultTemp - 10;
                    temp++;
                }
                result[realIndex] = String.valueOf(resultTemp);
            }
            if (temp != 0) {
                result[realIndex - 1] = String.valueOf(temp + (result[realIndex - 1] == null ? 0 : Integer.valueOf(result[realIndex - 1])));
            }
        }
        StringBuilder builder = new StringBuilder();
        boolean flag = true;
        for (String st : result) {
            if (st == null) {
                continue;
            }
            if (st.equals("0") && flag) {
                continue;
            } else {
                flag = false;
                builder.append(st);
            }
        }
        return builder.toString().isEmpty() ? "0" : builder.toString();
    }

    public String getPermutation(int n, int k) {
        return "";
    }

    private static List<String> getList() {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            for (int i1 = 1; i1 <= 3; i1++) {
                if (i1 == i) {
                    continue;
                }
                for (int i2 = 1; i2 <= 3; i2++) {
                    if (i2 == i || i2 == i1) {
                        continue;
                    }
                    result.add("" + i + i1 + i2);
                }
            }
        }
        return result;
    }

    private static List<String> getList2(List<String> result,
                                         String resultTemp,
                                         int total,
                                         int deepth) {
        for (int i = 1; i <= total; i++) {
            if (resultTemp.contains("" + i)) {
                continue;
            }

            if (deepth == total) {
                result.add(resultTemp + i);
            } else {
                getList2(result, resultTemp + i, total, deepth + 1);
            }
        }

        return result;
    }


    public static void main(String[] args) {
//        String result = getLongestSubstring("pwwkew");
//        System.out.println(result);
//
//        int num = getLongestSubstring2("pwwkew");
//        System.out.println(num);
//
//        String common = getCommonPrefix("flower", "flow", "flight");
//        System.out.println(common);
//
//        boolean have = getSub("ab", "eidbaooo");
//        System.out.println(have);

//        int result = binarySearch(new int[]{4,5,6,7,0,1,2}, 0);
//        System.out.println(result);

//        int result = longestIncrement(1, 3, 5, 4, 2, 3, 4, 5);
//        System.out.println(result);

//        String result = simplifyPath("/a/../../b/../c//.//");

//        var result = restoreIpAddresses("25525511135");
//        reverseWords("  hello world!  ");

//        multiply("9", "9");
//        List<String> result = getList();
//        List<String> result2 = new ArrayList<>();
//
//        getList2(result2, "", 3, 1);
    }
}
