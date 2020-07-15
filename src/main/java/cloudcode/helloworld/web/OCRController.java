package cloudcode.helloworld.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class OCRController {

    @RequestMapping(value="/ocr", method = RequestMethod.POST)
    public String convertOCR(
            @RequestParam("file") MultipartFile file
    ){
        return "OCR";
    }
}