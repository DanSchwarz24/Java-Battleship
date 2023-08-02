import java.util.*;

class Game{
  Board board1; //Player 1 ships, P2 guess
  Board board2; //Player 2 ships, P1 guess
  int ships; //number of ships each player can place
  int sunk1; // number of ships sunk on board1
  int sunk2; // number of ships sunk on board2
  boolean end; // true when the game will end
  Game(){
    board1 = new Board();
    board2 = new Board();
    ships = 0;
    end = false;
    sunk1 = 0;
    sunk2 = 0;
 } 
  void runGame(){
    this.setUpGame();
    this.playGame();
  }
  void setUpGame(){
    System.out.println("Welcome to Battleship.");
    System.out.println("How many ships do you want to play with? Please Choose a Number 1-7.");
    Scanner scan = new Scanner(System.in);
    ships = scan.nextInt();
    while(0 >= ships || ships > 7){
      System.out.println("Please pick a number between 1 and 7.");
      ships = scan.nextInt();
    }
    Random rand = new Random();
    ArrayList<Integer> lengths = new ArrayList<>();
    for(int i = 0; i < ships; i++){
      lengths.add(i, (Integer) (2 + rand.nextInt(4)));
    }
    System.out.println("Player 2 please look away.");
    board1.printHitBoard();
    for(int i = 1; i <= ships; i++){
    System.out.println("Player 1 pick where you want ship " + i +" to start, 1-64. This ship is "+ lengths.get(i-1) + " unit(s) long.");
    int start = scan.nextInt();
    System.out.println("What direction do you want the ship to go? 0 for up, 1 for right, 2 for down, and 3 for left");
    int direction = scan.nextInt();
    if(board1.shipPossible(start, direction, lengths.get(i-1))){
      board1.addShip(start, direction, lengths.get(i-1));
    }
    else{
      System.out.println("That ship is not possible please try again.");
      i--;
      }
    }
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.print("Please remember this board; you will not see it again. Take a picture if needed. The ships in this game are of the lengths: ");
    for (int i = 0; i < ships; i++){
      if(i + 1 == ships)
        System.out.println("" + lengths.get(i));
      else
        System.out.print("" + lengths.get(i) + ", ");
    }
    board1.printSelfBoard();
    System.out.println("Type any key to move on.");
    scan.next();
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("Player 2 may look again. Player 1 look away.");
    board1.printHitBoard();
    for(int i = 1; i <= ships; i++){
    System.out.println("Player 2 pick where you want ship " + i + " to start, 1-64. This ship is "+ lengths.get(i-1) + " long.");
    int start = scan.nextInt();
    System.out.println("What direction do you want the ship to go? 0 for up, 1 for right, 2 for down, and 3 for left");
    int direction = scan.nextInt();
    if(board2.shipPossible(start, direction, lengths.get(i-1))){
      board2.addShip(start, direction, lengths.get(i-1));
    }
    else{
      System.out.println("That ship is not possible please try again.");
      i--;
      }
    }
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.print("Please remember this board; you will not see it again. Take a picture if needed. The ships in this game are of the lengths: ");
    for (int i = 0; i < ships; i++){
      if(i+1==ships)
        System.out.println(""+lengths.get(i));
      else
        System.out.print(""+lengths.get(i)+", ");
    }
    board2.printSelfBoard();
    System.out.println("Type any key to move on.");
    scan.next();
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("Player 1 may look again. The game is ready to start!");
  }
  void playTurn(int player){
    Scanner scan = new Scanner(System.in);
    if (sunk1 == ships){
      System.out.println("Player 2 wins!");
      board1.printHitBoard();
      System.out.println("Type any key to move on.");
      scan.next();
      System.out.println();
      System.out.println("Player 1's Board:");
      board1.printSelfBoard();
      System.out.println("Player 2's Board:");
      board2.printSelfBoard();
      end = true;
      return;
    }
    if (sunk2 == ships){
      System.out.println("Player 1 wins!");
      board2.printHitBoard();
      System.out.println();
      System.out.println("Type any key to move on.");
      scan.next();
      System.out.println("Player 1's Board:");
      board1.printSelfBoard();
      System.out.println("Player 2's Board:");
      board2.printSelfBoard();
      end = true;
      return;
    }
    Board temp = null;
    switch(player){
      case 1:
        temp = board2;
        break;
       case 2:
        temp = board1;
        break;
      default:
        break;
    }
    System.out.println("Player " + player + "'s Hits:");
    temp.printHitBoard();
    System.out.println("Player " + player + " where do you want to guess?");
    int decision = scan.nextInt();
    if (temp.board.findPosition(decision).guessed == true || decision > 64 || decision < 1){
      System.out.println("Please pick a different postiion");
      this.playTurn(player);
      return;
    }
    temp.hitShip(decision);
    temp.board.findPosition(decision).guessed = true;
    if(temp.sinkShip(temp.board.findPosition(decision).ship)){
      System.out.println("You sunk the ship!");
      switch(player){
        case 1:
          sunk2++;
          break;
        case 2:
          sunk1++;
          break;
        default:
          break;
      }
    }
    if(temp.board.findPosition(decision).hit == true){
      this.playTurn(player);
    }
    else{
      System.out.println("Player " + player + "'s hits:");
      temp.printHitBoard();
      System.out.println("Type any key to move on.");
      scan.next();
      System.out.println();
    }
    }
  void playGame(){
   int x = 1;
   while(end != true){
     playTurn(x);
     switch(x){
       case 1:
         x = 2;
         break;
       case 2:
         x = 1;
         break;
      default: 
         break;
     }
    }
   System.out.println();
   System.out.println("The game is over. Run to play again.");
  }
}