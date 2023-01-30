import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FartherInFuture {
  private String[] request; 
  private int idPage = 0; 
  private int cacheSize; 
  private int pageFaults = 0; 
  private int[] stepArray; 
  private Hashtable<String, Integer> hash = new Hashtable<String, Integer>();
  
  private Hashtable<String, Integer> hashCache = new Hashtable<String, Integer>(); 
  private PriorityQueue<Integer> pQueueCache = new PriorityQueue<Integer>(Collections.reverseOrder());
  public FartherInFuture(int numInCache, int numRequest) {
    this.cacheSize = numInCache;
    this.request = new String[numRequest];
    this.stepArray = new int[numRequest];
  }
  
  public void setRequestSequence(String requestSequence) {
    this.request = requestSequence.trim().split(" ");
  }
  
  public boolean isFault(String newRequest) {
    if(!hashCache.containsKey(newRequest)) {
      return true;
    }
    return false;
  }
  
  public void findStepArray() {
    for(int i = this.request.length - 1; i>=0; i--) {
      if(!hash.containsKey(this.request[i])) {
        hash.put(this.request[i], i);
        stepArray[i] = Integer.MAX_VALUE /2 ;

        
      } else {
        stepArray[i] = hash.get(this.request[i]) - i;
        hash.put(this.request[i], i);
      }
    }
  }
  
  public String findKeyBasedValue(int val) {
    for(Object o:hashCache.keySet()) {
      if(hashCache.get(o).equals(val)) {
        return o.toString();
      }
    }
    return "null";
  }
  public void getNewPageRequest() {
    if(hashCache.size() < cacheSize ) {
      if(this.isFault(request[idPage])) {
        pageFaults++;
        hashCache.put(request[idPage], stepArray[idPage] + idPage);  
        pQueueCache.add(stepArray[idPage] + idPage);
        
        
      } else { 
        Iterator<Integer> iterator = pQueueCache.iterator();
        
        while (iterator.hasNext()) {
          if((int)iterator.next() == hashCache.get(request[idPage])) {
            iterator.remove();
            break;
          }
        }
        pQueueCache.add(stepArray[idPage] + idPage);
        
        
        hashCache.put(request[idPage], stepArray[idPage] + idPage);
        
        
      }
      return;
    }
    
    if(this.isFault(request[idPage])) { 
      pageFaults++;
      
      String keyRemoved = this.findKeyBasedValue(pQueueCache.poll());
      hashCache.remove(keyRemoved);
      
      hashCache.put(request[idPage], stepArray[idPage] + idPage);  
      pQueueCache.add(stepArray[idPage] + idPage);
      
    } else {
      Iterator<Integer> iterator = pQueueCache.iterator();
      
      while (iterator.hasNext()) {
        if((int)iterator.next() == hashCache.get(request[idPage])) {
          iterator.remove();
          break;
        }
      }
      pQueueCache.add(stepArray[idPage] + idPage);
      hashCache.put(request[idPage], stepArray[idPage] + idPage);
    }
  }
  public int getNumFaults() {
    while(idPage < request.length) {
      this.getNewPageRequest();
      idPage++;
    }
    return pageFaults;
  }
  public static void main(String[] args) {
   ArrayList<FartherInFuture> instanceList = new  ArrayList<FartherInFuture>();
    Scanner scan = new Scanner(System.in);
    String linesString = scan.nextLine().trim();
    int numInstance = Integer.parseInt(linesString);
    for(int id = 0; id< numInstance; id++) {
      linesString = scan.nextLine().trim();
      int numInCache = Integer.parseInt(linesString);
      
      linesString = scan.nextLine().trim();
      int numRequest = Integer.parseInt(linesString);
      instanceList.add(new FartherInFuture(numInCache, numRequest));
      
      linesString = scan.nextLine().trim();
      instanceList.get(id).setRequestSequence(linesString);
    }
    
    for(int id = 0; id< numInstance; id++) {
      instanceList.get(id).findStepArray();
      System.out.println(instanceList.get(id).getNumFaults());
    }
  }
}












