import java.io.*;
import java.util.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Random;
/**   
 * class imguRand downloads random imgur pictures from the internet.
 *
 * @author Obvious Anonymous
 *
 * @version 2.0 reliant
 *
 * NOTE: will not do anything by itself, all functions describe an object:
 *    - I have provided Class CallImguRand, run it in the same directory for it to work :thumbup:
 *
 * NOTE: will store files in the same directory as the script.
 *
 * NOTE: nomenclature of real files is its UID (Unique Identifier).
 *
 * NOTE: READ CALLIMGURAND CLASS DOC COMMENTS FOR WARNINGS // RECOMMENDATIONS.
 *
 * NOTE: program writes uselss data to local disk a LOT:
 *    - I recommend running this on an expendable hard drive or usb drive.
 *
 * reference nomenclature for IMGUR image links:
 *    - i.imgur.com/<variable>.pF:
 *    - variable is 7 digits long
 *    - characters can be either number or letter (36 possible characters)
 *
 * list of all methods:
 *    - default constructor
 *    - nondefault constructor(String)
 *    - get new random char
 *    - array initialization
 *    - transfer chars
 *    - try all three filetypes <call ONLY this method externally>
 *    - download file
 *    - main
 *    - get randomArr
 *    - get mainString
 *    - get mainFile
 *    - get preffered filetype
 *    - set preffered filetype
 */
public class ImguRand{



   //String pF stands for "Preferred Filetype"
   //its 2 AM and i REAALLY REALLY dont want to type that whole phrase out :cry:
   public String pF = "png";
   


   //this Random object is used to make random characters.
   public Random randy = new Random();
   
   //char[] randArr makes the link-initialization process eaiser.
   public char[] randArr = new char[7];


	//String mainString is randomly generated, is 7 characters long.
   public String mainString = "";

   //URL mainUrl is, well, the main URL...
	public URL mainUrl; //= new URL("https://google.com");


   //File mainFile is the file that will be written to.
   public File mainFile = new File("PlaceHolder." + pF);
	
	
	/**
    * the default constructor is pretty barebones, 
    * just assigns random characters to radArr and then assigns them to mainString.
    *
    *
    * custom variables // methods // classes used:
    *    - method arrInit
    *    - method transferChars
    *    - URL mainUrl
    *
    */
	public ImguRand(){
	   arrInit();
      transferChars();
      try{
      mainUrl = new URL("https://i.imgur.com/" + mainString + "." + pF);
      } catch(Exception e){e.printStackTrace();}
	}//end default constructor


   /**
    * the only non-default constructor takes a pre-decided input String 
    * and uses it instead of randomizing variable (see doc comments on class).
    *
    * custom methods // variables // classes used:
    *    - String mainString
    *    - URL mainUrl
    *
    * @param rigged : string used in place of a randomized set of characters.
    */
	public ImguRand(String rigged){
		//will refuse if input is not 7 characters long
      if(rigged.length() != 7){
         System.out.println("insufficient (or superflous) input length!");
      } else {
         try{
         mainString = rigged; // <-- quite literally, lol
         mainUrl = new URL("https://i.imgur.com/" + mainString + "." + pF);
         }catch(Exception e){e.printStackTrace();}
      }
		
	}//end non-default contructor




