package longestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Path {
    private static int longest = 0;

    private static int maximumPath(TreeNode node) {
        if (node == null) return 0;

        int left = maximumPath(node.left);
        int right = maximumPath(node.right);

        int currentLeft = 0;
        int currentRight = 0;
        if (node.left != null && node.val == node.left.val) {
            currentLeft = left + 1;
        }

        if (node.right != null && node.val == node.right.val) {
            currentRight = right + 1;
        }

        longest = Math.max(longest, currentLeft + currentRight);

        return Math.max(currentLeft, currentRight);
    }

    private static TreeNode construct(int... values) {
        TreeNode nodeTemp = null;
        TreeNode leftTemp = null;
        TreeNode rightTemp = null;
        for (int i = 0; i < values.length; i = i + 3) {
            int value = values[i];

            TreeNode leftNode = leftTemp;
            if (i + 1 < values.length) {
                int left = values[i + 1];
                leftNode = new TreeNode(left);
            }

            TreeNode rightNode = rightTemp;
            if (i + 2 < values.length) {
                int right = values[i + 2];
                rightNode = new TreeNode(right);
            }

            TreeNode node = new TreeNode(value);
            node.left = leftNode;
            node.right = rightNode;

            if (leftTemp == null) {
                leftTemp = node;
            } else if (rightTemp == null) {
                rightTemp = node;
            }
            nodeTemp = node;
        }
        return nodeTemp;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> current = Arrays.asList(root);

        boolean fromRight = false;
        while (!current.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            List<Integer> integers = new ArrayList<>();
            for (TreeNode node : current) {
                integers.add(node.val);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }

            current = next;
            if (fromRight) {
                Collections.reverse(integers);
            }
            result.add(integers);
            fromRight = !fromRight;


        }

        return result;
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> current = Arrays.asList(root);

        while (!current.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            List<Integer> integers = new ArrayList<>();
            for (TreeNode node : current) {
                integers.add(node.val);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            current = next;
            result.add(integers);
        }

        return result;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        }

        return right;
    }


    public static void main(String[] args) {
        System.out.println(maximumPath(construct(1, 1, 3, 4, 1, 2, 1)));
        levelOrder(construct(1, 2, 3, 4, 5, 6, 7));
    }
}
