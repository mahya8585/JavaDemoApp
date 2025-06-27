package com.example.javademoapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
    // URLリンクをレスポンスする
    @GetMapping
    public ResponseEntity<String> index() {
        String response = "<h1>用意されているAPI</h1>"
                + "<ul>"
                + "<li><a href=\"/api/expense/applications\">経費精算申請情報一覧取得</a></li>"
                + "<li><a href=\"/api/health\">ヘルスチェック</a></li>"
                + "<li><a href=\"/api/health/info\">ヘルス情報</a></li>"
                + "</ul>";
        return ResponseEntity.ok(response);
    }

}
