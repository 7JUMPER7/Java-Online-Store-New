package com.example.onlinestorenew.controllers;

import com.example.onlinestorenew.models.GoodEntity;
import com.example.onlinestorenew.models.ImageEntity;
import com.example.onlinestorenew.services.GoodService;
import com.example.onlinestorenew.services.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    @GetMapping(value = "/images/{id}")
    public void getGoodPreviewImage(@PathVariable(name = "id") Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");

        ImageService imageService = new ImageService();
        ImageEntity image = imageService.getImage(id);

        InputStream is = new ByteArrayInputStream(image.getData());
        IOUtils.copy(is, response.getOutputStream());
    }
}
