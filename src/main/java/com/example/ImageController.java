package com.example;

import com.example.Image;
import com.example.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) {
        try {
            Image image = imageService.saveImage(file);
            model.addAttribute("message", "Image uploaded successfully: " + image.getName());
            return "upload";
        } catch (IOException e) {
            model.addAttribute("message", "Image upload failed: " + e.getMessage());
            return "upload";
        }
    }
}
