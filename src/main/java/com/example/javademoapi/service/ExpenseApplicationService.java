package com.example.javademoapi.service;

import com.example.javademoapi.config.DataLoader;
import com.example.javademoapi.dto.ExpenseApplicationResponseDto;
import com.example.javademoapi.dto.DatabaseOperationResultDto;
import com.example.javademoapi.entity.ExpenseApplication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseApplicationService {
    
    private final DataLoader dataLoader;
    
    public ExpenseApplicationService(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }
    
    public ExpenseApplicationResponseDto getTop10Applications() {
        List<ExpenseApplication> applications = dataLoader.getTop10ExpenseApplications();
        
        List<ExpenseApplicationResponseDto.ExpenseApplicationDto> applicationDtos = applications.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        
        String message = applications.isEmpty() ? 
                "経費精算申請データがありません" : 
                "経費精算申請情報を正常に取得しました";
        
        return new ExpenseApplicationResponseDto(applicationDtos, applicationDtos.size(), message);
    }
    
    public DatabaseOperationResultDto insertTestData() {
        LocalDateTime executionTime = LocalDateTime.now();
        
        try {
            // Reset test data in DataLoader
            dataLoader.resetTestData();
            
            // Get all data to count
            List<ExpenseApplication> allData = dataLoader.getAllExpenseApplications();
            
            return new DatabaseOperationResultDto(
                    true,
                    "テストデータのリセットが完了しました",
                    allData.size(),
                    executionTime,
                    "RESET_TEST_DATA",
                    String.format("経費精算申請データ %d件をリセットしました。", allData.size())
            );
            
        } catch (Exception e) {
            return new DatabaseOperationResultDto(
                    false,
                    "テストデータのリセットに失敗しました: " + e.getMessage(),
                    0,
                    executionTime,
                    "RESET_TEST_DATA",
                    "エラーが発生しました: " + e.getMessage()
            );
        }
    }
    
    private ExpenseApplicationResponseDto.ExpenseApplicationDto convertToDto(ExpenseApplication application) {
        return new ExpenseApplicationResponseDto.ExpenseApplicationDto(
                application.getId(),
                application.getApplicationDate(),
                application.getApplicantName(),
                application.getDepartment(),
                application.getAmount(),
                application.getReason(),
                application.getPaymentStatus().name(),
                application.getPaymentStatus().getDisplayName()
        );
    }
}
