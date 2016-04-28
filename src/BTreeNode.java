
public class BTreeNode<T extends Comparable<T>> {
	
	private T data;
    private BTreeNode<T> left = null;
    private BTreeNode<T> right = null;

    public BTreeNode(T initialData, BTreeNode<T> initialLeft, BTreeNode<T> initialRight){
      data = initialData;
      left = initialLeft;
      right = initialRight;
    }
    
    public BTreeNode(T newData){
        data = newData;
    }
    
    public BTreeNode(BTreeNode<T> newItem){
        data = newItem.data;
        left = newItem.left;
        right = newItem.right;
    }
    
    public T getData(){
    	return data;
    }
    
    public void setData(T newData){
    	data = newData;
    }
    
    public BTreeNode<T> getLeft(){
    	return left; 
    }
    
    public BTreeNode<T> getRight(){
    	return right; 
    }
    
    public void setLeft(BTreeNode<T> newLeft){
    	left = newLeft;
    }
    
    public void setRight(BTreeNode<T> newRight){
    	right = newRight;
    }
    
    public boolean isLeaf(){
    	return (left == null) && (right == null);
    }
    
    public T getLeftmostData(){
    	if(left == null)
    		return data;
    	else
    		return left.getLeftmostData();
    	
    }
    
    public T getRightmostData(){
    	if(right == null)
    		return data;
    	else
    		return right.getRightmostData();
    	
    }
    
    public BTreeNode<T> removeLeftmost(){
    	if(left == null)
    		return right;
    	else{
    		left =left.removeLeftmost();
    		return this;
    	}
    }
    
    public BTreeNode<T> removeRightmost(){
    	if(right == null)
    		return left;
    	else{
    		right = right.removeRightmost();
    		return this;
    	}
    }    
    
}
