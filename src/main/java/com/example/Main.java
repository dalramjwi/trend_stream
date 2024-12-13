package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            // 파일 경로 지정
            Path filePath = Paths.get("src/main/resource/data.json");
            
            // 파일 읽기
            String content = new String(Files.readAllBytes(filePath));
            
            // 콘솔에 출력
            System.out.println("파일 내용:");
            System.out.println(content);
        } catch (NoSuchFileException e) {
            System.out.println("파일을 찾을 수 없습니다: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("파일을 읽는 중 입출력 오류 발생: " + e.getMessage());
        } 
    }
}
