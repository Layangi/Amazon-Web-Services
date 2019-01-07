package com.layangi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GreetingController {

	private AmazonClient amazonClient;

	@GetMapping("/myhome")
	public String greeting() {
		return "myhome";
	}

	@PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file1") MultipartFile file) {
     //   return this.amazonClient.uploadFile(file);
		amazonClient.uploadFile(file);
        return "end";
    }

	@Autowired
	GreetingController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}

	@DeleteMapping("/deleteFile")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	
	}

}
