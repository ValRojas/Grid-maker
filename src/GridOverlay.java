import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GridOverlay{
    private final BufferedImage image;
    private final int rows, cols;
    public GridOverlay(BufferedImage image, int rows, int cols) {
        this.image = image;
        this.rows = rows;
        this.cols = cols;
    }
    public BufferedImage createOverlay() {
        int width = image.getWidth();
        int height = image.getHeight();
        int cellWidth = width / cols;
        int cellHeight = height / rows;

        BufferedImage overlay = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = overlay.createGraphics();
        g.drawImage(image, 0, 0, null);

        g.setColor(Color.WHITE);

        // Draw horizontal
        for (int i = 1; i < rows; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }

        // Draw vertical
        for (int j = 1; j < cols; j++) {
            int x = j * cellWidth;
            g.drawLine(x, 0, x, height);
        }

        return overlay;
    }


}