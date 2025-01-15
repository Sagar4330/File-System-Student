package com.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ServiceFile {
	
	private static final String FILE_PATH = "studentsFile.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<StudentFile> getAllStudents() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return List.of(objectMapper.readValue(file, StudentFile[].class));
    }

    public void saveAllStudents(List<StudentFile> students) throws IOException {
    	
        objectMapper.writeValue(new File(FILE_PATH), students);
    }

    public void initializeStorage() throws IOException {
    	
        if (!Files.exists(Paths.get(FILE_PATH))) {
        	
            Files.createFile(Paths.get(FILE_PATH));
            saveAllStudents(new ArrayList<>());
        }
    }

}
