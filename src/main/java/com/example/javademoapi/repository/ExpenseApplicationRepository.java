package com.example.javademoapi.repository;

import com.example.javademoapi.entity.ExpenseApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseApplicationRepository extends JpaRepository<ExpenseApplication, Long> {
    
    @Query("SELECT e FROM ExpenseApplication e ORDER BY e.applicationDate DESC")
    List<ExpenseApplication> findTop10ByOrderByApplicationDateDesc(Pageable pageable);
    
    List<ExpenseApplication> findByApplicantNameContainingIgnoreCase(String applicantName);
    
    List<ExpenseApplication> findByDepartmentContainingIgnoreCase(String department);
    
    @Query("SELECT e FROM ExpenseApplication e WHERE e.paymentStatus = :status ORDER BY e.applicationDate DESC")
    List<ExpenseApplication> findByPaymentStatusOrderByApplicationDateDesc(ExpenseApplication.PaymentStatus status);
}
