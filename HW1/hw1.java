import java.util.Scanner;

public class hw1 {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String linesString = scan.nextLine().trim();
    int lines = Integer.parseInt(linesString);
    String[] names = new String[lines];
    for(int i = 0; i < lines; i++) {
	    names[i] = scan.nextLine();
    }
    for(int i = 0; i < lines; i++) {
      System.out.println("Hello, " + names[i] + "!");
    }
    

  }

}
