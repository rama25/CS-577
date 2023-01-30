import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class IntervalScheduling {
  private static int arrangeJob(String[] instance) {
    int[][] Time = new int[instance.length][2];
    for(int i = 0; i < instance.length; i++) {
      Time[i][0] = Integer.parseInt(instance[i].trim().split(" ")[0]);
      Time[i][1] = Integer.parseInt(instance[i].trim().split(" ")[1]);
    }
    
    Arrays.sort(Time, new Comparator<int[]>() {
      @Override
      public int compare(int[] entry1, int[] entry2) {
        if (entry1[1] > entry2[1]) {
          return 1;
        } else {
          return -1;
        }
      }
     });
    
    int count = 1; 
    int idx = 1;  
    int countInIdx = 0; 
    while(idx < Time.length) {
      if(Time[countInIdx][1] <= Time[idx][0]) {
        count++;
        countInIdx = idx;
      }
      idx++;
    }
    return count;
  }
  
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String linesString = scan.nextLine().trim();
    int numInstance = Integer.parseInt(linesString);
    ArrayList<String[]> instancesList = new ArrayList<String[]>(numInstance); 
    for(int i = 1; i <= numInstance; i++) {   
      linesString = scan.nextLine().trim();
      int numJobs = Integer.parseInt(linesString);
      String[] instance = new String[numJobs]; 
      int jobId = 0;
      while(jobId < numJobs) {
        String jobString = scan.nextLine().trim();
        instance[jobId] = jobString;
        jobId++;
      }
      instancesList.add(instance);
    }
    for(int i = 0; i < instancesList.size(); i++) {   
      int countNum = arrangeJob(instancesList.get(i));
      System.out.println(countNum);
    } 
  }
}
