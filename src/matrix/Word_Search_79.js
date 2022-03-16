var exist = function(board, word) {
    const row = board.length;
    const col = board[0].length;
    const directions= [[0,1],[1,0],[0,-1],[-1,0]];
    const visited = new Array(row).fill(null).map(row=> new Array(col));
    
    const DFS =( index, x,y) => {
        
        if( x<0 || x===row || 
            y<0 || y===col || 
            word[index] !== board[x][y] || 
            visited[x][y]){
            return false;
            
        } else if( index === word.length-1){
            return true;
        }
        
        visited[x][y] = true; //in progress
        
        // recursion
        for (let [i, j] of directions){
            if(DFS(index+1, x+i, y+j)){
                return true;
            }   
        }
        
        visited[x][y] = false; // done
    }
    
    for(let i=0; i<row; i++){
        for(let j=0; j<col; j++){    
            if(DFS(0, i,j)){
                return true;
            }
        }
    }
     return false;
};