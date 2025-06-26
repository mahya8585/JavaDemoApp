package com.example.javademoapi.controller;

import com.example.javademoapi.dto.ExpenseApplicationResponseDto;
import com.example.javademoapi.dto.DatabaseOperationResultDto;
import com.example.javademoapi.service.ExpenseApplicationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    
    private final ExpenseApplicationService expenseApplicationService;
    
    public ExpenseController(ExpenseApplicationService expenseApplicationService) {
        this.expenseApplicationService = expenseApplicationService;
    }
    
    /**
     * 経費精算申請情報一覧取得API
     * 申請日が新しいものから10件取得
     * 
     * @return XML形式の経費精算申請情報一覧
     */
    @GetMapping(value = "/applications", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ExpenseApplicationResponseDto> getExpenseApplications() {
        ExpenseApplicationResponseDto response = expenseApplicationService.getTop10Applications();
        return ResponseEntity.ok(response);
    }
    
    /**
     * テストデータ登録API
     * Azure SQL Databaseにテストデータを登録
     * 
     * @return XML形式の実行結果
     */
    @PostMapping(value = "/data/insert", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<DatabaseOperationResultDto> insertTestData() {
        DatabaseOperationResultDto result = expenseApplicationService.insertTestData();
        
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.internalServerError().body(result);
        }
    }
    
    /**
     * 経費精算申請情報一覧取得API（JSON対応版）
     * Content-Typeやクエリパラメータでフォーマットを指定可能
     * 
     * @param format フォーマット指定（xml/json）
     * @return 経費精算申請情報一覧
     */
    @GetMapping("/applications/flexible")
    public ResponseEntity<ExpenseApplicationResponseDto> getExpenseApplicationsFlexible(
            @RequestParam(value = "format", defaultValue = "xml") String format) {
        ExpenseApplicationResponseDto response = expenseApplicationService.getTop10Applications();
        
        if ("json".equalsIgnoreCase(format)) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(response);
        }
    }
    
    /**
     * テストデータ登録API（JSON対応版）
     * Content-Typeやクエリパラメータでフォーマットを指定可能
     * 
     * @param format フォーマット指定（xml/json）
     * @return 実行結果
     */
    @PostMapping("/data/insert/flexible")
    public ResponseEntity<DatabaseOperationResultDto> insertTestDataFlexible(
            @RequestParam(value = "format", defaultValue = "xml") String format) {
        DatabaseOperationResultDto result = expenseApplicationService.insertTestData();
        
        MediaType contentType = "json".equalsIgnoreCase(format) ? 
                MediaType.APPLICATION_JSON : MediaType.APPLICATION_XML;
        
        if (result.isSuccess()) {
            return ResponseEntity.ok()
                    .contentType(contentType)
                    .body(result);
        } else {
            return ResponseEntity.internalServerError()
                    .contentType(contentType)
                    .body(result);
        }
    }
}
