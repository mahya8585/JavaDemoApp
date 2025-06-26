package com.example.javademoapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "expense_applications")
public class ExpenseApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Application date is required")
    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;
    
    @NotBlank(message = "Applicant name is required")
    @Size(max = 100, message = "Applicant name cannot exceed 100 characters")
    @Column(name = "applicant_name", nullable = false, length = 100)
    private String applicantName;
    
    @NotBlank(message = "Department is required")
    @Size(max = 100, message = "Department name cannot exceed 100 characters")
    @Column(name = "department", nullable = false, length = 100)
    private String department;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    
    @NotBlank(message = "Reason is required")
    @Size(max = 500, message = "Reason cannot exceed 500 characters")
    @Column(name = "reason", nullable = false, length = 500)
    private String reason;
    
    @NotNull(message = "Payment status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 20)
    private PaymentStatus paymentStatus;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
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
