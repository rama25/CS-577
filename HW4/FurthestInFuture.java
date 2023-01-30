import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FurthestInFuture {
  
  private String[] cache;
  private String[] request;
  private int idPage = 0; 
  private int cacheSize = 0; 
  private int pageFaults = 0;
  
  public FurthestInFuture(int numInCache, int numRequest) {
    this.cache = new String[numInCache];
    this.request = new String[numRequest];
    
  }
  
  public void setRequestSequence(String requestSequence) {
    this.request = requestSequence.trim().split(" ");
  }
  
  public boolean isFault(String newRequest) {
    for(int i = 0; i < cache.length; i++) {
      if(newRequest.equals(cache[i])) {
        return false;
      }
    }
    return true;
  }
  
  public int findFurthestPagePosition(String page) {
    int furthestPosition = -1;
    int position = idPage;
    while(position < request.length) {
      if(request[position].equals(page)) {
        furthestPosition = position;
        break;
      }
      position++;
    }    
    return furthestPosition;
  }
  
  public int findEvictedPagePosition() {
    int cacheID = 0;
    for(int i = 1; i < cacheSize; i++) {
      if (this.findFurthestPagePosition(cache[cacheID]) == -1) return cacheID;
      if (this.findFurthestPagePosition(cache[i]) == -1) return i; 
      if (this.findFurthestPagePosition(cache[i]) > this.findFurthestPagePosition(cache[cacheID])) {
        cacheID = i;
      }
    } 
    return cacheID;
  }
  public void getNewPageRequest() {
    if(cacheSize < cache.length) {
      if(this.isFault(request[idPage])) {
        pageFaults++;
        cache[cacheSize] = request[idPage];
        cacheSize++;
      }
      return;
    }
     if(this.isFault(request[idPage])) {  
      pageFaults++;
      cache[this.findEvictedPagePosition()] = request[idPage]; 
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
    ArrayList<FurthestInFuture> instanceList = new  ArrayList<FurthestInFuture>();
    Scanner scan = new Scanner(System.in);
    String linesString = scan.nextLine().trim();
    int numInstance = Integer.parseInt(linesString);
    for(int id = 0; id< numInstance; id++) {
      linesString = scan.nextLine().trim();
      int numInCache = Integer.parseInt(linesString);
      linesString = scan.nextLine().trim();
      int numRequest = Integer.parseInt(linesString);
      instanceList.add(new FurthestInFuture(numInCache, numRequest));
      linesString = scan.nextLine().trim();
      instanceList.get(id).setRequestSequence(linesString);
    }
    for(int id = 0; id< numInstance; id++) {
      System.out.println(instanceList.get(id).getNumFaults());
    }
  }

}
