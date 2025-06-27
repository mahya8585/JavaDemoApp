package com.example.javademoapi.config;

import com.example.javademoapi.entity.ExpenseApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader {
    
    private List<ExpenseApplication> expenseApplications;
    
    public DataLoader() {
        loadExpenseApplicationData();
    }
    
    private void loadExpenseApplicationData() {
        expenseApplications = new ArrayList<>();
        
        // Create sample expense applications
        ExpenseApplication app1 = new ExpenseApplication(
                LocalDate.now().minusDays(1),
                "田中太郎",
                "営業部",
                new BigDecimal("15000"),
                "取引先との会食費",
                ExpenseApplication.PaymentStatus.PENDING
        );
        app1.setId(1L);
        
        ExpenseApplication app2 = new ExpenseApplication(
                LocalDate.now().minusDays(2),
                "佐藤花子",
                "マーケティング部",
                new BigDecimal("8500"),
                "展示会参加費",
                ExpenseApplication.PaymentStatus.APPROVED
        );
        app2.setId(2L);
        
        ExpenseApplication app3 = new ExpenseApplication(
                LocalDate.now().minusDays(3),
                "鈴木一郎",
                "開発部",
                new BigDecimal("12000"),
                "技術書籍購入費",
                ExpenseApplication.PaymentStatus.PAID
        );
        app3.setId(3L);
        
        ExpenseApplication app4 = new ExpenseApplication(
                LocalDate.now().minusDays(4),
                "高橋美咲",
                "人事部",
                new BigDecimal("25000"),
                "研修会参加費",
                ExpenseApplication.PaymentStatus.APPROVED
        );
        app4.setId(4L);
        
        ExpenseApplication app5 = new ExpenseApplication(
                LocalDate.now().minusDays(5),
                "山田太郎",
                "経理部",
                new BigDecimal("6800"),
                "事務用品購入費",
                ExpenseApplication.PaymentStatus.PAID
        );
        app5.setId(5L);
        
        ExpenseApplication app6 = new ExpenseApplication(
                LocalDate.now().minusDays(6),
                "渡辺次郎",
                "営業部",
                new BigDecimal("18000"),
                "顧客訪問時の交通費",
                ExpenseApplication.PaymentStatus.PENDING
        );
        app6.setId(6L);
        
        ExpenseApplication app7 = new ExpenseApplication(
                LocalDate.now().minusDays(7),
                "伊藤三郎",
                "開発部",
                new BigDecimal("9500"),
                "ソフトウェアライセンス費",
                ExpenseApplication.PaymentStatus.REJECTED
        );
        app7.setId(7L);
        
        ExpenseApplication app8 = new ExpenseApplication(
                LocalDate.now().minusDays(8),
                "中村四郎",
                "総務部",
                new BigDecimal("14000"),
                "会議室設備費",
                ExpenseApplication.PaymentStatus.APPROVED
        );
        app8.setId(8L);
        
        ExpenseApplication app9 = new ExpenseApplication(
                LocalDate.now().minusDays(9),
                "小林五郎",
                "マーケティング部",
                new BigDecimal("7200"),
                "広告制作費",
                ExpenseApplication.PaymentStatus.PAID
        );
        app9.setId(9L);
        
        ExpenseApplication app10 = new ExpenseApplication(
                LocalDate.now().minusDays(10),
                "加藤六郎",
                "人事部",
                new BigDecimal("11000"),
                "採用活動費",
                ExpenseApplication.PaymentStatus.PENDING
        );
        app10.setId(10L);
        
        ExpenseApplication app11 = new ExpenseApplication(
                LocalDate.now().minusDays(11),
                "吉田七郎",
                "営業部",
                new BigDecimal("16500"),
                "営業資料印刷費",
                ExpenseApplication.PaymentStatus.APPROVED
        );
        app11.setId(11L);
        
        ExpenseApplication app12 = new ExpenseApplication(
                LocalDate.now().minusDays(12),
                "松本八郎",
                "開発部",
                new BigDecimal("13800"),
                "開発環境構築費",
                ExpenseApplication.PaymentStatus.PAID
        );
        app12.setId(12L);
        
        expenseApplications.add(app1);
        expenseApplications.add(app2);
        expenseApplications.add(app3);
        expenseApplications.add(app4);
        expenseApplications.add(app5);
        expenseApplications.add(app6);
        expenseApplications.add(app7);
        expenseApplications.add(app8);
        expenseApplications.add(app9);
        expenseApplications.add(app10);
        expenseApplications.add(app11);
        expenseApplications.add(app12);
        
        System.out.println("Expense application sample data loaded successfully! Total: " + expenseApplications.size());
    }
    
    public List<ExpenseApplication> getTop10ExpenseApplications() {
        return expenseApplications.stream()
                .sorted((a, b) -> b.getApplicationDate().compareTo(a.getApplicationDate()))
                .limit(10)
                .collect(Collectors.toList());
    }
    
    public List<ExpenseApplication> getAllExpenseApplications() {
        return new ArrayList<>(expenseApplications);
    }
    
    public void resetTestData() {
        loadExpenseApplicationData();
    }
}
