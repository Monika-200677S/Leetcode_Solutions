class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {

            // Add nums[right] to the window
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            // If nums[right] appears more than k times,
            // move left until the window becomes valid
            while (freq.get(nums[right]) > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                left++;
            }

            // Current window is [left ... right]
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}