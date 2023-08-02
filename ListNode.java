class ListNode{
  int position; // 1-64 l>r,l>r grid
  int ship; // 0 for no ship, 1 for type 1 ship, etc.
  boolean hit; //if this has been guessed
  ListNode next;
  boolean guessed;
  
  ListNode(int pos){
    position = pos;
    ship = 0;
    hit = false;  
    guessed = false;
  }
}