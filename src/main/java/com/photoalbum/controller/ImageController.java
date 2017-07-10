package com.photoalbum.controller;

import com.photoalbum.model.image.Image;
import com.photoalbum.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album-images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(final ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Image getImage() {
        return imageService.getOneImage();
    }


}
