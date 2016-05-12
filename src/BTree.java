/*
 * BTree -------- 1 : m contains >> ---- BTreeNode
   BTreeNode ---- 1 : 1 includes >> ---- Data
   
   Class BTree
   
   (+)BTree()
   (+)BTree(BTreeNode<T>)
   (+)BTree(T data)
   
   Members
   
   (-)BTreeNode<T> root
   
   Methods
   
   (+)insert(T data)
     (-)recursiveInsert(T data, BTreeNode<T> current)
   (+)inOrderTraversal()
   (+)boolean search()
     (-)boolean recursiveSearch()
 */

public class BTree<T extends Comparable<T>> {

	private BTreeNode<T> root;
	
	public BTree(){
		
	}
	
	public BTree(BTreeNode<T> newRoot){
		root = newRoot;
	}
	
	public BTree(T data){
		root = new BTreeNode<T>(data);
	}
	
	/*public BTree(BTree<T>){
		copy tree
	}*/
	
	public void insert(T data){
		if(root == null){
			root.setData(data);
		}
		else{
			recursiveInsert(data, root);
		}
	}
	
	public boolean search(T data){
	  return recursiveSearch(data, root);
	}
	
	private boolean recursiveSearch(T data, BTreeNode<T> current){
		if(current == null){
			return false;
		}else{
			if(current.getData().equals(data)){//found it
				return true;
			}else{//look to continue search left or right
		
				if(current.getData().compareTo(data) > 0){
					return recursiveSearch(data, current.getLeft());
				}else{
					return recursiveSearch(data, current.getRight());
				}
			}//end not found in current
		}//non null case
	}
	
	private void recursiveInsert(T data, BTreeNode<T> current){
		
		if(current.isLeaf()){
			if(current.getData().compareTo(data) > 0){
				current.setLeft(new BTreeNode<T>(data));
			}else
				current.setRight(new BTreeNode<T>(data));
		}else{
			if(current.getData().compareTo(data) > 0){//go left
				
				if(current.getLeft() == null){
					current.setLeft(new BTreeNode<T>(data));
				}else
				  recursiveInsert(data, current.getLeft());	
			
			}else{//go right
				
				if(current.getRight() == null){
					current.setRight(new BTreeNode<T>(data));
				}else{
					recursiveInsert(data, current.getRight());
				}
		   }
	   }//end not leaf case
	}//end recursive insert
	
	 public void leftInOrderTraversal(){
	    	
		 root.leftRightTraversal(root);
	    
	 }
	 
	 public void delete(T data){
		 if(search(data)){
			 recursiveDelete(data, root);
		 }
	 }
	 
	 private void recursiveDelete(T data, BTreeNode<T> current){
			 
			 if(current.getData().equals(data)){//found it
					if(current.isLeaf()){
						current.setData(null);
					}else{
						if(current.getLeft() != null && current.getRight() != null){
							//promote immediate successor
							promoteImmediateSuccessor(current);
						}else{
							if(current.getLeft() != null){
								//promote immediate predecessor
								promoteImmediatePredecessor(current);
							}else{
								//promote immediate successor
								promoteImmediateSuccessor(current);
							}
						}
					}
				}else{//look to continue search left or right
			
					if(current.getData().compareTo(data) > 0){
						recursiveDelete(data, current.getLeft());
					}else{
						recursiveDelete(data, current.getRight());
					}
				}//end not found in current 				 
	 }
	 
	 private void promoteImmediateSuccessor(BTreeNode<T> current){
		 
		 recursivePromoteSuccessor(current);
	
	 }
	 
	 private void recursivePromoteSuccessor(BTreeNode<T> current){
		 if(current.getRight() == null){//limit call
             
		 }else{
			 current.setData(current.getRight().getLeftmostData());
			 recursivePromoteSuccessor(current.getLeftmost(current.getRight()));
			 current.setRight(current.getRight().removeLeftmost());
		 }
	 }
	 
     private void promoteImmediatePredecessor(BTreeNode<T> current){
		recursivePromotePredecessor(current);
	}
    private void recursivePromotePredecessor(BTreeNode<T> current){ 
	     if(current.getLeft() == null){//limit call
	         
		 }else{
			 current.setData(current.getLeft().getRightmostData());
			 recursivePromotePredecessor(current.getRightmost(current.getLeft()));
			 current.setLeft(current.getLeft().removeRightmost());
		 }
    }
}
