/* CS1101  Intro to Computer Science 
Instructor: Aguirre 
Comprehensive Lab 2 
By including my name below, I confirm that:
-	I am submitting my original work.
-	If I include code obtained from another source or I received help I am giving attribution to those sources as comments.
-	This submission does not incur in any academic dishonesty practice as described in the course syllabus.

//References
//Diego Rodriguez (Peer Leader) - recieved helped on options A, B, C
//Dalia Atiyeh (TA)- recieved help on methods replaceGreenPixels() and replaceGreenPixelsRecursive()
//Diego Aguirre (Instructor)- recieved help on replaceGreenPixelsRecursive()

Modified and submitted by: [Aaron Zambrano] 
*/
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Scanner;

public class AwesomeGreenScreenReplacer {
	public static void main(String[] args) throws Exception 
	{
		System.out.println("Welcome :) This application will allow you to use a green screen picture and a background picture of your choosing to create a new image.");
		System.out.println("");
		//Scanner for user input
		Scanner scnr = new Scanner(System.in);
		String userAns;
		String userBackPic = "";
		boolean valid = false;
		/////////////////////////////////////////////////First Question///////////////////////////////////////////////////////////////
        String userGreenPic = getFileName();                         //name of green picture 
		Color[][] greenPicArray = readImage(userGreenPic);           //2d color array of user's green picture
		/////////////////////////////////////////////////Second Question//////////////////////////////////////////////////////////////
		do{
			System.out.println("Use backgroundPic.jpg as the file name of the background picture? [Y/N]: ");
			userAns = scnr.nextLine();
			if("Y".equalsIgnoreCase(userAns)){
				userBackPic = "backgroundPic.jpg";
				valid = true;
			} //End of Y option
			else if("N".equalsIgnoreCase(userAns)){
				System.out.println("Please select an option(A, B, or C)");
				System.out.println("A: Use the default background image provided" );
				System.out.println("B: Enter the file your own custom background image" );
				System.out.println("C: Use the folder path that provides your background images");
				String userOp = scnr.nextLine();   //user input for options A, B, or C
				if("A".equalsIgnoreCase(userOp)){
					userBackPic = "backgroundPic.jpg";
					valid = true;
				}
				else if("B".equalsIgnoreCase(userOp)){
					System.out.println("Please enter your custom background image (.jpg): ");
					userBackPic = scnr.nextLine();
					valid = true;
				}
				else if ("C".equalsIgnoreCase(userOp)){
					String folderPath = "C:\\Users\\Crene\\Desktop\\code\\backgrounds"; // folder path 
					String[] pathname = getFilesInFolder(folderPath);
					replaceGreenPixelsRecursive(greenPicArray, pathname,pathname.length - 1); //ask if they want to save or not
					System.out.println("Thank you :)");
					System.exit(0);
					}
				else{
					System.out.println("Invalid Please enter either A, B or C");
				}
			} //end of N option
			else{
				System.out.println("Invalid: Please enter either Y or N");
			} 
		}while(!valid);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Color[][] backgroundPicArray = readImage(userBackPic);                            //2d color array of user's background image
		Color[][] resultingImage = replaceGreenPixels(greenPicArray, backgroundPicArray); //resulting image of the user's picture
		saveNewImage(resultingImage);                                                     // calls saveNewImage(custom method I made) to ask the user if they wish to save their image
		System.out.println("Thank you :)");
		System.exit(0);
	} //End of main
	
	public static String getFileName(){
		Scanner scnr = new Scanner(System.in);
		String userAns;
		String userGreenPic = "";
		boolean validFile = false;
		do{ 
			System.out.println("Use greenScreenPic.jpg as the file name of the green screen picture? [Y/N]:");
			userAns = scnr.nextLine();
			if("Y".equalsIgnoreCase(userAns)) {
				userGreenPic = "greenScreenPic.jpg";
				validFile = true;
			}// end of Y option
			else if ("N".equalsIgnoreCase(userAns)){
				System.out.println("Please type the file name of your green screen picture (.jpg):");
				userGreenPic = scnr.nextLine();
				validFile = true; 
			}//end of N option
			else{
				System.out.println("Invalid: Please enter either Y or N");
			}
		}while(!validFile);
		return userGreenPic;
	}
	/**
	 * This method receives the color values associated with a pixel.
	 * It returns true if and only if the given pixel is green
	 * 
	 * @param red	Red value of the pixel
	 * @param green	Green value of the pixel
	 * @param blue	Blue value of the pixel
	 * @return true if green; false, otherwise.
	 */
	public static boolean isGreenPixel(int red, int green, int blue) 
	{
		if((red<=200)&& green>190 && blue < 120){
		//if((red<=120)&& green>110 && blue < 210){  //extra credit green picture
		return true;
		}
		return false;
	}
	/**
	 * This method creates a new image where the the green pixels in greenScreenImg 
	 * are replaced with the corresponding background pixels from backgroundImg 
	 * 
	 * @param greenScreenImg 2D array that contains the pixels of a green screen image
	 * @param backgroundImg	 2D array that contains the pixels of a background image
	 * 
	 * @return Image where the green pixels from greenScreenImg were replace with the 
	 * corresponding background pixels from backgroundImg
	 */
	public static Color[][] replaceGreenPixels(Color[][] greenScreenImg , Color[][] backgroundImg) 
	{
		int redValue = 0;
		int greenValue = 0;
		int blueValue = 0;
		Color [][] newImage = new Color [greenScreenImg.length][greenScreenImg[0].length];
		for(int i = 0; i < greenScreenImg.length; i++){
			for(int j = 0; j < greenScreenImg[i].length;j++){
				redValue = greenScreenImg[i][j].getRed();
				greenValue = greenScreenImg[i][j].getGreen();
				blueValue = greenScreenImg[i][j].getBlue();
				if(isGreenPixel(redValue, greenValue, blueValue)){
					newImage [i][j] = backgroundImg[i][j];     //replacing green pixels to pixels of background image
				}
				else{
				newImage[i][j] = greenScreenImg[i][j];  //keep the pixel if it is not green
				}                
			}
		} //end of nested for loop
		return newImage; //new image 2d array
	}
    
