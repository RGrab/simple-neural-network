import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class MnistData{

  //holds each line in the csv as an element in ArrayList.
  private ArrayList<String> data = new ArrayList<String>();

  public MnistData(){
  }

  public MnistData(String filename){
    storedata(filename);
  }

  private void storedata(String fileName){
    try{
      //temporarily hold line data.
      String line = null;

      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      //stares data into ArrayList Data one line at a time.
      while((line = bufferedReader.readLine()) != null){
        this.data.add(line);
      }
      //close file
      bufferedReader.close();
    } catch(FileNotFoundException ex) {
      System.out.println("File not found!");
    } catch(IOException ex) {
      ex.printStackTrace();
    }
  }

  public String getDataByIndex(int index){
    return data.get(index);
  }
}
