package cloudcode.ocr.web.service.impl;

import cloudcode.ocr.web.service.OCRService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class OCRServiceImpl implements OCRService {

    @Override
    public String getOCRString(MultipartFile file) throws IOException, TesseractException {
        byte[] bytes = file.getBytes();
        File temp = File.createTempFile(file.getName(),".tmp");
        Path path = temp.toPath();
        Files.write(path,bytes);
        File convFile = convert(file);

        ITesseract instance = new Tesseract();

//        File f = File.createTempFile("tessdata",".tmp");
//        IOUtils.copy(resourceFile.getInputStream(),new FileOutputStream(f));
//        File directory = new File(file.getParent());
//        String courseFile = null;
//        try {
//            courseFile = directory.getCanonicalPath();
//        } catch (IOException e) {
//            return file.getParent();
//        }
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("eng");// 英文库识别数字比较准确
//
        tesseract.setDatapath("/tessdata/");
//        File file1 = new File("/tessdata/eng.cube.bigrams");
//        return  file1.getAbsolutePath();
        String text = tesseract.doOCR(convert(file));

        return text;
    }

    private static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return convFile;
    }

    private String getParentDirectoryFromJar() {
        String dirtyPath = getClass().getResource("").toString();
//        String jarPath = dirtyPath.replaceAll("^.*file:/", ""); //removes file:/ and everything before it
//        jarPath = jarPath.replaceAll("jar!.*", "jar"); //removes everything after .jar, if .jar exists in dirtyPath
//        jarPath = jarPath.replaceAll("%20", " "); //necessary if path has spaces within
//        if (!jarPath.endsWith(".jar")) { // this is needed if you plan to run the app using Spring Tools Suit play button.
//            jarPath = jarPath.replaceAll("/classes/.*", "/classes/");
//        }
//        String directoryPath = Paths.get(jarPath).getParent().toString(); //Paths - from java 8
        return dirtyPath;
    }

    public static boolean isPathValid(String path) {

        try {
            File file = new File("/tessdata/eng.cube.bigrams");

        } catch (InvalidPathException ex) {
            return false;
        }

        return true;
    }
}