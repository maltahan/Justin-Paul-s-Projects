
/**
 * Write a description of class Prgm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.Image;
import java.awt.image.*;
import java.awt.color.ColorSpace;
import sun.awt.image.ByteInterleavedRaster;

public class Prgm {
    private static BufferedImage scannedImage;
    private static double[] histogram = new double[255];
    private static final int THRESHOLD_ISBLACK = 160;
    private static final double THRESHOLD_ISFILLED = 0.45;
   // private static ArrayList<int> xPosns;
   // private static ArrayList<int> yPosns;
   
    public static BufferedImage test() throws Exception{
        BufferedImage img = getGrayscaleImage("150color.tif");
        convertToBlackAndWhite(img);
        return img;
    }
    
    public static BufferedImage getGrayscaleImage(String path) throws Exception{
        BufferedImage img = ImageViewer.getImage(path);
        convertToGrayscale(img);
        return img;
    }
    
    public static byte[] getImageData(BufferedImage img) throws Exception{
        return ((ByteInterleavedRaster)img.getData()).getDataStorage();
    }
    
    private static int unsignedByteRepresentation(byte b){
        int i = b & 0xff;  // Converts byte to unsigned equivalent. (Converts to int and masks)
        return i;
    }
    
    public static double[] getHistogram(BufferedImage img) throws Exception{
        int[] hist1 = new int[256];
        double numDig = img.getWidth() * img.getHeight();
        for (byte b : getImageData(img)){
            hist1[unsignedByteRepresentation(b)]++;
        }
        double[] histogram = new double[256];
        for (int i = 0; i < 256; i++) {
            histogram[i] = hist1[i]/numDig;
        }
        return histogram;
    }
        
    
    private static void convertToGrayscale(BufferedImage source) { 
        BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null); 
        op.filter(source, source);
    }
    
    private static void convertToBW(ByteInterleavedRaster rast) {
        byte[] iarr = rast.getDataStorage();
        byte[] oarr = new byte[iarr.length];
        for (int i = 0; i < iarr.length; i++) {
            oarr[i] = unsignedByteRepresentation(iarr[i]) <= THRESHOLD_ISBLACK ? (byte)0x0 : (byte)0xFF;
        }
        rast.putByteData(0,0,rast.getWidth(),rast.getHeight(),oarr);
    }
    
    public static void convertToBlackAndWhite(BufferedImage source) {
        ByteInterleavedRaster rast = (ByteInterleavedRaster)source.getData();
        convertToBW(rast);
        source.setData(rast);
    }
    
    public static int getPixel(BufferedImage img, int x, int y) {
        return img.getRGB(x, y) & 0xFF; // Returns blue pixel data for coordinate.  RGB should all be same.
    }
    
    public static String getAnswer(BufferedImage img, int questNum) {
        String[] letters = {"A","B","C","D","E"};
        String s = "";
        double conversionFactor = img.getHeight()/(11*72);    // ASSUMES 8.5" x 11" PAPER!
        int x0 = (int) ((questNum - 1)/25 * (131.25 * conversionFactor) + (81 * conversionFactor));
        int y1 = (int) ((questNum - 1)%25 * (22.87 * conversionFactor) + (196 * conversionFactor));
        
        for (int choice = 0; choice < 5; choice++){
            int x1 = (int) (x0 + choice * (19.7 * conversionFactor));
            int filledPix = 0;
            int checkedPix = 0;
            
            System.out.println("Checking \"" + letters[choice] + "\"");
            for (int r = 0; r < (5.25 * conversionFactor); r++) {
                for (int c = 0; c < 11.25 * conversionFactor; c++) {
//                     System.out.println("Checking (" + (x1+c) + "," + (y1+r) + ")");
                    if (getPixel(img, x1 + c, y1 + r) == 0) {
//                         System.out.println("Found pixel!");
                        filledPix++;
                    }
                    checkedPix++;
                }
            }
            System.out.println("Filled Pixels = " + filledPix);
            System.out.println("Checked Pixels= " + checkedPix);
            System.out.println("Percent Filled= " + (100 * filledPix/((double) checkedPix)));
            System.out.println();
            if (filledPix/((double) checkedPix) >= THRESHOLD_ISFILLED) {
                s += letters[choice];
            }
        }
        return s;
    }
    
    public static String getAnswerTest(int questNum) throws Exception {
        return getAnswer(test(), questNum);
    }
     
}