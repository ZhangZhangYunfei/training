package list;

import java.util.HashSet;
import java.util.Set;

public class ListReverse {

    private static ListNode reverse(ListNode header) {
        if (header == null || header.next == null) {
            return header;
        }

        ListNode previous = null;
        ListNode current = header;

        while (current != null) {
            ListNode next = current.next;
            current.next = (previous);
            previous = current;
            current = next;
        }

        return previous;
    }

    private static ListNode reverse_recursive(ListNode header) {
        return swap(null, header);
    }

    private static ListNode swap(ListNode previous, ListNode current) {
        if (current == null) {
            return previous;
        }
        ListNode next = current.next;
        current.next = (previous);
        return swap(current, next);
    }

    private static ListNode reverse_recursive_stack(ListNode header) {
        if (header == null || header.next == null) {
            return header;
        }
        ListNode ListNode = reverse_recursive_stack(header.next);
        ListNode.next = (header);
        header.next = (null);
        return header;
    }

    private static ListNode reverse_stack(ListNode header) {
        return null;
    }

    private static void print(ListNode header) {
        ListNode current = header;
        while (current != null) {
            System.out.print(current.val);
            current = current.next;
        }
        System.out.println();
    }

    private static ListNode construct(int... values) {
        ListNode header = null;
        ListNode current = null;
        for (int i : values) {
            ListNode listNode = new ListNode(i);
            if (header == null) {
                header = listNode;
            }
            if (current == null) {
                current = listNode;
            } else {
                current.next = (listNode);
                current = current.next;
            }
        }
        current.next = header;
        return header;
    }

    public static ListNode plusAB(ListNode a, ListNode b) {
        ListNode current = null;
        ListNode result = null;
        int temp = 0;
        while (a != null || b != null) {
            int value = 0;
            value = value + temp;
            temp = 0;
            if (a != null) {
                value = value + a.val;
                a = a.next;
            }
            if (b != null) {
                value = value + b.val;
                b = b.next;
            }

            while (value >= 10) {
                value = value - 10;
                temp += 1;
            }

            if (result == null) {
                result = new ListNode(value);
                current = result;
            } else {
                current.next = new ListNode(value);
                current = current.next;
            }

            if (a == null && b == null && temp > 0) {
                current.next = new ListNode(temp);
            }
        }
        return result;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        ListNode result = lists[0];
        for (int i = 1; i < lists.length; i++) {
            result = mergeTwoLists(result, lists[i]);
        }
        return result;
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode result = null;
        ListNode current = null;
        while (a != null || b != null) {
            int value = 0;
            if (a != null && b != null) {
                if (a.val < b.val) {
                    value = a.val;
                    a = a.next;
                } else {
                    value = b.val;
                    b = b.next;
                }
            } else if (a != null) {
                value = a.val;
                a = a.next;
            } else {
                value = b.val;
                b = b.next;
            }

            if (result == null) {
                result = new ListNode(value);
                current = result;
            } else {
                current.next = new ListNode(value);
                current = current.next;
            }
        }
        return result;
    }

    private static ListNode sortList(ListNode head, ListNode end) {
        if (head != end && head.next != null) {
            ListNode middle = findMiddle(head, end);
            sortList(head, middle);
            sortList(middle.next, end);
            return head;
        }
        return head;
    }

    private static ListNode findMiddle(ListNode start, ListNode end) {
        ListNode head = start;
        int value = start.val;

        ListNode location = start;
        while (start != end) {
            if (start.val < value) {
                location = location.next;

                // swap start, location value only
                int temp = start.val;
                start.val = location.val;
                location.val = temp;
            }
            start = start.next;
        }
        int temp = head.val;
        head.val = location.val;
        location.val = temp;

        return location;
    }

    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode aCursor = headA;
        ListNode bCursor = headB;

        boolean aFlag = false;
        boolean bFlag = false;

        ListNode result = null;

