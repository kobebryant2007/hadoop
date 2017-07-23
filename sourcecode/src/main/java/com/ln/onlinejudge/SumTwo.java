package com.ln.onlinejudge;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by n on 2017/7/6.
 */
public class SumTwo {
    public static void main(String[] args){
        String s = "bbtablud";
        int i = lengthOfLongestSubstring(s);
        int[] num1 = {1,2};
        int[] num12 = {1,2,3};
        double j = findMedianSortedArrays(num1,num12);
        System.out.println(j);
    }
    public int[] twoSum(int[] nums, int target) {
        if(nums == null)
            return null;
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++){
            int temp = target - nums[i];
            for(int j = i + 1; j < nums.length; j++){
                if(temp == nums[j]){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = 0;
        ListNode ln = new ListNode(0);
        ln.next = l1;
        while(l1.next != null && l2.next != null){
            int i = (l1.val + l2.val + temp)%10;
            temp = (l1.val + l2.val + temp)/10;
            l1.val = i;
            l1 = l1.next;
            l2 = l2.next;
        }
        int j = (l1.val + l2.val + temp)%10;
        temp = (l1.val + l2.val + temp)/10;
        l1.val = j;
        if (l1.next == null && l2.next == null){
            if(temp > 0){
                ListNode end = new ListNode(temp);
                l1.next = end;
            }
            return ln.next;
        }

        if (l1.next != null){
            l1 = l1.next;
            while(l1.next != null){
                int i = (l1.val + temp)%10;
                temp = (l1.val + temp)/10;
                l1.val = i;
                l1 = l1.next;
                if (temp == 0)
                    break;
            }
            int j1 = (l1.val + temp)%10;
            temp = (l1.val + temp)/10;
            l1.val = j1;
            if(temp > 0){
                ListNode end = new ListNode(temp);
                l1.next = end;
            }
            return ln.next;
        }

        if (l2.next != null){
            l1.next = l2.next;
            l1 = l1.next;
            while(l1.next != null){
                int i = (l1.val + temp)%10;
                temp = (l1.val + temp)/10;
                l1.val = i;
                l1 = l1.next;
                if (temp == 0)
                    break;
            }
            int j2 = (l1.val + temp)%10;
            temp = (l1.val + temp)/10;
            l1.val = j2;
            if(temp > 0){
                ListNode end = new ListNode(temp);
                l1.next = end;
            }
            return ln.next;
        }

        return null;
    }

    public static int lengthOfLongestSubstring(String s) {
        int position = 0;
        int length = 1;
        int result = 0;
        if(s.equals(""))
            return 0;
        if(s.length() == 1)
            return 1;
        for (int i = 1; i < s.length(); i++){
            if(position > s.length() - 1)
                break;
            if (s.substring(position, i).contains(s.charAt(i) + "")){
                position = s.substring(position, i).indexOf(s.charAt(i)) + position + 1;
                if (result < length){
                    result = length;
                    length = 1;
                }
            }else {
                length = i - position + 1;
            }
        }
        if (result < length){
            result = length;
        }
        return result;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;
        int length = nums1.length + nums2.length;
        int[] temp = new int[length];
        int lenOfNum1 = 0;
        int lenOfNum2 = 0;
        int count = 0;
        while(lenOfNum1 < nums1.length && lenOfNum2 < nums2.length){
            if(nums1[lenOfNum1] > nums2[lenOfNum2]){
                temp[count++] = nums2[lenOfNum2++];
            }else if (nums1[lenOfNum1] < nums2[lenOfNum2]){
                temp[count++] = nums1[lenOfNum1++];
            }else {
                temp[count++] = nums2[lenOfNum2++];
                temp[count++] = nums1[lenOfNum1++];
            }
            if(length % 2 != 0 && count == length/2 + 1){
                return temp[length/2];
            }
            if(length % 2 == 0 && count == length/2 + 1){
                return ( new Double(temp[length/2 - 1]) + new Double(temp[length/2]))/2;
            }
        }
        while(lenOfNum1 < nums1.length){
            temp[count++] = nums1[lenOfNum1++];
            if(length % 2 != 0 && count == length/2 + 1){
                return temp[length/2];
            }
            if(length % 2 == 0 && count == length/2 + 1){
                return ( new Double(temp[length/2 - 1]) + new Double(temp[length/2]))/2;
            }
        }

        while(lenOfNum2 < nums2.length){
            temp[count++] = nums2[lenOfNum2++];
            if(length % 2 != 0 && count == length/2 + 1){
                return temp[length/2];
            }
            if(length % 2 == 0 && count == length/2 + 1){
                return ( new Double(temp[length/2 - 1]) + new Double(temp[length/2]))/2;
            }
        }
        if(length % 2 != 0){
            return temp[length/2];
        }
        if(length % 2 == 0){
            return ( new Double(temp[length/2 - 1]) + new Double(temp[length/2]))/2;
        }
        return result;
    }
}
//Definition for singly-linked list.
 class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
