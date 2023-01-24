import java.io.*;
import java.util.regex.Pattern;
import java.util.Scanner;
public class avltree {
    public static void main(String[] args) {
        if (args.length == 0)
            return;
        //Open file
        File filename = new File(args[0]);
        File outfile = new File("output_file.txt");
        avl tree = new avl();
        try {
          //create a writer object to write to specified output file
          BufferedWriter  writer = new BufferedWriter(new FileWriter(outfile));
        //read each line and call each corresponding  function from input file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while((line = reader.readLine()) != null) {
                String[] arrofvals = line.split("[\\(\\)|,]");
                String function = arrofvals[0];
                switch (function) {
                  case "Initialize":
                    System.out.println("Tree has been initialized");
                    break;
                  //if insert needs to be invoked
                  case "Insert":
                      int new_key = Integer.parseInt(arrofvals[1]);
                      tree.Insert(new_key); 
                      break;
                  //if delete needs to be invoked
                  case "Delete":
                      int del_key = Integer.parseInt(arrofvals[1]);
                      tree.Delete(del_key);
                      break;
                  //if search needs to be invoked
                  case "Search":
                      int num1 = Integer.parseInt(arrofvals[1]);
                      if (arrofvals.length>2){
                        int num2 = Integer.parseInt(arrofvals[2]);
                        String value = tree.Search(num1, num2);
                        writer.write(value+"\n");
                      }else{String val = tree.Search(num1);
                        writer.write(val+"\n");
                        }
                      break;
                  // Default case
                  default:          
                      System.out.println("no match");
                  }
                
            }reader.close();}
            
           catch (IOException e) {
            e.printStackTrace();
           }
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }      
           
      
      
        