        while (aCursor != null && bCursor != null) {
            if (aCursor.val == bCursor.val && result == null) {
                result = aCursor;
            } else if (aCursor.val != bCursor.val) {
                result = null;
            }

            if (aCursor.next == null && !aFlag) {
                aFlag = true;
                aCursor = headB;
            } else {
                aCursor = aCursor.next;
            }

            if (bCursor.next == null && !bFlag) {
                bFlag = true;
                bCursor = headA;
            } else {
                bCursor = bCursor.next;
            }
        }
        return result;
    }

    private static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode aCursor = headA;
        ListNode bCursor = headB;

        // 比较地址
        while (aCursor != bCursor) {
            if (aCursor == null) {
                aCursor = headB;
            } else {
                aCursor = aCursor.next;
            }

            if (bCursor == null) {
                bCursor = headA;
            } else {
                bCursor = bCursor.next;
            }
        }
        return aCursor;
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return null;
    }

    public static ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public static int trap(int length, int[] nums) {
        boolean prefixValue = false;
        int tempValue = 0;
        int sum = 0;
        int index = 1;
        boolean flagValue = false;
        while (true) {
            prefixValue = false;
            tempValue = 0;
            flagValue = false;
            for (int j = 0; j < length; j++) {
                if (nums[j] >= index) {
                    flagValue = true;
                    prefixValue = true;
                    sum += tempValue;
                    tempValue = 0;
                } else {
                    if (prefixValue) {
                        tempValue++;
                    }
                }
            }
            if (!flagValue) {
                break;
            }
            index++;
        }
        return sum;
    }

    int trap(int A[], int n) {
        if (n <= 2) return 0;
        int max = -1, maxInd = 0;
        int i = 0;
        for (; i < n; ++i) {
            if (A[i] > max) {
                max = A[i];
                maxInd = i;
            }
        }
        int area = 0, root = A[0];
        for (i = 0; i < maxInd; ++i) {
            if (root < A[i]) root = A[i];
            else area += (root - A[i]);
        }
        for (i = n - 1, root = A[n - 1]; i > maxInd; --i) {
            if (root < A[i]) root = A[i];
            else area += (root - A[i]);
        }
        return area;
    }

    public static int test(int[] nums) {
        int result = 0;
        int left = 0;
        for (int leftIndex = 0; leftIndex < nums.length - 1; ) {
            left = nums[leftIndex];
            int sumTemp = 0;
            int rightIndex = leftIndex + 1;
            boolean found = false;
            int max = nums[rightIndex];
            int maxSumTemp = 0;
            int maxIndex = rightIndex;
            for (; rightIndex < nums.length; rightIndex++) {
                if (nums[rightIndex] >= left) {
                    found = true;
                    break;
                }

                if (nums[rightIndex] > max) {
                    max = nums[rightIndex];
                    maxSumTemp = sumTemp;
                    maxIndex = rightIndex;
                }
                sumTemp += nums[rightIndex];
            }
            if (found) {
                result = result + (rightIndex - leftIndex - 1) * left - sumTemp;
                leftIndex = rightIndex;
            } else {
                result = result + (maxIndex - leftIndex - 1) * max - maxSumTemp;
                leftIndex = maxIndex;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // 方法一
//    print(construct(1, 2, 3, 4, 5));
//    print(reverse(construct(1, 2, 3, 4, 5)));
//    print(construct(10, 9, 8, 7, 6));
//    print(reverse(construct(10, 9, 8, 7, 6)));
//    print(construct(1));
//    print(reverse(construct(1)));
//    // 方法二
//    print(construct(1, 2, 3, 4, 5));
//    print(reverse_recursive(construct(1, 2, 3, 4, 5)));
//    print(construct(10, 9, 8, 7, 6));
//    print(reverse_recursive(construct(10, 9, 8, 7, 6)));
//    print(construct(1));
//    print(reverse_recursive(construct(1)));
        // 方法三
//        print(construct(1, 2, 3, 4, 5));
//        print(reverse_recursive_stack(construct(1, 2, 3, 4, 5)));
//        print(construct(10, 9, 8, 7, 6));
//        print(reverse_recursive_stack(construct(10, 9, 8, 7, 6)));
//        print(construct(1));
//        print(reverse_recursive_stack(construct(1)));

//        ListNode header1 = new ListNode(9);
//        header1.next = new ListNode(9);
//        header1.next.next = new ListNode(9);
//
//        ListNode header2 = new ListNode(9);
//        header2.next = new ListNode(9);
//        header2.next.next = new ListNode(9);
//
//        ListNode result = plusAB(header1, header2);
//        System.out.println(result.val);

//        int result = trap(5, new int[]{5, 2, 1, 4, 3});
//        System.out.println(result);

//        print(sortList(construct(5, 4, 4, 6, 7, 8, 9, 1, 2, 3, 10, 1), null));

//        print(getIntersectionNode2(construct(4, 1, 8, 4, 5), construct(5, 0, 1, 8, 4, 5)));

//        ListNode node = detectCycle2(construct(1, 2));
        int result1 = trap(5, new int[]{5, 2, 1, 4, 3});
//        int result = test(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        int result = test(new int[]{4, 2, 3});
    }
}
