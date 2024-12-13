package com.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // JSON 파일 경로
        Path filePath = Paths.get("src/main/resource/data.json");

        try {
            // JSON 파일 읽기
            String jsonContent = Files.readString(filePath, StandardCharsets.UTF_8);

            // JSON 문자열을 JsonArray로 파싱
            JsonArray jsonArray = JsonParser.parseString(jsonContent).getAsJsonArray();

            // JSON 데이터 출력
            System.out.println("JSON 데이터 출력:");
            for (JsonElement element : jsonArray) {
                System.out.println(element);
            }
        } catch (IOException e) {
            System.out.println("파일 읽는 중 오류 발생: " + e.getMessage());
        }
    }
}
