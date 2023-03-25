package com.init.ecom.Y.Controllers;

import com.init.ecom.Y.Exceptions.ResourceNotFoundException;
import com.init.ecom.Y.model.Profile;
import com.init.ecom.Y.repositories.ProfileRepository;
import com.mysql.cj.jdbc.Blob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping
    public Profile createProfile(
            @RequestParam("name") String name,
            @RequestParam("mobileNumber") Long mobileNumber,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("password") String password) throws IOException, IOException {

        Profile profile = new Profile();
        profile.setName(name);
        profile.setMobileNumber(mobileNumber);
        profile.setPicture(picture.getBytes());
        profile.setPassword(password);

        return profileRepository.save(profile);
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<Profile> getProfileById(@PathVariable(value = "mobileNumber") Long profileNumber)
            throws ResourceNotFoundException {
        Profile profile = profileRepository.findById(profileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found for this id :: " + profileNumber));
        return ResponseEntity.ok().body(profile);
    }

}
