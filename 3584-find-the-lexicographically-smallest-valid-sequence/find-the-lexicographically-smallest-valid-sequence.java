class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        // suf[i] = smallest j such that word2[j:] can be matched exactly
        // as a subsequence of word1[i:]
        int[] suf = new int[n + 1];
        suf[n] = m;
        int j = m;
        for (int i = n - 1; i >= 0; i--) {
            if (j > 0 && word1.charAt(i) == word2.charAt(j - 1)) {
                j--;
            }
            suf[i] = j;
        }

        int[] res = new int[m];
        int i = 0;
        j = 0;
        boolean usedChange = false;

        while (j < m && i < n) {
            if (word1.charAt(i) == word2.charAt(j)) {
                res[j] = i;
                i++;
                j++;
            } else if (!usedChange && suf[i + 1] <= j + 1) {
                // spend the one allowed change here
                res[j] = i;
                i++;
                j++;
                usedChange = true;
            } else {
                i++;
            }
        }

        if (j < m) {
            return new int[0]; // no valid sequence
        }
        return res;
    }
}