import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImportImage {
    private final File selectedFile; //File = represents a file or directory path name
    private JLabel imageLabel;

    public ImportImage(File selectedFile, JLabel imageLabel) { //recieves imported image and label where it will be displayed
        this.selectedFile = selectedFile;
        this.imageLabel = imageLabel;
    }

    public void importImage() {

        try {
            BufferedImage image = ImageIO.read(selectedFile);
            ImageIcon icon = new ImageIcon(image);
            imageLabel.setIcon(icon);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
