import java.io.*;
import java.util.Scanner;
/**The main class, which takes 2 command line arguments: first is input file,
 * second is output file. It takes the instructions from the specified pre-existing input
 *  file, processes the instructions, then outputs the results to a new output file
 * that it creates.
 *
 * @author Brandon Atwal
 */
public class AtwalProject2 {
    static FileWriter fileWriter;
    /**Main function. Parses command line arguments, reads and creates files, then runs instructions from input file
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{ //1st arg = input, 2nd = output
        if (args.length != 2)
            System.out.println("Error in launch: Invalid # of args. 2 args required");
        else{
            for (String s : args){
                System.out.println(s);
            }
            LazyBinarySearchTree x = new LazyBinarySearchTree();
            
            File input = new File(args[0]); //set input file
            if (input.exists() == false){
                System.out.println("Error in launch: input file does not exist.");
            }
            
            File output = new File(args[1]); //set output file
            if (output.createNewFile()){     //attempt to create file
                //file created, you're good to go
            }else{
                output.delete(); //if file already exists, delete then create empty
                output.createNewFile();
            }
            
            Scanner fileReader = new Scanner(input);
            fileWriter = new FileWriter(output);
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine(); //get next line
                String[] commands = {"Insert:", "Delete:", "Contains:", 
                    "PrintTree", "Height", "Size", "FindMin", "FindMax"};
                boolean isValid = false; //is it a valid keyword?
                for(int i = 0; i < commands.length; i++){
                    if (line.startsWith(commands[i])){ //if any key words relating to commands are found, attempt to run command
                        isValid = true;
                        switch(i){
                            case 0: insert(x,line);break;
                            case 1: delete(x,line);break;
                            case 2: contains(x,line);break;
                            case 3: printTree(x,line);break;
                            case 4: height(x,line);break;
                            case 5: size(x,line);break;
                            case 6: min(x,line);break;
                            case 7: max(x,line);break;
                        }
                    }
                }
                //if no valid command keyword is found, output error at line message
                if (isValid == false)
                        outputError(line);
//                System.out.println(line + " - done");
            }
            
            //close reader and writer
            fileReader.close();
            fileWriter.close();
        }
    }
    
    //for the FindMin instruction
    private static void min(LazyBinarySearchTree x, String line){
        Integer s = null;
        if (line.trim().compareTo("FindMin") == 0){ //if theyre the same
            try{
                s = x.findMin();
            }catch (Exception e){
                outputError();
            }
        }else{ //if theyre different
            outputError(line); //output error at Line message, since it's invalid input
        }
        if (s != null) //if no error, output result
            write("" + s);
    }
    
    //for the FindMax instruction
    private static void max(LazyBinarySearchTree x, String line){
        Integer s = null;
        if (line.trim().compareTo("FindMax") == 0){ //if theyre the same
            try{
                s = x.findMax();
            }catch (Exception e){
                outputError();
            }
        }else{ //if theyre different
            outputError(line); //output error at Line message, since it's invalid input
        }
        if (s != null) //if no error, output result
            write("" + s);
    }
    
    //for the Size instruction
    private static void size(LazyBinarySearchTree x, String line){
        if (line.trim().compareTo("Size") == 0){ //if theyre the same
            write("" + x.size()); //output size
        }else{ //if theyre different
            outputError(line); //output error at Line message, since it's invalid input
        }
    }
    
    //for the Height instruction
    private static void height(LazyBinarySearchTree x, String line){
        if (line.trim().compareTo("Height") == 0){ //if theyre the same
            write("" + x.height()); //output height
        }else{ //if theyre different
            outputError(line); //output error at Line message, since it's invalid input
        }
    }
    
    //fir the PrintTree instruction
    private static void printTree(LazyBinarySearchTree x, String line){
        if (line.trim().compareTo("PrintTree") == 0){ //if theyre the same
            write("" + x.toString());
        }else{ //if theyre different
            outputError(line); //output error at Line message, since it's invalid input
        }
    }
    
    //for the Insert instruction
    private static void insert(LazyBinarySearchTree x, String line){
        String keyStr = line.substring("Insert:".length()); //get rid of the command
        int key = Integer.valueOf(keyStr);
        Boolean s = null;
        try{
            s = x.insert(key);
        }catch (Exception e){
            outputError(); //if error, output error at insert message
        }
        if (s != null){ //output results if not an error
            if (s == true){
                write("True");
            }else{
                write("False");
            }
        }
    }
    
    //for the Delete instruction
    private static void delete(LazyBinarySearchTree x, String line){
        String keyStr = line.substring("Delete:".length()); //get rid of the command
        int key = Integer.valueOf(keyStr);
        Boolean s = null;
        try{
            s = x.delete(key);
        }catch (Exception e){
            outputError(); //if error, output error at insert message
        }
        if (s != null){ //output results if not an error
            if (s == true){
                write("True");
            }else{
                write("False");
            }
        }
    }
    
    //for the Contains instruction
    private static void contains(LazyBinarySearchTree x, String line){
        String keyStr = line.substring("Contains:".length()); //get rid of the command
        int key = Integer.valueOf(keyStr);
        Boolean s = null;
        try{
            s = x.contains(key);
        }catch (Exception e){
            outputError(); //if error, output error at insert message
        }
        if (s != null){ //output results if not an error
            if (s == true){
                write("True");
            }else{
                write("False");
            }
        }
    }
    
    //Writes the given string to the output file.
    private static void write(String s){
//        System.out.println(s);
        try{
            fileWriter.write(s + System.getProperty("line.separator")); //output string + new line character
        }catch(Exception e){
            
        }
    }
    
    //Writes an error at insert type error message to the input file.
    private static void outputError(){
        write("Error in insert: IllegalArgumentException raised");
    }
    
    /*Writes an error in line type error message with the given line.
      ln is the line where the error was found.*/
    private static void outputError(String ln){
        write("Error in Line: " + ln);
    }
    
}
