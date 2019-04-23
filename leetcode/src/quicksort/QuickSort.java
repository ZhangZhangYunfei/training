package quicksort;

import java.util.*;

public class QuickSort {
    private static void sort(int[] values, int left, int right) {
        if (left >= right) {
            return;
        }
        int leftTemp = findMiddle(values, left, right);

        sort(values, left, leftTemp);
        sort(values, leftTemp + 1, right);
    }

    private static int findMiddle(int[] values, int left, int right) {
        int leftTemp = left;
        int rightTemp = right;
        int initial = values[left];
        while (leftTemp < rightTemp) {
            while (leftTemp < rightTemp && values[rightTemp] >= initial) {
                rightTemp--;
            }
            // swap
            if (leftTemp < rightTemp) {
                int temp = values[rightTemp];
                values[rightTemp] = values[leftTemp];
                values[leftTemp] = temp;
            }

            while (leftTemp < rightTemp && values[leftTemp] <= initial) {
                leftTemp++;
            }
            // swap
            if (leftTemp < rightTemp) {
                int temp = values[rightTemp];
                values[rightTemp] = values[leftTemp];
                values[leftTemp] = temp;
            }
        }
        return leftTemp;
    }

    private static int findMiddle2(int[] values, int left, int right) {
        int temp = values[left];
        int location = left;
        for (int i = left + 1; i <= right; i++) {
            if (values[i] < temp) {
                location++;
                int wapTemp = values[location];
                values[location] = values[i];
                values[i] = wapTemp;
            }
        }

        int wapTemp = values[location];
        values[location] = values[left];
        values[left] = wapTemp;

        return location;
    }

    private static int findMiddle3(int[] values, int left, int right) {
        int temp = values[right];
        int location = left;
        for (int i = left; i < right; i++) {
            if (values[i] < temp) {
                int wapTemp = values[location];
                values[location] = values[i];
                values[i] = wapTemp;
                location++;
            }
        }
        int wapTemp = values[location + 1];
        values[location + 1] = values[right];
        values[right] = wapTemp;

        return location;
    }

    private static int findK(int[] values, int left, int right, int k) {
        if (values.length == 1) {
            return values[0];
        }

        if (left >= right) {
            return values[left];
        }

        int leftTemp = findMiddle(values, left, right);

        if (leftTemp == k - 1) {
            return values[leftTemp];
        }


        return leftTemp >= k
                ? findK(values, left, leftTemp, k)
                : findK(values, leftTemp + 1, right, k);
    }

    public static int longestConsecutive(int[] nums) {
        int result = 0;

        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }

        for (int num : nums) {
            if (!set.contains(num)) {
                continue;
            }
            set.remove(num);

            int pre = num - 1;
            while (set.contains(pre)) {
                set.remove(pre);
                pre--;
            }
            int next = num + 1;
            while (set.contains(next)) {
                set.remove(next);
                next++;
            }
            result = Math.max(result, next - pre - 1);
        }
        return result;
    }

    public String getPermutation(int n, int k) {
        return "";

    }

    private static void test(int n, List<Integer> resultTemp, List<List<Integer>> result) {
        for (int index = 1; index <= n; index++) {
            if (resultTemp.contains(index)) {
                continue;
            }
            resultTemp.add(index);
            if (resultTemp.size() == n) {
                result.add(new ArrayList<>(resultTemp));
                resultTemp.clear();
                continue;
            }
            test(n, resultTemp, result);
        }
    }


