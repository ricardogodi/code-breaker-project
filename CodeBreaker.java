/* Ricardo Gonzalez
 * MCS 141
 * Professor: Jonathan Sprague
 * 12/01/18
 * Code Breaker Project
 * */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeBreaker {
  
  public static void main (String [] args) throws IOException {
    
    StringBuilder trainingStr = new StringBuilder(); //SB to store training
    StringBuilder codeStr = new StringBuilder(); //SB to store code
    
    File training = new File("training.txt");
    File code = new File("decodethis.txt");
    
    Scanner readTraining = new Scanner(training); //point at training file
    Scanner readCode = new Scanner(code); //point at code file
    
    while (readTraining.hasNext()) { //read training
      trainingStr.append(readTraining.nextLine());
      trainingStr.append("\r\n"); 
    }
    
    while (readCode.hasNext()) { //read code
      codeStr.append(readCode.nextLine());
      codeStr.append("\r\n");
    }
    
    int [] trainingCounter = letterCounter(trainingStr);   //count letters from training
    printFrequency("LETTER FREQUENCY IN TRAINING:", trainingCounter); 
    int [] codeCounter = letterCounter(codeStr);   //count letters from code
    printFrequency("LETTER FREQUENCY IN CODE:", codeCounter); 
    
    
    char [] alphabetTraining = new char[26];
    alphabetTraining = fillLetters(alphabetTraining);
    sort(alphabetTraining, trainingCounter);       //sort char and int arrays from training
    printTable("SORTED TRAININGS's LETTER FREQUENCY", alphabetTraining, trainingCounter);
    
    char [] alphabetCode = new char[26];
    alphabetCode = fillLetters(alphabetCode);    
    sort(alphabetCode, codeCounter);            //sort char and int arrays from code
    printTable("SORTED CODE's LETTER FREQUENCY", alphabetCode, codeCounter);
    
    
    printKey("CIPHER KEY", alphabetCode, alphabetTraining);
    
    codeStr = replaceLetters(codeStr, alphabetCode, alphabetTraining); //Replace letters
    
    //Write decoded file
    File decoded = new File("decoded.txt");    
    PrintWriter pw = new PrintWriter(decoded);
    pw.println(codeStr);
    pw.close();
    
    readTraining.close();
    readCode.close();
  }
  
  public static int [] letterCounter(StringBuilder input) {
    String temp = "";
    int [] a = new int[26];
    for (int i = 0; i < input.length(); i++) {  
      temp = temp + input.charAt(i);     
      char letter = temp.toUpperCase().charAt(0); 
      for (char n = 'A'; n <= 'Z'; n++) {
        if (letter == n) {
          a [n-'A'] = a[n-'A'] + 1;
          break;
        }
      }
      temp = "";
    } return a;
  }
  
  public static char [] swapLetter(char [] array, int index1, int index2) {
    char temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
    return array;
  }
  
  public static int [] swapNumber(int [] array, int index1, int index2) {
    int temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
    return array;
  }
  
  public static char [] fillLetters(char [] array) { //Fill char array with alphabet
    char letter = 'a';
    array = new char[26];
    for (int i = 0; i < array.length; i++) { 
      
      array[i] = letter;
      letter++;
    }
    return array;
  }
  
  public static StringBuilder replaceLetters(StringBuilder codeStr, char [] alphabetCode, char [] alphabetTraining) {
    
    for (int i = 0; i < codeStr.length(); i++) { //Traverse the code string 
      
      if (codeStr.charAt(i) >= 'a' && codeStr.charAt(i) <= 'z') {  //Found LOWER case letter
        
        for (int k = 0; k < alphabetCode.length; k++) { //Loop through alphabet to find the location of the letter
          
          if (codeStr.charAt(i) == alphabetCode[k]) {   //If char at i matches the one in the alphabet...
            
            codeStr.setCharAt(i, alphabetTraining[k]);   //Replace with corresponding letter from the cipher key
            
            break;
            
          }
        }
      }
      
      if (codeStr.charAt(i) >= 'A' && codeStr.charAt(i) <= 'Z') {  //Found UPPER case letter
        
        for (int k = 0; k < alphabetCode.length; k++) { //Loop through alphabet to find the location 
                                                         // of the letter
          
          if (codeStr.charAt(i) == alphabetCode[k] - 32) {  //Compare with the UPPER case letter
            
            codeStr.setCharAt(i, (char) (alphabetTraining[k] - 32)); //Replace with corresponding upper
                                                                       //case letter from the cipher key
            
            break;
          }
        }
      }
    }
    return codeStr;
  }
  
  public static void printKey(String string, char [] alphabetCode, char [] alphabetTraining){  //printKeyCipher
    System.out.println(string);
    for (int i = 0; i < alphabetCode.length; i++) {
      System.out.printf("%3c ------> %c%n", (char)(alphabetCode[i]-32), (char)(alphabetTraining[i]-32));
    }
  }
  
  public static void sort(char [] letters, int [] counter) {
    for (int i = 0; i < counter.length; i++) { 
      int maxIndex = i;
      for (int j = i; j < counter.length; j++) {
        if (counter[j] > counter[ maxIndex ]) {
          maxIndex = j;
        }
      }
      counter = swapNumber(counter, i, maxIndex );
      letters = swapLetter(letters, i, maxIndex );
    }
  }
  
  public static void printTable(String string, char [] letters, int [] counter) {
    System.out.println(string);
    for (int i = 0; i < letters.length; i++) {
      System.out.printf("%3c's: %6d%n", letters[i], counter[i]);
    }
    System.out.println();
  }
  
  public static void printFrequency(String string, int [] counter){
    System.out.println(string);
    for (int i = 0; i < counter.length; i++) {
      System.out.printf("%3c: %6d%n", (char)(i+'A'), counter[i]);
    }
    System.out.println();
  }
}


/*
 public static int [] frequencyOrder(int [] counts) {
 int [] frequencyOrder = new int[26];
 int index = 0;
 int max = 0;
 int counter = 1;
 for (int i = 0; i < counts.length; i++) {
 for (int j = 0; j < counts.length; j++) {
 if (max < counts[j]) {
 max = counts[j]; 
 index = j;
 }
 }
 frequencyOrder[index] = counter;
 counter++;
 counts[index] = 0;
 max = 0;
 }
 return frequencyOrder;
 }
 */