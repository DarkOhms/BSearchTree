
public class BTree<T extends Comparable<T>> {

	BTreeNode<T> root;
	
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
	
	private void recursiveInsert(T data, BTreeNode<T> current){
		
		if(current.isLeaf()){
			if(current.getData().compareTo(data)>0){
				current.setLeft(new BTreeNode<T>(data));
			}else
				current.setRight(new BTreeNode<T>(data));
		}else{
			if(current.getData().compareTo(data)<0){//go left
				
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
	
}
