package com.generate.qr.serviceImpl;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.generate.qr.interfaces.CodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

@Service
public class CodeServiceImpl implements CodeService {

	@Override
	public String generateCode(String data) throws Exception {

		String path = "H:\\QR_directory\\".concat(String.valueOf((LocalDateTime.now()).toEpochSecond(ZoneOffset.UTC)))
				.concat(".jpg");

		BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
		MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
		return path;
	}

	@Override
	public String getDataCode(String path) throws Exception {

		BufferedImage image = ImageIO.read(new FileInputStream(path));
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
		Result result = new MultiFormatReader().decode(bitmap);
		System.out.println(result.getText());

		return result.getText();
	}

}
