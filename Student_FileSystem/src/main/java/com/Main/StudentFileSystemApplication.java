package com.Main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentFileSystemApplication {
	
	
	 @Autowired
	    private ServiceFile fileStorageService;

	   
	

	public static void main(String[] args) {
		
		SpringApplication.run(StudentFileSystemApplication.class, args);
	}
	
	  
	    public void run(String... args) throws Exception {
	        fileStorageService.initializeStorage();
	    }

}
