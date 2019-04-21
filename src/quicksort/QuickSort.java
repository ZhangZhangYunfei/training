package quicksort;

import java.util.ArrayList;
import java.util.List;

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
        if (left >= right && values.length > 1) {
            return -1;
        }
        if (left > right && values.length == 1) {
            return -1;
        }

        int leftTemp = findMiddle(values, left, right);

        if (leftTemp == k - 1) {
            return values[leftTemp];
        }


        return leftTemp >= k
                ? findK(values, left, leftTemp, k)
                : findK(values, leftTemp + 1, right, k);
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

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            List<Integer> arr = new ArrayList<>();
            treeSum(nums, 1, 0, i, arr, result);
        }

        return result;
    }

    private static void treeSum(int[] nums, int path, int value, int index, List<Integer> oneResult, List<List<Integer>> results) {
        if (path == 3) {
            for (int i = index; i < nums.length; i++) {
                if (nums[i] + value == 0) {
                    oneResult.add(nums[i]);
                    results.add(oneResult);
                    break;
                }
            }
        } else {
            oneResult.add(nums[index]);
            value = nums[index] + value;
            for (int j = index + 1; j < nums.length - 1; j++) {
                treeSum(nums, path + 1, value, j, new ArrayList<>(oneResult), results);
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
        int[] values1 = new int[]{99, 99};
        int result = findK(values1, 0, values1.length - 1, values1.length - 1 + 1);
//        System.out.println(result);
//
//        int[] values2 = new int[]{5, 4, 4, 6, 7, 8, 9, 1, 2, 3, 10, 1};
//        int middle = findMiddle2(values2, 0, values2.length - 1);
//
//        int[] values3 = new int[]{5, 4, 4, 6, 7, 8, 9, 1, 2, 3, 10, 1};
//        int middle3 = findMiddle3(values3, 0, values3.length - 1);
//        var result = threeSum(new int[]{-1, 0, 1, 2, -1, -4});

    }
}