   /** method getNewRandomChar gets only one random character.
    *
    * note: in the scope of this script, this method needs to be called multiple times,
    *       it will not generate an entire char[] or string by itself.
    *
    * customs variables // methods // classes used:
    *    - Random randy
    *
    * @return random number or letter (char).
    */
   public char getNewRandomChar(){
      for(int i = 0;;){
         //print statement is just here to look cool, feel free to comment it out :)
         //System.out.print(".");
         i = randy.nextInt(1000);
         //makes sure that the ascii number corresponds with nomenclature of link
         if(((i >= 48) && (i <= 57)) || ((i >= 65) && (i <= 90)) || ((i >= 97) && (i <= 122))){
            char result = (char)i;
            //System.out.print(result);
            return result;
         }// end if^for: filter out unwanted numbers
      }//end for(i): runs infinitely
   }//end getnewrandomchar



/**
 * method arrInit fills char[] randArr with random letters // letters.
 *
 * custom classes // variables // methods used:
 *    - char[] randArr
 *    - method getNewRandomChar
 *
 * @return void.
 */
   public void arrInit(){
      randArr = new char[7];
      for(int i = 0; i < randArr.length; i++){
         randArr[i] = getNewRandomChar();
      }//end for(i): assign random chars to array
   }//end arrInit


/**
 * method transferChars takes the contents of randArr and puts it in mainString.
 *
 * custom methods // variables // classes used:
 *    - char[] randArr
 *
 * @return void.
 */
   public void transferChars(){
      mainString = "";
      for(int i = 0; i < randArr.length; i++){
         mainString += randArr[i];  
      }//end for(i): add contents of randArr to mainString
   }//end transferChars






/**
 * method tryThreeFileTypes is the method that should be called externally:
 *    it creates a random URL, tests it in all three possible filetypes, and 
 *    keeps going until it finds a real image.
 *
 *    note: as of version 2.0, it will download a copy of each image in each filetype:
 *          - imgur stores images in all three filetypes.
 *
 * custom methods // classes // variables used:
 *    - String mainString
 *    - method arrInit
 *    - method transferChars
 *    - URL mainUrl
 *    - File mainFile
 *
 * version 2.0 added the option for the program to continue after it finds a picture:
 *    - it renames the image to it's UID after it confirms that its a real image,
 *      meaning that no useful data will be overwritten.
 *
 * @param boolean : enter true for the method to stop after it finds an image, 
 *                  enter false for it to keep going.
 *
 *
 */
public void tryThreeFileTypes(boolean input){
   String[] fTs = {"png", "gif", "jpeg"};//fTs stands for "File Types", serves as a
                                         //reference list for all available file types.
   
   for(;;){
      //next seven lines resets the random letters // assigns it to url
      mainString = "";
      randArr = new char[7];
      arrInit();
      transferChars();
      try{
      mainUrl = new URL("https://i.imgur.com/" + mainString + "." + pF);
      } catch(Exception e){e.printStackTrace();}


      for(int i = 0; i < fTs.length; i++){


         mainFile = new File("extracted_picture." + fTs[i]);
         pF = fTs[i];
         try{
           mainUrl = new URL("https://i.imgur.com/" + mainString + "." + pF);
         } catch(Exception e){e.printStackTrace();}

         downloadFile();
         System.out.print(mainString + "." + fTs[i] + " \t--->\t");
         if(mainFile.length() > 510){
            if(mainFile.renameTo(new File(mainString + "." + fTs[i]))){
               System.out.print(success() + "\n");
            } else {
               System.out.print("Failed: Failed to Rename File.\n");
            }//end if-else: check for correct execution in renaming.
         } else {
            System.out.print( fail() + " File Not Found.\n");
         }//end if-else: check for real picture

      }//end for^for(i): check all three filetypes.

      if((mainFile.length() > 510) && input){
         break;
      }//end break statement

   }//end infinite for
}//end trythrefiletypes



public String fail(){return(  "\u001B[31m"   +  "Failed:"   +  "\u001B[0m");}//return "Failed" in the color red.

public String success(){return(  "\u001B[32m"   +  "Success!"  +  "\u001B[0m");}//return "Success" in color green.

/**
 * downloadFile takes a perfect URl and downloads its contents to the local directory.
 *
 * note: the preffered format must be consistent throughout the process.
 *
 * custom variables // methods // classes used:
 *    - String pF
 *
 * @return void.
 */
   public boolean downloadFile(){
      try( BufferedInputStream temp = new BufferedInputStream(mainUrl.openStream());
         FileOutputStream out = new FileOutputStream("extracted_picture." + pF)){
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while((bytesRead = temp.read(dataBuffer, 0, 1024)) != -1){
               out.write(dataBuffer, 0, bytesRead);
            }
         
      }catch(Exception e){e.printStackTrace(); return false;}
      return true;
   }//end downloadFile;



/**
 * the main method has no purpose other than to alert the user that they are not supposed the use the class in this way.
 *
 * @param args : not used in the scope of this project.
 */
	public static void main(String[] args){
	   System.out.println("This class only makes objects,\n you need another class to do the useful things!" + 
      
            
            "\n(unless you were looking specifically just to get an error screen, i wont judge ;)        )");
	
	
	
	}//end main



	public char[] getRandArr(){return randArr;}//end getter for randarr

   public String getMainString(){return mainString;}//end getter for mainstring

   //public URL getMainUrl(){return mainUrl;}//end getter for mainurl

   public File getMainFile(){return mainFile;}//end getter for mainfile

   public String getPF(){return this.pF;}// end getter for pF (preffered Filetype)

   public void setPF(String input){this.pF = input;}//end setter for pF (preffered Filetype)


}//end class
