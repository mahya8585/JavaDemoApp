package com.example.javademoapi.controller;

import com.example.javademoapi.dto.ExpenseApplicationResponseDto;
import com.example.javademoapi.service.ExpenseApplicationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
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
}
