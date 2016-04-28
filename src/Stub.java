
public class Stub {
  public static void main(String[] args){
	  
	  BTree<String> myTree = new BTree<String>("Martin");
	  
	  myTree.insert("This");
	  myTree.insert("can");
	  myTree.insert("be");
	  myTree.insert("cool");
	  
	  System.out.println(myTree.root.getData());
	  System.out.println(myTree.root.getLeftmostData());
	  
  }
}