//  private static int findK(int[][] values,
//                           int[][] leftAndRight,
//                           int k) {
//    if (left >= right) {
//      return -1;
//    }
//    int leftTemp = left;
//    int rightTemp = right;
//    int initial = values[left];
//    while (leftTemp < rightTemp) {
//      while (leftTemp < rightTemp && values[rightTemp] >= initial) {
//        rightTemp--;
//      }
//      // swap
//      if (leftTemp < rightTemp) {
//        int temp = values[rightTemp];
//        values[rightTemp] = values[leftTemp];
//        values[leftTemp] = temp;
//      }
//
//      while (leftTemp < rightTemp && values[leftTemp] <= initial) {
//        leftTemp++;
//      }
//      // swap
//      if (leftTemp < rightTemp) {
//        int temp = values[rightTemp];
//        values[rightTemp] = values[leftTemp];
//        values[leftTemp] = temp;
//      }
//    }
//
//    if (leftTemp == k - 1) {
//      return values[leftTemp];
//    }
//
//    int result = findK(values, left, leftTemp, k);
//    if (result != -1) {
//      return result;
//    }
//    result = findK(values, leftTemp + 1, right, k);
//    if (result != -1) {
//      return result;
//    }
//
//    return -1;
//  }

    public static List<List<Integer>> threeSum2(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        sort(nums, 0, nums.length - 1);
        for (int k = 0; k < nums.length; ++k) {
            if (nums[k] > 0) break;
            int target = 0 - nums[k];
            int i = k + 1, j = nums.length - 1;
            while (i < j) {
                if (nums[i] + nums[j] == target) {
                    result.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    while (i < j && nums[i] == nums[i + 1]) ++i;
                    while (i < j && nums[j] == nums[j - 1]) --j;
                    ++i;
                    --j;
                } else if (nums[i] + nums[j] < target) ++i;
                else --j;
            }
        }
        return new ArrayList<>(result);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            List<Integer> arr = new ArrayList<>();
            treeSum(nums, 1, 0, i, arr, result);
        }

        return new ArrayList<>(result);
    }

    private static void treeSum(int[] nums, int path, int value, int index, List<Integer> oneResult, Set<List<Integer>> results) {
        if (path == 3) {
            for (int i = index; i < nums.length; i++) {
                if (nums[i] + value == 0) {
                    oneResult.add(nums[i]);
                    oneResult.sort(Integer::compareTo);
                    results.add(oneResult);
                    break;
                }
            }
        } else {
            oneResult.add(nums[index]);
            value = nums[index] + value;
            for (int j = index + 1; j < nums.length - (2 - path); j++) {
                treeSum(nums, path + 1, value, j, new ArrayList<>(oneResult), results);
            }
        }

    }

    private static List<List<Integer>> find(int n) {
        List<Integer> resultTemp = new ArrayList<>();
        List<List<Integer>> results = new ArrayList<>();
        find(0, 0, 1, n, n, resultTemp, results, 2*n);
        return results;
    }

    private static void find(int zeroNumbers,
                             int oneNumbers,
                             int deepth,
                             int zeroLeft,
                             int oneLeft,
                             List<Integer> resultTemp,
                             List<List<Integer>> results,
                             int maxDeepth) {
        // 没到深度已经使用完0或者1
        if ((zeroLeft < 0 || oneLeft < 0) && deepth != maxDeepth) {
            return;
        }
        // 达到深度并且符合条件
        if (deepth == maxDeepth) {
            if (zeroLeft == 1) {
                resultTemp.add(0);
                results.add(resultTemp);
            } else if (oneLeft == 1) {
                resultTemp.add(1);
                results.add(resultTemp);
            }
        } else {
            if (zeroNumbers == oneNumbers) {
                zeroNumbers = zeroNumbers + 1;
                zeroLeft = zeroLeft - 1;
                resultTemp.add(0);
                find(zeroNumbers, oneNumbers, deepth + 1, zeroLeft, oneLeft, resultTemp, results, maxDeepth);
            } else if (zeroNumbers > oneNumbers) {
                List<Integer> resultTemp2 = new ArrayList<>(resultTemp);
                resultTemp2.add(0);
                find(zeroNumbers + 1, oneNumbers, deepth + 1, zeroLeft - 1, oneLeft, resultTemp2, results, maxDeepth);

                List<Integer> resultTemp3 = new ArrayList<>(resultTemp);
                resultTemp3.add(1);
                find(zeroNumbers, oneNumbers + 1, deepth + 1, zeroLeft, oneLeft - 1, resultTemp3, results, maxDeepth);
            }
        }
    }

    public static void main(String[] args) {
//        int[] values = new int[]{5, 4, 4, 6, 7, 8, 9, 1, 2, 3, 10, 1};
//        sort(values, 0, values.length - 1);
//
//        for (int value : values)
//            System.out.println(value);
//
//        int[] values1 = new int[]{99, 99};
//        int result = findK(values1, 0, values1.length - 1, values1.length - 1 + 1);
//        System.out.println(result);
//
//        int[] values2 = new int[]{5, 4, 4, 6, 7, 8, 9, 1, 2, 3, 10, 1};
//        int middle = findMiddle2(values2, 0, values2.length - 1);
//
//        int[] values3 = new int[]{5, 4, 4, 6, 7, 8, 9, 1, 2, 3, 10, 1};
//        int middle3 = findMiddle3(values3, 0, values3.length - 1);
//        List<List<Integer>> result = threeSum(new int[]{14, 4, 6, -1, 10, 9, -8, 7, -13, 14, -13, -11, -8, -9, 11, 14, -8, -14, -13, 7, -10, -15, -13, -11, -11, 11, 14, 13, 2, -14, 1, -7, -2, 14, -1, -15, 9, 7, -1, 3, 6, 1, 7, 5, -1, -5, 4, -2, -4, -1, -9, -7, -1, -7, -11, 3, 12, 10, -7, -1, 12, 1, 8, -13, 1, 14, 9, -13, 6, -7, -3, -11, 2, -11, 10, -14, -1, -9, 0, 2, 5, 6, 3, -11, 6, 7, 0, 3, 3, 0, -12, -8, -13, 3, -14, -5, 2, 10, -11, -14, -12, 1, -10, 5, 5, 7, -1, 11, 14, 6, -10, -4, -3, 8, -7, 10, 1, 8, -1, -11, -15, -6, -12, -13, 12, -11});

//        int result = longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});

        test(3, new ArrayList<>(), new ArrayList<>());

//        var result = find(2);
    }
}
