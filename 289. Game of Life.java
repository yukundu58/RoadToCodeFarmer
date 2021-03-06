class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = numOfLives(board, i, j, m, n);
                // 下一时刻的情况用board[i][j] >>= 1，如果下一状态是0，不用变，高位的0自然移到下一状态
                // 只用考虑下一状态是1的情况
                if (board[i][j] == 1 && (lives == 2 || lives == 3)) {
                    // board[i][j]改为2bits，高位代表下一状态，低位代表当前状态
                    board[i][j] = 3;
                }             
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }       
    }   
    
    private int numOfLives(int[][] board, int i, int j, int m, int n) {
        int lives = 0;
        for (int x = Math.max(0, i - 1); x <= Math.min(m -1, i + 1); x++) {
            for (int y = Math.max(0, j - 1); y <= Math.min(n - 1, j + 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1; // 不算自己的死活
        return lives;
    }
}
