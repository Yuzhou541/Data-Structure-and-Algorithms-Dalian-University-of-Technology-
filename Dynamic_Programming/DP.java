package Dynamic_Programming;

import java.util.Arrays;

public class DP {
    public static void main(String[] args) {

        // Max_subarray
        System.out.println("MaxsubArray: ");
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Brute Force: " + new DP().maxSubArrayBruteForce(nums));
        System.out.println("Divide and Conquer: " + new DP().maxSubArrayDivideAndConquer(nums));
        System.out.println("Dynamic Programming: " + new DP().maxSubArrayDP(nums));

        // Climbing Step
        System.out.println("Climbing Step: ");
        int n = 5; 
        System.out.println("Number of ways to climb " + n + " stairs: " + new DP().climbStairs(n));

        // Grid
        System.out.println("Grid: ");
        int[][] grid = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        System.out.println("Unique Paths: " + new DP().uniquePathsWithObstacles(grid)); 

        // Dungeon Game
        System.out.println("Dungeon Game: ");
        int[][] dungeon = {
            {-2, -3, 3},
            {-5, -10, 1},
            {10, 30, -5}
        };
        System.out.println("Minimum Initial Health: " + new DP().calculateMinimumHP(dungeon));

        // Rod Cutting
        System.out.println("Rod Cutting: ");
        int[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}; // start in index 1
        int nr = 8; 
        System.out.println("Top-Down with Memoization: " + new DP().rodCuttingTopDown(prices, nr));
        System.out.println("Bottom-Up Dynamic Programming: " + new DP().rodCuttingBottomUp(prices, nr));

        // Permutation & Combination
        System.out.println("Permutation and Combination: ");
        System.out.println("Permutations:");
        StringBuffer orig1 = new StringBuffer("ABC");
        StringBuffer dest1 = new StringBuffer("   "); 
        new DP().permutation(orig1, dest1, 0);
        System.out.println("Combinations:");
        StringBuffer orig2 = new StringBuffer("ABC");
        StringBuffer dest2 = new StringBuffer("  "); 
        new DP().combination(orig2, dest2, 0);

        // Coin Change
        System.out.println("Coin change: ");
        int[] coins = {1, 2, 5};
        int amount = 11;
        int result = new DP().coinChange(coins, amount);
        System.out.println("Minimum coins needed: " + result);

        // TSP
        System.out.println("TSP: ");
        int[][] distance = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        int resultTSP = new DP().tsp(distance);
        System.out.println("The minimum cost is: " + resultTSP);
    }

    // maxSubArray
    int maxSubArrayBruteForce(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }
    int maxSubArrayDivideAndConquer(int[] nums) {
        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }
    int maxSubArrayHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;

        int leftMax = maxSubArrayHelper(nums, left, mid);
        int rightMax = maxSubArrayHelper(nums, mid + 1, right);
        int crossMax = maxCrossingSum(nums, left, mid, right);

        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }
    int maxCrossingSum(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE, rightSum = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        return leftSum + rightSum;
    }
    int maxSubArrayDP(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        for(int i = 1; i < nums.length; i++){
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    // Climbing step
    int climbStairs(int n) {
        if(n <= 1)  return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Grid
    int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        if(grid[0][0] == 1 || grid[m - 1][n - 1] == 1)  return 0;
        dp[0][0] = 1;
        for(int i = 1; i < m; i++){
            dp[i][0] = (grid[i][0] == 0 && dp[i - 1][0] == 1) ? 1 : 0;
        }
        for(int i = 1; i < n; i++){
            dp[0][i] = (grid[0][i] == 0 && dp[0][i - 1] == 1) ? 1 : 0;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(grid[i][j] == 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // Dungeon Game
    int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = Math.max(1, 1 - dungeon[m-1][n-1]);
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n-1] = Math.max(1, dp[i+1][n-1] - dungeon[i][n-1]);
        }
        for (int j = n - 2; j >= 0; j--) {
            dp[m-1][j] = Math.max(1, dp[m-1][j+1] - dungeon[m-1][j]);
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int down = Math.max(1, dp[i+1][j] - dungeon[i][j]); 
                int right = Math.max(1, dp[i][j+1] - dungeon[i][j]); 
                dp[i][j] = Math.min(down, right); 
            }
        }
        return dp[0][0];
    }

    // Rod Cutting
    int rodCuttingTopDown(int[] prices, int n) {
        int[] memo = new int[n + 1]; 
        for (int i = 0; i <= n; i++) memo[i] = -1; 
        return topDownHelper(prices, n, memo);
    }
    int topDownHelper(int[] prices, int n, int[] memo) {
        if (n == 0) return 0; 
        if (memo[n] != -1) return memo[n]; 

        int maxRevenue = 0;
        for (int i = 1; i <= n; i++) {
            maxRevenue = Math.max(maxRevenue, prices[i] + topDownHelper(prices, n - i, memo));
        }
        memo[n] = maxRevenue; 
        return maxRevenue;
    }
    int rodCuttingBottomUp(int[] prices, int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0; 
        for (int i = 1; i <= n; i++) {
            int maxRevenue = 0;
            for (int j = 1; j <= i; j++) {
                maxRevenue = Math.max(maxRevenue, prices[j] + dp[i - j]);
            }
            dp[i] = maxRevenue;
        }
        return dp[n];
    }

    // Permutation
    void permutation(StringBuffer orig, StringBuffer dest, int pos) {
        if (pos == dest.length()) {
            System.out.println(dest);
            return;
        }
        for (int i = 0; i < orig.length(); i++) {
            dest.setCharAt(pos, orig.charAt(i)); 
            orig.deleteCharAt(i);               
            permutation(orig, dest, pos + 1);   
            orig.insert(i, dest.charAt(pos));  
        }
    }
    // Combination
    void combination(StringBuffer orig, StringBuffer dest, int pos) {
        if (pos == dest.length()) {
            System.out.println(dest);
            return;
        }
        while (orig.length() != 0) {
            dest.setCharAt(pos, orig.charAt(0));                  
            orig.deleteCharAt(0);                                   
            combination(new StringBuffer(orig), dest, pos + 1);     
        }
    }

    // Coin change
    int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int coin : coins){
            for(int i = coin; i <= amount; i++){
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount];
    }    

    // TSP
    int tsp(int[][] distance) {
        int n = distance.length;
        int[][] dp = new int[1 << n][n]; 
        int INF = Integer.MAX_VALUE / 2; 
        for (int mask = 0; mask < (1 << n); mask++) {
            for (int i = 0; i < n; i++) {
                dp[mask][i] = INF;
            }
        }
        dp[1][0] = 0; 
        for (int mask = 1; mask < (1 << n); mask++) { 
            for (int i = 0; i < n; i++) {            
                if ((mask & (1 << i)) == 0) continue; 
                for (int j = 0; j < n; j++) {        
                    if ((mask & (1 << j)) != 0) continue; 
                    int nextMask = mask | (1 << j);   
                    dp[nextMask][j] = Math.min(dp[nextMask][j], dp[mask][i] + distance[i][j]);
                }
            }
        }
        int result = INF;
        for (int i = 1; i < n; i++) {
            result = Math.min(result, dp[(1 << n) - 1][i] + distance[i][0]);
        }
        return result;
    }
}
