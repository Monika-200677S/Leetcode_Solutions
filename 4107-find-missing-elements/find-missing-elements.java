class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        
        List<Integer> ans= new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
int min = Integer.MAX_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            set.add(num);
        }
        for(int i = min;i<=max;i++){
           if(!set.contains(i)){
            ans.add(i);
           }
        }
return ans;
    }
}