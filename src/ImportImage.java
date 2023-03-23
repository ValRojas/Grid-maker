import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImportImage {
    private final File selectedFile; //File = represents a file or directory path name
    private final JLabel imageLabel;

    public ImportImage(File selectedFile, JLabel imageLabel) { //receives imported image and label where it will be displayed
        this.selectedFile = selectedFile;
        this.imageLabel = imageLabel;
        this.imageLabel.setVerticalAlignment(JLabel.CENTER);
        this.imageLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public BufferedImage importImage() {
        try {
            BufferedImage image = ImageIO.read(selectedFile);
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            int maxWidth = 400;
            int newHeight = (int) (((double) originalHeight / originalWidth) * maxWidth);

            // Resize
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
