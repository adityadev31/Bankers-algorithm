import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        int p=5, r=4;
        
        int[][] alloc = {   // int[p][r] alloc
            {0,0,1,2},
            {1,0,0,0},
            {1,3,5,4},
            {0,6,3,2},
            {0,0,1,4}
        };
        
        int[][] max = {
            {0,0,1,2},
            {1,7,5,0},
            {2,3,5,6},
            {0,6,5,2},
            {0,6,5,6}
        };
        
        int[] avail = {1,5,2,0};
     
        int[][] need = new int[p][r];   
        
        int[] safeSeq = new int[p];
        
        // setting need
        for(int i=0; i<p; i++){
            for(int j=0; j<r; j++){
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }
        
        // printing matrix
        System.out.println("Alloc Matrix \t\t Max Matrix \t\t Need Matrix");
        for(int i=0; i<p; i++){
            for(int j=0; j<r; j++){ System.out.print(alloc[i][j] + " "); }
            System.out.print("\t\t ");
            for(int j=0; j<r; j++){ System.out.print(max[i][j] + " "); }
            System.out.print("\t\t ");
            for(int j=0; j<r; j++){ System.out.print(need[i][j] + " "); }
            System.out.println();
        }
        
        int[] flag = {0,0,0,0,0};
        
        // if need < avail  then allocate resources
        
        int i = 0;
        int count = 0;
        
        try {
            while(count<p){
                if(flag[i]==0){                   // untested
                    flag[i] = 1;                  // setting it to 1
                    for(int j=0; j<r; j++){
                        if(need[i][j] > avail[j]){
                            flag[i] = 0;
                            break;
                        }
                    }
                    if(flag[i]==1){           // if 1 then add it to safeSeq[]
                        safeSeq[count] = i;
                        count++;
                        for(int j=0; j<r; j++){     // also check for avail = avail + max
                            avail[j] = avail[j] + alloc[i][j];
                        }
                    }
                }
                if(i==p-1){ i=0; }
                else{ i+=1; }
            }
        } catch(Exception e) {
            System.out.println("\n\nSystem unsafe !!");
            return;
        }
        
        System.out.println("\n\nSystem is safe !!");
        for(i=0; i<p; i++){ System.out.print(safeSeq[i] + " -> "); }
    }
}
