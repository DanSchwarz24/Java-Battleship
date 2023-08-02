public class Board{
int shipNum; // next ship number to be used
Colors myColors = new Colors();
Fonts myFonts = new Fonts();
LinkedList board;
  Board(){
    board = new LinkedList();
    board.fillList();
    shipNum = 1; 
  }
  boolean shipPossible(int startPos, int direction, int length){
    int x = 0;
    switch(direction){
      case 0: // up
        x = -8;
        break;
      case 1: // right
        x = 1;
        break;
      case 2: // down
        x = 8;
        break;
      case 3: //left
        x = -1;
        break;
      default:
          break;
    }
    int pos = startPos;
    for (int i = 0; i < length; i++){
      if( (pos < 1) || (direction == 1 && pos % 8 == 0 && i+1 != length) || (pos > 64) || (direction == 3 && (pos-1) % 8 == 0 && i+1 != length) || board.findPosition(pos).ship != 0 || x == 0){
        return false; }
      else{
        pos += x; }
      }
    return true;      
    }
  ListNode nextNode(ListNode start, int direction){
    int x = 0;
    switch(direction){
      case 0: // up
        x =- 8;
        break;
      case 1: // right
        x = 1;
        break;
      case 2: // down
        x = 8;
        break;
      case 3: //left
        x =- 1;
        break;
      default:
          break;
    }
    return (board.findPosition(start.position + x));
  }
  void addShip(int startPos, int direction, int length){
    if(!shipPossible(startPos, direction, length)){
      System.out.println("Ship not possible."); 
      return; }
    int shipVal = shipNum;
    ListNode current = board.findPosition(startPos);
    current.ship = shipVal;
    for (int i = 0;i < length-1; i++){
      current = this.nextNode(current, direction);
      current.ship = shipVal;
    }
    shipNum++;
    }
  boolean sinkShip(int num){
    ListNode current = board.head;
    while(current != null){
      if(current.ship == num && current.hit == false)
        return false;
      current = current.next;
    }
    return true;
  }
  void printHitBoard(){
    ListNode current = board.head;
    while (current != null){
      if ((current.position-1) % 8 == 0 && current.position != 1)
        System.out.println();
      if(current.hit){
        //System.out.print("/u001B[34m");
        if(this.sinkShip(current.ship))
          System.out.print(myColors.black + myColors.redBG + "X " + myColors.reset);
        else
          System.out.print(myColors.redBG + "*  " + myColors.reset); }
      else if(current.guessed){
        System.out.print(myColors.black + myFonts.bold + myColors.blueBG + "o  " + myColors.reset); }
      else{
        if (current.position < 10)
          System.out.print(""+current.position+"  ");
        else
          System.out.print(""+current.position+" ");
      }
      //System.out.print("/u001B[0m");
      current = current.next;
      }
    System.out.println();
  }
  void printSelfBoard(){
    ListNode current = board.head;
    while (current != null){
      if ((current.position-1) % 8 == 0 && current.position != 1)
        System.out.println();
      System.out.print(current.ship + " ");
      current = current.next;
      }
    System.out.println();
  }
  boolean hitShip(int position){
    ListNode temp = board.findPosition(position);
      if (temp.ship != 0){
        System.out.println("Hit.");
        if (sinkShip(temp.ship))
          System.out.println("You sunk the ship!");
        return temp.hit = true;  
      }
    System.out.println("Miss.");
    return false;
  }
}