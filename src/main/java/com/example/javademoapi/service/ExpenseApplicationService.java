package com.example.javademoapi.service;

import com.example.javademoapi.dto.ExpenseApplicationResponseDto;
import com.example.javademoapi.dto.DatabaseOperationResultDto;
import com.example.javademoapi.entity.ExpenseApplication;
import com.example.javademoapi.repository.ExpenseApplicationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExpenseApplicationService {
    
    private final ExpenseApplicationRepository expenseApplicationRepository;
    
    public ExpenseApplicationService(ExpenseApplicationRepository expenseApplicationRepository) {
        this.expenseApplicationRepository = expenseApplicationRepository;
    }
    
    @Transactional(readOnly = true)
    public ExpenseApplicationResponseDto getTop10Applications() {
        Pageable pageable = PageRequest.of(0, 10);
        List<ExpenseApplication> applications = expenseApplicationRepository
                .findTop10ByOrderByApplicationDateDesc(pageable);
        
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
            // Clear existing data
            expenseApplicationRepository.deleteAll();
            
            // Create test data
            List<ExpenseApplication> testData = createTestData();
            
            // Insert test data
            List<ExpenseApplication> savedData = expenseApplicationRepository.saveAll(testData);
            
            return new DatabaseOperationResultDto(
                    true,
                    "テストデータの登録が完了しました",
                    savedData.size(),
                    executionTime,
                    "INSERT_TEST_DATA",
                    String.format("経費精算申請データ %d件を正常に登録しました。", savedData.size())
            );
            
        } catch (Exception e) {
            return new DatabaseOperationResultDto(
                    false,
                    "テストデータの登録に失敗しました: " + e.getMessage(),
                    0,
                    executionTime,
                    "INSERT_TEST_DATA",
                    "エラーが発生しました: " + e.getMessage()
            );
        }
    }
    
    private List<ExpenseApplication> createTestData() {
        return List.of(
                new ExpenseApplication(
                        LocalDate.now().minusDays(1),
                        "田中太郎",
                        "営業部",
                        new BigDecimal("15000"),
                        "取引先との会食費",
                        ExpenseApplication.PaymentStatus.PENDING
                ),
                new ExpenseApplication(
                        LocalDate.now().minusDays(2),
                        "佐藤花子",
                        "マーケティング部",
                        new BigDecimal("8500"),
                        "展示会参加費",
                        ExpenseApplication.PaymentStatus.APPROVED
                ),
                new ExpenseApplication(
                        LocalDate.now().minusDays(3),
                        "鈴木一郎",
                        "開発部",
                        new BigDecimal("12000"),
                        "技術書籍購入費",
                        ExpenseApplication.PaymentStatus.PAID
                ),
                new ExpenseApplication(
                        LocalDate.now().minusDays(4),
                        "高橋美咲",
                        "人事部",
                        new BigDecimal("25000"),
                        "研修会参加費",
                        ExpenseApplication.PaymentStatus.APPROVED
                ),
                new ExpenseApplication(
                        LocalDate.now().minusDays(5),
                        "山田太郎",
                        "経理部",
                        new BigDecimal("6800"),
                        "事務用品購入費",
                        ExpenseApplication.PaymentStatus.PAID
                ),
                new ExpenseApplication(
                        LocalDate.now().minusDays(6),
                        "渡辺次郎",
                        "営業部",
                        new BigDecimal("18000"),
                        "顧客訪問時の交通費",
                        ExpenseApplication.PaymentStatus.PENDING
                ),
                new ExpenseApplication(
                        LocalDate.now().minusDays(7),
                        "伊藤三郎",
                        "開発部",
                        new BigDecimal("9500"),
                        "ソフトウェアライセンス費",
                        ExpenseApplication.PaymentStatus.REJECTED
                ),
                new ExpenseApplication(
                        LocalDate.now().minusDays(8),
                        "中村四郎",
                        "総務部",
                        new BigDecimal("14000"),
                        "会議室設備費",
                        ExpenseApplication.PaymentStatus.APPROVED
                ),
                new ExpenseApplication(
                        LocalDate.now().minusDays(9),
                        "小林五郎",
                        "マーケティング部",
                        new BigDecimal("7200"),
                        "広告制作費",
                        ExpenseApplication.PaymentStatus.PAID
                ),
                new ExpenseApplication(
                        LocalDate.now().minusDays(10),
                        "加藤六郎",
                        "人事部",
                        new BigDecimal("11000"),
                        "採用活動費",
                        ExpenseApplication.PaymentStatus.PENDING
                ),
                new ExpenseApplication(
                        LocalDate.now().minusDays(11),
                        "吉田七郎",
                        "営業部",
                        new BigDecimal("16500"),
                        "営業資料印刷費",
                        ExpenseApplication.PaymentStatus.APPROVED
                ),
                new ExpenseApplication(
                        LocalDate.now().minusDays(12),
                        "松本八郎",
                        "開発部",
                        new BigDecimal("13800"),
                        "開発環境構築費",
                        ExpenseApplication.PaymentStatus.PAID
                )
        );
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
