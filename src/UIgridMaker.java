import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UIgridMaker extends JFrame{
    private JPanel background;
    private JButton ImportButton;
    private JLabel imageLabel;
    private JTextField RowsValue, ColValue;
    private JButton gridITButton;
    private BufferedImage image;
    private float imageAspectRatio;

    public UIgridMaker(){
        super("Grid app"); //title
        setContentPane(background);//set content panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit app when close

        this.imageLabel.setVerticalAlignment(JLabel.CENTER);
        this.imageLabel.setHorizontalAlignment(JLabel.CENTER);

        ImportButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg");
            chooser.setFileFilter(filter);

            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) { //user has selected an image

                File selectedFile = chooser.getSelectedFile();
                ImportImage importer = new ImportImage(selectedFile); //resize
                image = importer.importImage();
                imageAspectRatio = (float) image.getWidth() / image.getHeight();

                GridOverlay overlay = new GridOverlay(image, 5,Math.round(imageAspectRatio * 5)); //put overlay on it (default)
                BufferedImage overlayImage = overlay.createOverlay();

                ImageIcon icon = new ImageIcon(overlayImage);
                imageLabel.setIcon(icon); //sets when input empty
            }
        });

        gridITButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Grid Image");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG Image", "jpg", "jpeg");
            fileChooser.setFileFilter(filter);

            int userSelection = fileChooser.showSaveDialog(UIgridMaker.this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String fileName = fileToSave.getName();

                if (!fileName.endsWith(".jpg")) { //sets extension
                    fileToSave = new File(fileToSave.getParentFile(), fileName + ".jpg");
                }

                ImageIcon icon = (ImageIcon) imageLabel.getIcon();
                Image image = icon.getImage();

                // Resize image to delete black margins (delete test())
                BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = bufferedImage.createGraphics();
                graphics2D.drawImage(image, 0, 0, null);
                graphics2D.dispose();


                try {
                    ImageIO.write(bufferedImage, "jpg", fileToSave);
                    System.out.println("File saved successfully.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        /* S: Handle grid overlay maker*/
        RowsValue.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleValueChange(true);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                handleValueChange(true);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                handleValueChange(true);
            }
        });
        ColValue.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleValueChange(false);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                handleValueChange(false);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                handleValueChange(false);
            }
        });
    }

    private void handleValueChange(boolean isRow) {
        int rows;
        int cols;
        try {
            if (isRow) {
                rows = Integer.parseInt(RowsValue.getText());
                cols = Math.round(((float) image.getWidth() / image.getHeight()) * rows);
            } else {
                cols = Integer.parseInt(ColValue.getText());
                rows = Math.round(((float) image.getHeight() / image.getWidth()) * cols);
            }
        } catch (NumberFormatException ex) {
            rows = 5;
            cols = Math.round(((float) image.getWidth() / image.getHeight()) * rows);
        }
        updateOverlay(rows, cols);
    }
    /* E: Handle grid overlay maker*/

    /* S: Return show image*/
    private void updateOverlay(int rows, int cols) {
        GridOverlay overlay = new GridOverlay(image, rows, cols); //user sets rows-cols
        BufferedImage overlayImage = overlay.createOverlay();
        ImageIcon icon = new ImageIcon(overlayImage);
        imageLabel.setIcon(icon);
    }
    /* E: Return*/

}
