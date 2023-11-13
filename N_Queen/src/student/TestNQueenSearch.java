package student;

public class TestNQueenSearch {

  public static void main(String[] args) {

	NQueenSearchAlgo searcher = new NQueenSearchAlgo(); 
    // Khởi tạo node ban đầu
    Node initial = new Node();
    initial.generateBoard();

    // Thuật toán Hill Climbing 
    Node result1 = searcher.execute(initial);
    System.out.println("Hill Climbing:");
    System.out.println("Total steps climbed: " + searcher.stepClimbed);
    result1.displayBoard();

    // Thuật toán Hill Climbing với khởi tạo ngẫu nhiên
    Node result2 = searcher.executeHillClimbingWithRandomRestart(initial); 
    System.out.println("Hill Climbing with Random Restart:");
    System.out.println("Total steps climbed after each restart: " + searcher.stepClimbedAfterRandomRestart);  
    System.out.println("Number of random restarts: " + searcher.randomRestarts);
    result2.displayBoard();

  }

}