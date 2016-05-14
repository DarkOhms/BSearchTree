import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Stub {
  public static void main(String[] args) {

    BSearchTree<String> myTree = new BSearchTree<String>();

    File file = new File("dictionary.txt");
    
    try{
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      
      String input;
      
      do
      {
        input = br.readLine();
        if(input != null){
          myTree.insert(input);
        }
      }while(input != null);
      
      br.close();
      
    }catch(FileNotFoundException e){
      System.out.println("File not found: " + file.toString());
    }catch (IOException e){
      System.out.println("Unable to read file: " + file.toString());
    }
   
    
    //show the trees contents forward and backward
    myTree.leftInOrderTraversal();   
    myTree.rightInOrderTraversal();

    // delete and check
    myTree.delete("Isolation");
    myTree.delete("Normalization");
    myTree.delete("Distributed Processing");
     
    System.out.println("==========================================");
    myTree.leftInOrderTraversal();
    System.out.println("==========================================");    
    myTree.rightInOrderTraversal();

  }
}
