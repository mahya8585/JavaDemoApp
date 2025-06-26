package com.example.javademoapi.config;

import com.example.javademoapi.entity.ExpenseApplication;
import com.example.javademoapi.repository.ExpenseApplicationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    
    private final ExpenseApplicationRepository expenseApplicationRepository;
    
    public DataLoader(ExpenseApplicationRepository expenseApplicationRepository) {
        this.expenseApplicationRepository = expenseApplicationRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        loadExpenseApplicationData();
    }
    
    private void loadExpenseApplicationData() {
        // Check if expense application data already exists
        if (expenseApplicationRepository.count() == 0) {
            // Create sample expense applications
            ExpenseApplication app1 = new ExpenseApplication(
                    LocalDate.now().minusDays(1),
                    "田中太郎",
                    "営業部",
                    new BigDecimal("15000"),
                    "取引先との会食費",
                    ExpenseApplication.PaymentStatus.PENDING
            );
            
            ExpenseApplication app2 = new ExpenseApplication(
                    LocalDate.now().minusDays(2),
                    "佐藤花子",
                    "マーケティング部",
                    new BigDecimal("8500"),
                    "展示会参加費",
                    ExpenseApplication.PaymentStatus.APPROVED
            );
            
            ExpenseApplication app3 = new ExpenseApplication(
                    LocalDate.now().minusDays(3),
                    "鈴木一郎",
                    "開発部",
                    new BigDecimal("12000"),
                    "技術書籍購入費",
                    ExpenseApplication.PaymentStatus.PAID
            );
            
            ExpenseApplication app4 = new ExpenseApplication(
                    LocalDate.now().minusDays(4),
                    "高橋美咲",
                    "人事部",
                    new BigDecimal("25000"),
                    "研修会参加費",
                    ExpenseApplication.PaymentStatus.APPROVED
            );
            
            ExpenseApplication app5 = new ExpenseApplication(
                    LocalDate.now().minusDays(5),
                    "山田太郎",
                    "経理部",
                    new BigDecimal("6800"),
                    "事務用品購入費",
                    ExpenseApplication.PaymentStatus.PAID
            );
            
            expenseApplicationRepository.save(app1);
            expenseApplicationRepository.save(app2);
            expenseApplicationRepository.save(app3);
            expenseApplicationRepository.save(app4);
            expenseApplicationRepository.save(app5);
            
            System.out.println("Expense application sample data loaded successfully!");
        }
    }
}
