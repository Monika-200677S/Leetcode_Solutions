import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMask = new HashMap<>();

        // Build a bitmask per row for seats 2..9 (bit i = seat i+2), 8 bits total: bits 0-7
        for (int[] rs : reservedSeats) {
            int row = rs[0];
            int seat = rs[1];
            if (seat < 2 || seat > 9) continue; // seats 1 and 10 don't block any group
            int bit = 1 << (seat - 2);
            rowMask.merge(row, bit, (a, b) -> a | b);
        }

        int leftMask  = 0b00001111; // seats 2,3,4,5 -> bits 0-3
        int midMask   = 0b00111100; // seats 4,5,6,7 -> bits 2-5
        int rightMask = 0b11110000; // seats 6,7,8,9 -> bits 4-7

        long totalGroups = 0;

        for (int mask : rowMask.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean midFree = (mask & midMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;

            if (leftFree && rightFree) {
                totalGroups += 2; // left and right blocks don't overlap
            } else if (leftFree || midFree || rightFree) {
                totalGroups += 1;
            }
            // else 0 groups for this row
        }

        long rowsWithReservations = rowMask.size();
        long emptyRows = n - rowsWithReservations;
        totalGroups += emptyRows * 2L; // fully empty rows always fit 2 groups

        return (int) totalGroups;
    }
}