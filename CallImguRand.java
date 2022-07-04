import java.io.File;
import java.util.*;
/**
 * class CallImguRand does nothing more than convey both the capabilities and the requirements of the ImguRand Object
 * class.
 *
 * @author Obvious Anonymous
 *
 * @version 2.0 reliant
 *
 * note: this class is only a shell used to show the abilites of class Imgurand. without class ImguRand,
 *       this class serves no real purpose. I made it this way so that the objects are easily 
 *       deployable in other use cases.
 *
 * note: to use the ImguRand class, create a new object and use ONLY method tryThreeFileTypes. 
 *       you must pass in a boolean:
 *          - true means that the program will stop when it finds an image.
 *          - false means that the program will keep going regardless:
 *             - useful for collecting large amounts of images
 *
 * note: I recomend you run multiple instances of the program:
 *       - execute each instance in separate directories:
 *          - ensures no overwritten images and overall cleaner execution.
 *
 * note: as of version 2.0, I recommend that you run this on a directory inside an expendable drive:
 *    - it writes a ton of data over and over again, I have been testing this on a usb drive.
 *    - I'm currently working on a way to keep all of the "extracted_picture" files in RAM:
 *       - read doc comments of class ImguRand for more info.
 *
 *
 *
 * all methods:
 *    - main
 *    - thats it :)
 *
 *
 *
 * all public // final variables:
 *    - none :)
 *
 */
public class CallImguRand{

/**
 * the main method is the only method that does anything in this class, it
 * does nothing but call an ImguRand object.
 *
 * @return void.
 */
   public static void main(String[] args){
         ImguRand temp = new ImguRand();
         temp.tryThreeFileTypes(true);
   }//end main
}//end class
