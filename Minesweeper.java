//time: n*m; size of the board
//space:m* n;
//leetcode:yes
class Solution {
    int [][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {
     if(board == null || board.length == 0)return board;
        m = board.length;
        n = board[0].length;
        
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int []> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int [] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int mines = getMines(board, r, c);
            if(mines == 0){//then process childrens
                for(int [] dir : dirs){
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'E'){
                        q.add(new int[]{nr,nc});
                        board[nr][nc] = 'B';
                    } 
                }
            }else{
                board[r][c] = (char)(mines + '0');
            }
        }
        return board;
    }
    
    private int getMines(char [][] board, int i, int j){
        int count = 0;
        for(int [] dir: dirs){
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'M'){
                count++;
            }
        }
        return count;
    }
}