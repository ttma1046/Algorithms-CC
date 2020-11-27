public class Set_K_Mins_in_mn_grid {
    public static void main(String[] args) {
        int[][] mines = generateMines(4, 5, 10);
        for(int[] m: mines)
            System.out.println(Arrays.toString(m));
    }
    static int[][] generateMines(int r, int c, int newMines){
        int[][] mines = new int[r][c];
        if(newMines > r*c){ //edge case 
            for(int[] a: mines) Arrays.fill(a, 1);
            return mines;
        }
        Random rand = new Random();
        
        // reservoir of all possible positions
        Set<Integer> keys = new HashSet<>();
        for(int i=0; i<r*c; i++) keys.add(i);
        
        while(newMines>0){
            int index = rand.nextInt(keys.size());
            if(keys.contains(index)){ // if I didn't use the index
                
                // mine it..
                mines[getRow(index, c)][getCol(index, c)] = 1;
                newMines--;
                keys.remove(index); // remove fromt he reservoir
            }
        }
        return mines;
    }
    static int getRow(int index, int col){
        return index / col;
    }
    static int getCol(int index, int col){
        return index % col;
    }
}

/*
const test = (A, k)=>{
    let selected =[];
    let totalLength = A.length*A[0].length
    for(let i = 0; i<k;i++){
        selected.push(i);        
    }

    for(let i = k;i<totalLength;i++){
        const toBeRemoved = parseInt(Math.random()*k);
        const shouldAdd =  parseInt(Math.random(0,1)*(i+1)) < k;
         

        if(shouldAdd){
          let row = parseInt(i/A[0].length);
          let col = i%A[0].length;
          selected[toBeRemoved] = A[row][col]
        }
    }
    return selected;
}
*/