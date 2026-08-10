class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];

        // dp[i] = true means the current player can win
        // when there are i stones remaining.

        for (int i = 1; i <= n; i++) {

            // Try removing every possible square number
            for (int j = 1; j * j <= i; j++) {

                int square = j * j;

                // If after removing square stones,
                // the opponent is in a losing position,
                // then current player wins.
                if (!dp[i - square]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}