class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        long lo = 1, hi = (long) Math.min(coins[0], 25) * k; // safe upper bound
        // more robust upper bound: smallest coin * k
        long minCoin = Long.MAX_VALUE;
        for (int c : coins) minCoin = Math.min(minCoin, c);
        hi = minCoin * k;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (countMultiplesUpTo(mid, coins) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    // Count numbers in [1, x] divisible by at least one coin, via inclusion-exclusion over subsets
    private long countMultiplesUpTo(long x, int[] coins) {
        int n = coins.length;
        long count = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = Integer.bitCount(mask);
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    lcm = lcmSafe(lcm, coins[i], x);
                    if (lcm == -1) { // overflow / exceeds x already
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow || lcm > x) continue;

            long term = x / lcm;
            if (bits % 2 == 1) {
                count += term;
            } else {
                count -= term;
            }
        }

        return count;
    }

    private long lcmSafe(long a, long b, long cap) {
        long g = gcd(a, b);
        long l = a / g;
        // check overflow before multiplying
        if (l > cap / b) return -1; // would exceed cap
        return l * b;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}