	/**
	 * Recursive method to process multiple background images 
	 * 
	 * @param greenScreenImage
	 * @param backgroundImagesPaths
	 * @param currentIndex
	 */
	public static void replaceGreenPixelsRecursive(Color[][] greenScreenImage, String[] backgroundImagesPaths, int currentIndex) 
	{

		//new color 2d array for background
		Color [][] newBackground = readImage(backgroundImagesPaths[currentIndex]);
		Color[][] result = replaceGreenPixels(greenScreenImage, newBackground); //result of new image 
		// base case 1
		if(currentIndex == 0){
			result = replaceGreenPixels(greenScreenImage, newBackground);
			saveNewImage(result);
		}
		else{
			saveNewImage(result);
			replaceGreenPixelsRecursive(greenScreenImage, backgroundImagesPaths, currentIndex - 1);
		}
	}
	/*
	*This method asks the user if they wish to save their image
	* @param aBackground
	*/
	/////////////////////////////////////////////////////saveNewImage/////////////////////////////////////////////////////////////////////////////////
	public static void saveNewImage(Color[][] aBackground){
		Scanner scnr = new Scanner(System.in);
		
		displayImage(aBackground);
		System.out.println("Do you wish save your image? (Y/N)"); 
		String userAns = scnr.nextLine();
		if("Y".equalsIgnoreCase(userAns)){ 
			System.out.println("Please enter the name of your new image( Ex: newPic.jpg)");
			String userNewPic = scnr.nextLine();
			saveImage(aBackground, userNewPic);                   //saves the custom image of the user
			System.out.println("Thank you! :)");
			System.exit(0);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	/**
	 * This method saves a given image to disk
	 * 
	 * @param imagePixels Image to be saved
	 * @param fileName	  Name of the file
	 */
	public static void saveImage(Color[][] imagePixels, String fileName) 
	{
		try {
		    // Convert to BufferedImage
		    BufferedImage bi = createImageFromPixelArray(imagePixels);
		    
		    //Save file
		    File outputfile = new File(fileName);
		    ImageIO.write(bi, "jpg", outputfile);
		} catch (IOException e) {
		    System.out.println("Error: " + e.toString());
		}
	}
	
	/**
	 * This method displays the imaged passed as input
	 * 
	 * @param colors Image to be displayed
	 * 
	 */
    public static void displayImage(Color[][] imagePixels) 
    {
        BufferedImage image = createImageFromPixelArray(imagePixels);
        
        ImageIcon icon=new ImageIcon(image);
        
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(1280,720);
        
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
    /**
     * This method reads an image given its path
     * 
     * @param filePath	The path of the image to be read
     * @return			A 2D array of pixels representing the image
     * @throws Exception Exception is thrown when the file does not exist
     */
	 
	 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// DIEGO (INTRUCTOR) MODIFIED IT
    public static Color[][] readImage(String filePath)  {     
		Color[][] colors = null;
		try{
			File imageFile = new File(filePath);
			BufferedImage image = ImageIO.read(imageFile);
			colors = new Color[image.getWidth()][image.getHeight()];

			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					colors[x][y] = new Color(image.getRGB(x, y));
				}
			}
		}
		catch(Exception e)
		{
			
		}
        return colors;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Reads all the files in a given folder
     * 
     * @param folderPath The path to the folder
     * 
     * @return List of all files inside of the input folder
     */
    public static String[] getFilesInFolder(String folderPath)
    {

    	ArrayList<String> fileArrayList = new ArrayList<String>();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        fileArrayList.add(file.getAbsolutePath());
		    }
		}
		
		return fileArrayList.toArray(new String[fileArrayList.size()]);
    }
    
    /**
     * This method receives a 2D array of pixel colors and returns
     * its equivalent BufferedImage representation
     * 
     * @param imagePixels Pixel values of the image to be converted // new image 2d array to an actual image 
     * 
     * @return BufferedImage representation of the input
     */
    public static BufferedImage createImageFromPixelArray(Color[][] imagePixels) {
        BufferedImage image = new BufferedImage(imagePixels.length, imagePixels[0].length, BufferedImage.TYPE_INT_RGB);
        
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, imagePixels[x][y].getRGB());
            }
        }
        return image;
    }
}