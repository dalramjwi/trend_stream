package com.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

public class Server {

    public static void main(String[] args) throws IOException {
        // 서버 생성 (포트: 8080)
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // 경로 등록
        server.createContext("/", new HtmlHandler());       // index.html 반환
        server.createContext("/data", new JsonHandler());   // JSON 데이터 반환

        // 서버 시작
        server.start();
        System.out.println("서버가 시작되었습니다. http://localhost:8080 에 접속하세요.");
    }
}

// 핸들러: index.html 반환
class HtmlHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        File file = new File("src/main/resource/Index.html");
        byte[] response = new FileInputStream(file).readAllBytes();

        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response);
        }
    }
}

// 핸들러: JSON 데이터 반환
class JsonHandler implements HttpHandler {
    private static final Gson gson = new Gson();
    private static final List<Map<String, String>> data = loadJsonData();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = gson.toJson(data);

        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    private static List<Map<String, String>> loadJsonData() {
        try (Reader reader = new FileReader("src/main/resource/data.json")) {
            return gson.fromJson(reader, new TypeToken<List<Map<String, String>>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
