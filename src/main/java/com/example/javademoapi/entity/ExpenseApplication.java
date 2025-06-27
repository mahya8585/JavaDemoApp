package com.example.javademoapi.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExpenseApplication {
    
    private Long id;
    
    private LocalDate applicationDate;
    
    private String applicantName;
    
    private String department;
    
    private BigDecimal amount;
    
    private String reason;
    
    private PaymentStatus paymentStatus;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    // Enum for payment status
    public enum PaymentStatus {
        PENDING("申請中"),
        APPROVED("承認済み"),
        REJECTED("否認"),
        PAID("支払い済み");
        
        private final String displayName;
        
        PaymentStatus(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    // Constructors
    public ExpenseApplication() {}
    
    public ExpenseApplication(LocalDate applicationDate, String applicantName, String department, 
                            BigDecimal amount, String reason, PaymentStatus paymentStatus) {
        this.applicationDate = applicationDate;
        this.applicantName = applicantName;
        this.department = department;
        this.amount = amount;
        this.reason = reason;
        this.paymentStatus = paymentStatus;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getApplicationDate() {
        return applicationDate;
    }
    
    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
    
    public String getApplicantName() {
        return applicantName;
    }
    
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public String toString() {
        return "ExpenseApplication{" +
                "id=" + id +
                ", applicationDate=" + applicationDate +
                ", applicantName='" + applicantName + '\'' +
                ", department='" + department + '\'' +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
