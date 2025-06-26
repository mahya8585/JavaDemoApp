package com.example.javademoapi.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.LocalDateTime;

@JacksonXmlRootElement(localName = "DatabaseOperationResult")
public class DatabaseOperationResultDto {
    
    @JacksonXmlProperty(localName = "success")
    private boolean success;
    
    @JacksonXmlProperty(localName = "message")
    private String message;
    
    @JacksonXmlProperty(localName = "recordsInserted")
    private int recordsInserted;
    
    @JacksonXmlProperty(localName = "executionTime")
    private LocalDateTime executionTime;
    
    @JacksonXmlProperty(localName = "operation")
    private String operation;
    
    @JacksonXmlProperty(localName = "details")
    private String details;
    
    // Constructors
    public DatabaseOperationResultDto() {}
    
    public DatabaseOperationResultDto(boolean success, String message, int recordsInserted, 
                                    LocalDateTime executionTime, String operation, String details) {
        this.success = success;
        this.message = message;
        this.recordsInserted = recordsInserted;
        this.executionTime = executionTime;
        this.operation = operation;
        this.details = details;
    }
    
    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getRecordsInserted() {
        return recordsInserted;
    }
    
    public void setRecordsInserted(int recordsInserted) {
        this.recordsInserted = recordsInserted;
    }
    
    public LocalDateTime getExecutionTime() {
        return executionTime;
    }
    
    public void setExecutionTime(LocalDateTime executionTime) {
        this.executionTime = executionTime;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public String getDetails() {
        return details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
}
