package com.shimels.mobugs.controllers;

import com.shimels.mobugs.models.Bug;
import com.shimels.mobugs.models.data.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/bug-admin", produces = { MediaType.APPLICATION_JSON_VALUE})
public class BugRestController {

    @Autowired
    private BugRepository bugRepository;

    public BugRepository getBugRepository() {
        return bugRepository;
    }

    public void setBugRepository(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    @GetMapping(value= "/bug")
    public List<Bug> getAllBugs(){
        return bugRepository.findAll();
    }

    @PostMapping(value = "/bug", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Bug createOrSaveBug(@RequestPart (value = "common") String common, @RequestPart (value = "scientific")
            String scientific, @RequestPart (value = "image") MultipartFile image){
        String fileName = "/var/tmp/img/" + image.getOriginalFilename();
        try {
            image.transferTo(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bug newBug = new Bug();
        newBug.setCommonName(common);
        newBug.setScientificName(scientific);
        newBug.setUrl(image.getOriginalFilename());
        return bugRepository.save(newBug);
    }

    @PutMapping("/bug/{id}")
    Bug updateBug(@RequestBody Bug newBug, @PathVariable int id){
        return bugRepository.findById(id).map(bug -> {
            bug.setCommonName(newBug.getCommonName());
            bug.setScientificName(newBug.getScientificName());
            bug.setUrl(newBug.getUrl());
            bug.setTags(newBug.getTags());
            return bugRepository.save(bug);
        }).orElseGet(() -> {
           newBug.setId(id);
           return bugRepository.save(newBug);
        });
    }

    @DeleteMapping("/bug/{id}")
    void deleteBug(@PathVariable int id){
        bugRepository.deleteById(id);
    }
}
