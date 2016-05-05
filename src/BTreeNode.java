/*
 * Class BTreeNode
 * 
 * (+)BTreeNode<T>(T initialData, BTreeNode<T> initialLeft, BTreeNode<T> initialRight)
 * (+)BTreeNode<T>(T newData)
 * (+)BTreeNode<T>(BTreeNode<T> newItem)
 * 
 * Members
 * (-)T data
 * (-)BTreeNode<T> left, right
 * 
 * Methods
 * (+)T getData()
 * (+)void setData(T newData)
 * (+)BTreeNode<T> getLeft(), getRight()
 * (+)void setLeft(), setLeft()
 * 
 */


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
    
    public void leftRightTraversal(BTreeNode<T> current){
    	if(current.getLeft() != null)
    		leftRightTraversal(current.getLeft());
 
    	process(current);
    	
    	if(current.getRight() != null)
    		leftRightTraversal(current.getRight()); 
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
   
	private void process(BTreeNode<T> node){
	  System.out.println(String.valueOf(node.getData()));
	} 
}
