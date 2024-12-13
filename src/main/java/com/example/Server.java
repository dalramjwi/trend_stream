package com.example;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
public class Server {
    public static void main(String[] args) throws IOException {
        // 1. 서버 생성 (포트: 8080, 백로그: 0)
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // 2. URL 경로와 핸들러 연결
        server.createContext("/", new HelloHandler());

        // 3. 서버 시작
        server.start();
        System.out.println("서버가 시작되었습니다. http://localhost:8080 에 접속하세요.");
    }
}
// HTTP 요청을 처리할 핸들러 클래스
class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // 1. 응답 메시지
        String response = "Hello, World!";

        // 2. 응답 헤더 설정 (상태 코드 200 OK, Content-Length)
        exchange.sendResponseHeaders(200, response.getBytes().length);

        // 3. 응답 본문 출력
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}