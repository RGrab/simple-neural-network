import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class MnistData{

  //holds each line in the csv as an element.
  private ArrayList<String> data = new ArrayList<String>();

  public MnistData(){
  }

  public MnistData(String filename){
    storedata(filename);
  }

  /**
  * reads in and stores data from file.
  *
  *@param String fileName : file to read from.
  */
  private void storedata(String fileName){
    // check to make sure file opens and is read correctly.
    try{
      //temporarily hold line data.
      String line = null;

      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      //stores data into ArrayList Data one line at a time.
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

  /**
  * returns queried data given index.
  *
  *@param int index
  *@return String : mnist line data.
  */
  public String getDataByIndex(int index){
    return data.get(index);
  }

  /**
  * return size of data.
  */
  public int dataSize(){
    return this.data.size();
  }
}
