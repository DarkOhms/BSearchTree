/*
 * BSearchTree -------- 1 : m contains >> ---- BTreeNode
   BTreeNode ---- 1 : 1 includes >> ---- Data
   
   Class BSearchTree
   
   (+)BSearchTree()
   (+)BSearchTree(BTreeNode<T>)
   (+)BSearchTree(T data)
   
   Members
   
   (-)BTreeNode<T> root
   
   Methods
   
   (+)insert(T data)
     (-)recursiveInsert(T data, BTreeNode<T> current)
   (+)inOrderTraversal()
   (+)boolean search()
     (-)boolean recursiveSearch()
 */

public class BSearchTree<T extends Comparable<T>> {

  private BTreeNode<T> root;
  private BTreeNode<T> parentOfCursor;

  public BSearchTree() {

  }

  public BSearchTree(BTreeNode<T> newRoot) {
    root = newRoot;
  }

  public BSearchTree(T data) {
    root = new BTreeNode<T>(data);
  }

  /*
   * public BSearchTree(BSearchTree<T>){ copy tree }
   */

  public void insert(T data) {
    if (root == null) {
      root = new BTreeNode<T>(data);
    } else {
      recursiveInsert(data, root);
    }
  }

  public boolean search(T data) {
    return recursiveSearch(data, root);
  }

  private boolean recursiveSearch(T data, BTreeNode<T> current) {
    if (current == null) {
      return false;
    } else {
      if (current.getData().equals(data)) {// found it
        return true;
      } else {// look to continue search left or right

        if (current.getData().compareTo(data) > 0) {
          return recursiveSearch(data, current.getLeft());
        } else {
          return recursiveSearch(data, current.getRight());
        }
      } // end not found in current
    } // non null case
  }

  private void recursiveInsert(T data, BTreeNode<T> current) {

    if (current.isLeaf()) {
      if (current.getData().compareTo(data) > 0) {
        current.setLeft(new BTreeNode<T>(data));
      } else
        current.setRight(new BTreeNode<T>(data));
    } else {
      if (current.getData().compareTo(data) > 0) {// go left

        if (current.getLeft() == null) {
          current.setLeft(new BTreeNode<T>(data));
        } else
          recursiveInsert(data, current.getLeft());

      } else {// go right

        if (current.getRight() == null) {
          current.setRight(new BTreeNode<T>(data));
        } else {
          recursiveInsert(data, current.getRight());
        }
      }
    } // end not leaf case
  }// end recursive insert

  public void leftInOrderTraversal() {

    root.leftRightTraversal(root);

  }
  
  public void rightInOrderTraversal() {

    root.rightLeftTraversal(root);

  }

  public void delete(T data) {
    if (search(data)) {
      recursiveDelete(data, root);
    }
  }

  private void recursiveDelete(T data, BTreeNode<T> current) {

    if (current.getData().equals(data)) {// found it
      if (current.isLeaf()) {
        if(parentOfCursor.getRight() == current){
          parentOfCursor.setRight(null);
        }else{
          parentOfCursor.setLeft(null);
        }
      } else {
        if (current.getLeft() != null && current.getRight() != null) {
          // promote immediate successor
          promoteImmediateSuccessor(current);
        } else {
          if (current.getLeft() != null) {
            // promote immediate predecessor
            promoteImmediatePredecessor(current);
          }
        }
      }
    } else {// look to continue search left or right
      parentOfCursor = current;
      if (current.getData().compareTo(data) > 0) {
        recursiveDelete(data, current.getLeft());
      } else {
        recursiveDelete(data, current.getRight());
      }
    } // end not found in current
  }

  private void promoteImmediateSuccessor(BTreeNode<T> current) {
    parentOfCursor = null;
  
    recursivePromoteSuccessor(current);

  }

  private void recursivePromoteSuccessor(BTreeNode<T> current) {
    if (current.getRight() == null) {// limit call
      parentOfCursor.setRight(parentOfCursor.getRight().removeLeftmost());
    } else {
      current.setData(current.getRight().getLeftmostData());
      parentOfCursor = current;
      recursivePromoteSuccessor(current.getLeftmost(current.getRight()));
    }
  }

  private void promoteImmediatePredecessor(BTreeNode<T> current) {
    parentOfCursor = null;
    
    recursivePromotePredecessor(current);
  }

  private void recursivePromotePredecessor(BTreeNode<T> current) {
    if (current.getLeft() == null) {// limit call
      parentOfCursor.setLeft(parentOfCursor.getLeft().removeRightmost());
    } else {
      current.setData(current.getLeft().getRightmostData());
      parentOfCursor = current;
      recursivePromotePredecessor(current.getRightmost(current.getLeft()));
    }
  }
}
