

package co.mil.ejercito.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 *Fecha     Sep 26, 2017
 * @author DAVID ANTONIO QUIJANO RAMOS
 */
public class GetdeGeneradorQR {
    private static final Logger LOG = Logger.getLogger(GetdeGeneradorQR.class.getName());
    
    private static final int qr_image_width = 400;
    private static final int qr_image_height = 400;
    private static final String IMAGE_FORMAT = "png";
    private static final String IMG_PATH = "c:/";
    
    public static void main(String[] args) {//para provarlo
        generarQR("15EBEA440AE");
    }
    
    public static void generarQR(String mensaje){
        LOG.log(Level.INFO, "Entrada del metodo generarQR ");
        try{
        // Encode URL in QR format
        BitMatrix matrix;
        Writer writer = new QRCodeWriter();
        try {
            matrix = writer.encode(mensaje, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);

        } catch (WriterException e) {
            LOG.log(Level.SEVERE, "Error  ", e);
            return;//para que el metodo aborte
        }
        // Create buffered image to draw to
        BufferedImage image = new BufferedImage(qr_image_width,
                qr_image_height, BufferedImage.TYPE_INT_RGB);

        // Iterate through the matrix and draw the pixels to the image
        for (int y = 0; y < qr_image_height; y++) {
            for (int x = 0; x < qr_image_width; x++) {
                int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
                image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
            }
        }
        // Write the image to a file
        FileOutputStream qrCode = new FileOutputStream(IMG_PATH + mensaje + ".png");
        ImageIO.write(image, IMAGE_FORMAT, qrCode);
        qrCode.close();
        }catch(Exception e){
            LOG.log(Level.SEVERE, "Error  ", e);
            
        }
        LOG.log(Level.INFO, "Salida del metodo generarQR ");
        
    }

}
