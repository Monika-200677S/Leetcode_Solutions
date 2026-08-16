class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int s : stones) {
            cnt[s % 3]++;
        }

        if (cnt[0] % 2 == 0) {
            // Alice needs both types 1 and 2 available to avoid being forced into a losing move
            return cnt[1] > 0 && cnt[2] > 0;
        } else {
            // With an odd count of multiples of 3, the parity flips —
            // Alice wins if the counts of type1/type2 are sufficiently imbalanced
            return Math.abs(cnt[1] - cnt[2]) > 2;
        }
    }
}