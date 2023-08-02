class LinkedList{
  ListNode head; 
  ListNode getNode(int in){
    ListNode current = head;
    int count = 0;
    while(count < in){
      current = current.next;
      count++;
    }
    return current;
  }
  void addNode(int p){
    if(this.head == null){
      ListNode temp = new ListNode(p);
      this.head = temp;
    }
    else{
      ListNode temp = head;
      while (temp.next != null){
        temp = temp.next;
      }
      temp.next = new ListNode(p);
    }
  }
  ListNode findPosition(int pos){
    ListNode current = this.head;
    while(current != null){
      if (current.position == pos)
        return current;
     current = current.next; 
    }
    return null;
  }
  void fillList(){
    this.head = new ListNode(1);
    ListNode temp = head;
    for (int i = 2; i <= 64; i++){
      temp.next = new ListNode(i);
      temp = temp.next;
    }
  }
}