// 前三个都是backtrack枚举。
// 1. combination问题中，for loop需要start这个参数，因为是combination，不能每次都从0开始。permutation问题不需要start，每次从头开始枚举。
// 2. 如果给定nums中的元素能重复使用，recursion里的start还是从i开始，比如I，如果不能重复使用，则要从i+1开始，比如II,III。
// 3. recursion一开始，先设置好递归的退出条件。
// 4. temp.remove(temp.size() - 1)， 返回上一级recursion，所以要移除当前level递归加入的元素
// 5. 如果给定的set（nums）里面有重复元素，但是res里的list又不能有重复组合（某一个list里，可以有重复元素），需要sort nums。注意u第二点的区别。

// 39. Combination Sum.java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, res, new ArrayList<Integer>(), target, 0);// Permutations问题没有start/position这个参数，因为顺序matters，combination里面需要start/position，因为顺序改了算重复。不需要visited，因为可以有重复元素。
        return res;
    }
    private void backtrack(int[] candidates, List<List<Integer>> res, List<Integer> temp, int target, int start) {
        if (target == 0) { // target随加入的candidate改变，退出条件是target == 0，这样就不用检查list的元素和是target。
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target >= candidates[i]) { // target >= candidates[i]才能加，避免检查target < 0 的情况。
                temp.add(candidates[i]);
                backtrack(candidates, res, temp, target - candidates[i], i); // 由于set中的元素可以重复使用多次，所以recursio还是从i开始
                temp.remove(temp.size() - 1); // 返回上一级recursion，所以要移除当前level递归加入的元素
            }
        }
    }
}
//没有重复结果是由Combination，在recursion里i从start开始，而不是从0开始保证的。
//与Combination Sum 2的区别还在于这个题输入是没有重复元素的，所以不用sort，然后检查if (i > start && candidates[i] == candidates[i - 1])。

// 40. Combination Sum II
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 必须sort，下面才能判断candidates[i] == candidates[i - 1]
        combinationSum2Helper(candidates, target, res, new ArrayList<Integer>(), 0);
        return res;
    }
    private void combinationSum2Helper(int[] candidates, int target, List<List<Integer>> res, List<Integer> temp, int start) { // 由于是combination，所以需要start记录位置。
        if (target == 0) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) { // i > start && ...跳过重复，如果是permutation，则是i > 0 && ...。第一个重复元素
                continue;
            }
            if (target >= candidates[i]) {
                temp.add(candidates[i]);
                combinationSum2Helper(candidates, target - candidates[i], res, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
// 给定的set（nums）里面有重复元素，但是res里的list又不能有重复组合（某一个list里，可以有重复元素）。
// 比如
// [2,1,2,7,6,1,2,3,4,2]
// 7
// 结果是 [[1,1,2,3],[1,2,2,2],[1,2,4],[1,6],[2,2,3],[3,4],[7]]
// [2,1,2,7,6,1,2,3,4,2] 排完序是[1,1,2,2,2,2,3,4,6,7]
// 如果list中有重复的元素，那么他们是在不同层recursion加进去的。同一个层的递归不会出现重复元素。
// 比如[1,2,2,2]，三个2分别是不同level的recursion加进去的。

// 216. Combination Sum III
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(k, n, res, new ArrayList<>(), 1);
        return res;
    }
    
    private void dfs(int k, int n, List<List<Integer>> res, List<Integer> temp, int start) {
        if (temp.size() == k) { // size == k一定退出
            if (n == 0) { // 只有n == 0才加入res
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (i <= n) {
                temp.add(i);
                dfs(k, n - i, res, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
}

// 377. Combination Sum IV.java
class Solution {   
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
/*
The problem with negative numbers is that now the combinations could be potentially of infinite length. Think about nums = [-1, 1] and
target = 1. We can have all sequences of arbitrary length that follow the patterns -1, 1, -1, 1, ..., -1, 1, 1 and
1, -1, 1, -1, ..., 1, -1, 1 (there are also others, of course, just to give an example). So we should limit the length of the combination
sequence, so as to give a bound to the problem.
*/
// https://leetcode.com/problems/combination-sum-iv/discuss/85038/JAVA:-follow-up-using-recursion-and-memorization.
// use hashmap and recursion.
