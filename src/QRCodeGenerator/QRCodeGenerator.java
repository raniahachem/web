/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QRCodeGenerator;

/**
 *
 * @author abdel
 */
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class QRCodeGenerator {
    public static Image generateQRCode(String qrCodeText, int width, int height) {
        try {
            // Create a QR code writer
            QRCodeWriter qrWriter = new QRCodeWriter();

            // Set encoding hints for the QR code writer
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Generate the QR code matrix
            BitMatrix matrix = qrWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, width, height, hints);

            // Create a BufferedImage from the QR code matrix
            BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    qrImage.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            // Convert the BufferedImage to a JavaFX Image
            return SwingFXUtils.toFXImage(qrImage, null);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}

