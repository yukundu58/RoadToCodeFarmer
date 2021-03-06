class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i + 2 < nums.length; i++) {
            int temp = target - nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] < temp) {
                    count += k - j;
                    j++;
                }
                else {
                    k--;
                }
            }
        }
        return count;
    }
}
