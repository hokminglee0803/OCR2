package cloudcode.ocr.web;

import cloudcode.ocr.web.service.OCRService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class OCRController {

    @Autowired
    private OCRService ocrService;

    @RequestMapping(value="/ocr", method = RequestMethod.POST)
    public String convertOCR(
            @RequestParam("file") MultipartFile file
    ){
        try {
            return  ocrService.getOCRString(file);
        }catch (IOException e) {
            e.printStackTrace();
            return "File not Correct"+e.getMessage();
        } catch (TesseractException e) {
            e.printStackTrace();
            return "OCR convertion Error";
        } catch (Exception e){
            return "Any Error: "+ e.getMessage();
        }
    }
}