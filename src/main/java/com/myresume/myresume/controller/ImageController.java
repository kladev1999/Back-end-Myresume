package com.myresume.myresume.controller;

import com.myresume.myresume.entity.UersGeneralEntity;
import com.myresume.myresume.entity.UsersJobEntity;
import com.myresume.myresume.reponse.UserGeneralResponse;
import com.myresume.myresume.reponse.UsersJobResponse;
import com.myresume.myresume.repository.UserGenRepository;
import com.myresume.myresume.repository.UserJobRepository;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

  @Autowired
  UserGenRepository userRepository;

  @Autowired
  UserJobRepository userJobRepository;

  private static final String UPLOAD_DIR = "./profileUserGeneral/";

  private static final String UPLOAD_PROFILEJOB = "./profileUserJob/";

  private static final String UPLOAD_RESUME = "./Resume/";

  @PostMapping("/{id}/uploadProfileGeneral")
  public ResponseEntity<UserGeneralResponse> uploadImage(
    @PathVariable Integer id,
    @RequestParam("file") MultipartFile file
  ) {
    UersGeneralEntity user = userRepository.findById(id).orElseThrow();

    if (user == null) {
      return ResponseEntity.ok(new UserGeneralResponse("ok", "200", user));
    }

    try {
      String fileName = saveImage(file);
      user.setUserGenImg(fileName);
      userRepository.save(user);

      return ResponseEntity.ok(new UserGeneralResponse("ok", "200", user));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok(new UserGeneralResponse("ok", "200", user));
  }

  @PostMapping("/{id}/uploadProfileJob")
  public ResponseEntity<UsersJobResponse> uploadImageProfileJob(
    @PathVariable Integer id,
    @RequestParam("file") MultipartFile file
  ) {
    UsersJobEntity user = userJobRepository.findById(id).orElseThrow();

    if (user == null) {
      return ResponseEntity.ok(new UsersJobResponse("ok", "200", user));
    }

    try {
      String fileName = saveImageProfileJob(file);
      user.setUserJobImg(fileName);
      userJobRepository.save(user);

      return ResponseEntity.ok(new UsersJobResponse("ok", "200", user));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok(new UsersJobResponse("ok", "200", user));
  }

  @PostMapping("/{id}/uploadResume")
  public ResponseEntity<UsersJobResponse> uploadImageResume(
    @PathVariable Integer id,
    @RequestParam("file") MultipartFile file
  ) {
    UsersJobEntity user = userJobRepository.findById(id).orElseThrow();

    if (user == null) {
      return ResponseEntity.ok(new UsersJobResponse("ok", "200", user));
    }

    try {
      String fileName = saveImageResume(file);
      user.setUserJobResumeImg(fileName);
      userJobRepository.save(user);

      return ResponseEntity.ok(new UsersJobResponse("ok", "200", user));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok(new UsersJobResponse("ok", "200", user));
  }

  @GetMapping("/getImageGenneral/{imageName:.+}")
  public ResponseEntity<Resource> viewImageGeneral(
    @PathVariable String imageName
  ) {
    Path imagePath = Paths.get(UPLOAD_DIR + imageName);
    Resource imageResource;

    try {
      imageResource = new UrlResource(imagePath.toUri());
      if (imageResource.exists() && imageResource.isReadable()) {
        return ResponseEntity
          .ok()
          .contentType(MediaType.IMAGE_JPEG)
          .body(imageResource);
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/getImageJob/{imageName:.+}")
  public ResponseEntity<Resource> viewImageJob(@PathVariable String imageName) {
    Path imagePath = Paths.get(UPLOAD_PROFILEJOB + imageName);
    Resource imageResource;

    try {
      imageResource = new UrlResource(imagePath.toUri());
      if (imageResource.exists() && imageResource.isReadable()) {
        return ResponseEntity
          .ok()
          .contentType(MediaType.IMAGE_JPEG)
          .body(imageResource);
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/getImageResume/{imageName:.+}")
  public ResponseEntity<Resource> viewImageResume(
    @PathVariable String imageName
  ) {
    Path imagePath = Paths.get(UPLOAD_RESUME + imageName);
    Resource imageResource;

    try {
      imageResource = new UrlResource(imagePath.toUri());
      if (imageResource.exists() && imageResource.isReadable()) {
        return ResponseEntity
          .ok()
          .contentType(MediaType.IMAGE_JPEG)
          .body(imageResource);
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return ResponseEntity.notFound().build();
  }

  private String saveImage(MultipartFile file) throws IOException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String currentTimeStamp = dateFormat.format(new Date());

    String originalFilename = file.getOriginalFilename();
    String fileExtension = originalFilename.substring(
      originalFilename.lastIndexOf(".")
    );

    String newFileName = currentTimeStamp + fileExtension;

    Path targetPath = Paths.get(UPLOAD_DIR + newFileName);
    Files.copy(
      file.getInputStream(),
      targetPath,
      StandardCopyOption.REPLACE_EXISTING
    );

    return newFileName;
  }

  private String saveImageProfileJob(MultipartFile file) throws IOException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String currentTimeStamp = dateFormat.format(new Date());

    String originalFilename = file.getOriginalFilename();
    String fileExtension = originalFilename.substring(
      originalFilename.lastIndexOf(".")
    );

    String newFileName = currentTimeStamp + fileExtension;

    Path targetPath = Paths.get(UPLOAD_PROFILEJOB + newFileName);
    Files.copy(
      file.getInputStream(),
      targetPath,
      StandardCopyOption.REPLACE_EXISTING
    );

    return newFileName;
  }

  private String saveImageResume(MultipartFile file) throws IOException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String currentTimeStamp = dateFormat.format(new Date());

    String originalFilename = file.getOriginalFilename();
    String fileExtension = originalFilename.substring(
      originalFilename.lastIndexOf(".")
    );

    String newFileName = currentTimeStamp + fileExtension;

    Path targetPath = Paths.get(UPLOAD_RESUME + newFileName);
    Files.copy(
      file.getInputStream(),
      targetPath,
      StandardCopyOption.REPLACE_EXISTING
    );

    return newFileName;
  }
}
