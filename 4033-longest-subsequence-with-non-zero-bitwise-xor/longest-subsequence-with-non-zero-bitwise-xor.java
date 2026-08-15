class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        // If total XOR is non-zero, take the whole array.
        if (xor != 0) {
            return nums.length;
        }

        // Total XOR is zero.
        // If there is any non-zero element, removing it makes
        // the XOR non-zero, giving a subsequence of length n - 1.
        for (int num : nums) {
            if (num != 0) {
                return nums.length - 1;
            }
        }

        // All elements are zero, so every subsequence has XOR 0.
        return 0;
    }
}