package com.xsp.Lock.ReentrantLock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {

    public static String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxlength = 0;
        int begin = 0;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            // 初始化赋值 单个字母有回文串
        }
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i < s.length(); i++) {

                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if (dp[i][j] && maxlength <= j - i + 1) {
                        maxlength = j - i + 1;
                        begin = i;
                    }
                }
            }
        }

        return s.substring(begin, begin + maxlength);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <= 2) return ans;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return ans;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int left = i+1;
            int right = nums.length - 1;
            while (left < right) {
                int result = nums[i] + nums[left] + nums[right];
                if (result == 0) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    right--;
                    while (left < right&&nums[left] == nums[left - 1])left++;
                    while (left < right&&nums[right] == nums[right + 1])right--;
                } else if (result > 0) {
                    right--;
                } else {
                    left++;
                }
            }
            ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
            ThreadPoolExecutor executor = new ThreadPoolExecutor(3,30,10000L, TimeUnit.MILLISECONDS,arrayBlockingQueue);
            executor.execute(new Thread(() -> System.out.println("1111")));
            executor.shutdown();
            try {
                boolean bootable;
                do {
                    bootable = executor.awaitTermination(2, TimeUnit.MILLISECONDS);
                }while (bootable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return ans;
    }
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left+((right-left)>>1);
            if(nums[mid] == target){
                return mid;
            }
            if(nums[left]<=nums[mid]){
                if(nums[mid]>target && nums[left]<=target){
                    right = mid-1;
                }else{
                    left = mid +1;
                }
            }else{
                if(nums[mid] < target && target <= nums[right]){
                    left=mid+1;
                }else{
                    right= mid-1;
                }
            }
        }
        return -1;
    }

    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        if(nums.length<1){
            return 0;
        }
        if(nums.length<2){
            return nums[0];
        }

        while(left<=right){
            int mid = left+((right-left)>>1);
            if(mid+1>nums.length-1||mid-1<0){
                return Math.min(nums[left],nums[right]);
            }
            if(nums[mid]<nums[mid+1]&&nums[mid]<nums[mid-1]){
                return nums[mid];
            }
            if(nums[left]<=nums[mid]){
                if(nums[left]<=nums[right]){
                    return nums[left];
                }else{
                    left = mid+1;
                }
            }else{
                if(nums[left]>nums[right]){
                    right = mid-1;
                }else{
                    left = mid+1;

                }

            }
        }
        return nums[left];
    }
    public List<List<Integer>> threeSum1(int[] nums) {// 总时间复杂度：O(n^2)
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <= 2) return ans;

        Arrays.sort(nums); // O(nlogn)

        for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
            if (nums[i] > 0) break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去掉重复情况
            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));

                    // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    left++;
                    right--; // 首先无论如何先要进行加减操作
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {  // nums[left] + nums[right] > target
                    right--;
                }
            }
        }
        return ans;
    }


    static ReentrantReadWriteLock a = new ReentrantReadWriteLock();
    public static void main(String[] args) {
        ReentrantReadWriteLock.WriteLock writeLock = a.writeLock();
        writeLock.lock();

    }
}