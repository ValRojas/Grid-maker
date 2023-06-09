import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImportImage {
    private final File selectedFile; //File = represents a file or directory path name
    public ImportImage(File selectedFile) { //receives imported image and label where it will be displayed
        this.selectedFile = selectedFile;
    }

    public BufferedImage importImage() {
        try {
            BufferedImage image = ImageIO.read(selectedFile);
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            int maxWidth = 400;
            int newHeight = (int) (((double) originalHeight / originalWidth) * maxWidth);

            // Resize image to fit window
            BufferedImage resizedImage = new BufferedImage(maxWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = resizedImage.createGraphics();
            graphics.drawImage(image, 0, 0, maxWidth, newHeight, null);
            graphics.dispose();
            image = resizedImage;

            return image;

        } catch (IOException e) {
            e.printStackTrace(); //record error
        }
        return null;
    }
}
