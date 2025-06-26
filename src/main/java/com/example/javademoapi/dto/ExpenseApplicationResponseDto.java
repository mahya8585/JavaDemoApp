package com.example.javademoapi.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@JacksonXmlRootElement(localName = "ExpenseApplicationResponse")
public class ExpenseApplicationResponseDto {
    
    @JacksonXmlProperty(localName = "applications")
    @JacksonXmlElementWrapper(localName = "applications")
    private List<ExpenseApplicationDto> applications;
    
    @JacksonXmlProperty(localName = "total")
    private int total;
    
    @JacksonXmlProperty(localName = "message")
    private String message;
    
    // Constructors
    public ExpenseApplicationResponseDto() {}
    
    public ExpenseApplicationResponseDto(List<ExpenseApplicationDto> applications, int total, String message) {
        this.applications = applications;
        this.total = total;
        this.message = message;
    }
    
    // Getters and Setters
    public List<ExpenseApplicationDto> getApplications() {
        return applications;
    }
    
    public void setApplications(List<ExpenseApplicationDto> applications) {
        this.applications = applications;
    }
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    // Inner DTO class for individual expense application
    public static class ExpenseApplicationDto {
        
        @JacksonXmlProperty(localName = "id")
        private Long id;
        
        @JacksonXmlProperty(localName = "applicationDate")
        private LocalDate applicationDate;
        
        @JacksonXmlProperty(localName = "applicantName")
        private String applicantName;
        
        @JacksonXmlProperty(localName = "department")
        private String department;
        
        @JacksonXmlProperty(localName = "amount")
        private BigDecimal amount;
        
        @JacksonXmlProperty(localName = "reason")
        private String reason;
        
        @JacksonXmlProperty(localName = "paymentStatus")
        private String paymentStatus;
        
        @JacksonXmlProperty(localName = "paymentStatusDisplay")
        private String paymentStatusDisplay;
        
        // Constructors
        public ExpenseApplicationDto() {}
        
        public ExpenseApplicationDto(Long id, LocalDate applicationDate, String applicantName, 
                                   String department, BigDecimal amount, String reason, 
                                   String paymentStatus, String paymentStatusDisplay) {
            this.id = id;
            this.applicationDate = applicationDate;
            this.applicantName = applicantName;
            this.department = department;
            this.amount = amount;
            this.reason = reason;
            this.paymentStatus = paymentStatus;
            this.paymentStatusDisplay = paymentStatusDisplay;
        }
        
        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        
        public LocalDate getApplicationDate() { return applicationDate; }
        public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }
        
        public String getApplicantName() { return applicantName; }
        public void setApplicantName(String applicantName) { this.applicantName = applicantName; }
        
        public String getDepartment() { return department; }
        public void setDepartment(String department) { this.department = department; }
        
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
        
        public String getPaymentStatus() { return paymentStatus; }
        public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
        
        public String getPaymentStatusDisplay() { return paymentStatusDisplay; }
        public void setPaymentStatusDisplay(String paymentStatusDisplay) { this.paymentStatusDisplay = paymentStatusDisplay; }
    }
}
