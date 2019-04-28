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

    private static String getList3(int n, int k) {
        if (n == 1) {
            return "" + n;
        }
        int[] factorial = new int[n - 1];
        factorial[0] = 1;
        List<Integer> originValue = new ArrayList<>();
        for (int index = 1; index < n - 1; index++) {
            factorial[index] = factorial[index - 1] * (index + 1);
            originValue.add(index);
        }
        originValue.add(n - 1);
        originValue.add(n);

        String resultTemp = "";
        int reminder = k;
        for (int index = 1; index <= n; index++) {
            int dealer = 0;
            if (originValue.size() == 1) {
                dealer = originValue.get(0);
            } else {
                if (reminder != 0) {
                    int length = factorial[n - index - 1];
                    int reminderTemp = reminder % length;
                    dealer = reminder / length + (reminderTemp != 0 ? 1 : 0);
                    reminder = reminderTemp;

                    dealer = originValue.get(dealer - 1);
                } else {
                    dealer = originValue.get(originValue.size() - 1);
                }

                originValue.remove(Integer.valueOf(dealer));
            }

            resultTemp = resultTemp + dealer;
        }

        return resultTemp;
    }

    private static int result2 = 0;

    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;
        result2 = 0;
        for (int[] envelope : envelopes) {
            maxEnvelopes(envelopes, envelope, 1);
        }
        return result2;
    }

    public static int maxEnvelopes(int[][] envelopes, int[] reference, int result) {
        result2 = Math.max(result, result2);
        for (int[] envelope : envelopes) {
            if (reference[0] < envelope[0] && reference[1] < envelope[1]) {
                return maxEnvelopes(envelopes, envelope, result + 1);
            }
        }
        return result;
    }


    public static int[][] merge(int[][] intervals) {
        int[][] resultTemp = new int[intervals.length][2];

        int newIndex = -1;
        for (int index = 0; index < intervals.length; index++) {
            int[] value = intervals[index];
            // search temp
            int[] temp = null;
            int tempIndex = -1;
            for (int index2 = 0; index2 <= newIndex; index2++) {
                if (resultTemp[index2][1] < value[0] || value[1] < resultTemp[index2][0]) {
                    continue;
                }
                tempIndex = index2;
                temp = resultTemp[index2];

                resultTemp[tempIndex] = new int[]{Math.min(value[0], temp[0]),
                        Math.max(value[temp.length - 1], temp[temp.length - 1])};
            }

            if (temp == null) {
                resultTemp[++newIndex] = value;
            }
        }

        int[][] result = new int[newIndex + 1][2];
        for (int i = 0; i <= newIndex; i++) {
            result[i] = resultTemp[i];
        }
        // 没有合并过无需处理
        if (result.length != intervals.length) {
            return merge(result);
        }

        return result;
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        for (int horizontal = 0; horizontal < grid.length; horizontal++) {
            for (int vertical = 0; vertical < grid[0].length; vertical++) {

                result = Math.max(result, maxAreaOfIsland(grid, horizontal, vertical, new int[grid.length][grid[0].length]));
            }
        }
        return result;
    }

    private static int maxAreaOfIsland(int[][] grid, int horizontal, int vertical, int[][] visited) {
        if (horizontal < 0
                || vertical < 0
                || horizontal >= grid.length
                || vertical >= grid[0].length
                || grid[horizontal][vertical] == 0
                || visited[horizontal][vertical] == 1) {
            return 0;
        }

        visited[horizontal][vertical] = 1;

        return 1 + maxAreaOfIsland(grid, horizontal + 1, vertical, visited)
                + maxAreaOfIsland(grid, horizontal - 1, vertical, visited)
                + maxAreaOfIsland(grid, horizontal, vertical - 1, visited)
                + maxAreaOfIsland(grid, horizontal, vertical + 1, visited);
    }

    public static int findCircleNum(int[][] M) {
        int[] marked = new int[M.length];
        int result = 0;
        for (int people = 0; people < M.length; people++) {
            // 没有被检索过
            if (marked[people] == 0) {
                result++;
                markAllFriends(M, people, marked);
            }
        }
        return result;
    }

    private static void markAllFriends(int[][] M, int k, int[] marked) {
        marked[k] = 1;
        for (int people = 0; people < M.length; people++) {
            // 已经在朋友圈或者不是朋友
            if (marked[people] == 1 || M[k][people] == 0) {
                continue;
            }
            // 新朋友则找他所有的朋友
            markAllFriends(M, people, marked);
        }
    }

    public static int maxProfit(int[] prices) {
        int result = 0;
        for (int left = 0; left < prices.length; left++) {
            for (int right = left + 1; right < prices.length; right++) {
                result = Math.max(result, prices[right] - prices[left]);
            }
        }
        return result;
    }

    public static int maxProfit2(int[] prices) {
        int result = 0;
        if (prices == null || prices.length == 0) {
            return result;
        }
        int min = prices[0];
        for (int right = 1; right < prices.length; right++) {
            result = Math.max(result, prices[right] - min);
            min = Math.min(min, prices[right]);
        }
        return result;
    }

    public static int maxProfit3(int[] prices) {
        int result = 0;
        if (prices == null || prices.length <= 1) {
            return result;
        }
        int left = 0;
        int right = 0;
        for (int index = 1; index < prices.length; index++) {
            if (prices[index] >= prices[right]) {
                right++;
            } else if (prices[index] < prices[right]) {
                result += (prices[right] - prices[left]);
                right = index;
                left = index;
            }
        }
        result += (prices[right] - prices[left]);

        return result;
    }

    public static int maximalSquare(char[][] matrix) {
        int result = 0;
        for (int horizontal = 0; horizontal < matrix.length; horizontal++) {
            for (int vertical = 0; vertical < matrix[horizontal].length; vertical++) {
                if (matrix[horizontal][vertical] == '0') {
                    continue;
                }

                int square = 1;
                result = Math.max(result, square);
                int horizontalTemp;
                int verticalTemp;
                boolean match = true;
                while (match
                        && horizontal + square < matrix.length
                        && vertical + square < matrix[horizontal].length) {
                    for (horizontalTemp = horizontal; match && horizontalTemp <= horizontal + square; horizontalTemp++) {
                        for (verticalTemp = vertical; verticalTemp <= vertical + square; verticalTemp++) {
                            if (matrix[horizontalTemp][verticalTemp] == '0') {
                                match = false;
                                break;
                            }
                        }
                    }

                    if (match) {
                        result = Math.max(result, square + 1);
                        square++;
                    }
                }
            }
        }
        return result * result;
    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int resultTemp = nums[0];
        int result = resultTemp;
        for (int index = 1; index < nums.length; index++) {
            if (resultTemp <= 0) {
                resultTemp = nums[index];
            } else {
                resultTemp = resultTemp + nums[index];
            }
            result = Math.max(result, resultTemp);
        }
        return result;
    }

    private static int result = Integer.MAX_VALUE;

    public static int minimumTotal2(List<List<Integer>> triangle) {
        int[] res = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
            }
        }

        return res[0];
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        result = Integer.MAX_VALUE;
        add(triangle, 1, 0, 0);
        return result;
    }

    private static void add(List<List<Integer>> triangle, int deepth, int currentIndex, int preSum) {
        if (deepth == triangle.size()) {
            preSum += triangle.get(deepth - 1).get(currentIndex);
            result = Math.min(result, preSum);
            return;
        }

        add(triangle, deepth + 1, currentIndex, preSum + triangle.get(deepth - 1).get(currentIndex));
        add(triangle, deepth + 1, currentIndex + 1, preSum + triangle.get(deepth - 1).get(currentIndex));
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
//        getList2(result2, "", 5, 1);
//        String result = getList3(3, 2);
//        merge(new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}});
//        int result = maxAreaOfIsland(new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}});
//        int result = findCircleNum(new int[][]{{1, 0, 0, 1},
//                {0, 1, 1, 0},
//                {0, 1, 1, 1},
//                {1, 0, 1, 1}});
//        int result = maxProfit3(new int[]{1, 2, 3, 4, 5});
//        int result = maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
//        int result = minimumTotal(get(new int[][]{{1}, {2, 3}}));
//        int result = maximalSquare(new char[][]{
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}});
        int result = maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}});
    }

    private static List<List<Integer>> get(int[][] values) {
        List<List<Integer>> arrays = new ArrayList<>();
        for (int[] value1 : values) {
            List<Integer> value = new ArrayList<>();
            for (int aValue1 : value1) {
                value.add(aValue1);
            }
            arrays.add(value);
        }
        return arrays;
    }

}
