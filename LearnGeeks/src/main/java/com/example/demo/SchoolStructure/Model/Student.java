package com.example.demo.schoolstructure.model;

import com.example.demo.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(unique = true, nullable = false)
    private String admissionNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass schoolClass;

    public String getFullName() {
        if (user == null) {
            return "Unknown";
        }
        
        // Get first name and last name if they exist
        String firstName = user.getFirstName() != null ? user.getFirstName() : "";
        String lastName = user.getLastName() != null ? user.getLastName() : "";
        
        // Combine names, handling cases where one might be empty
        String fullName = (firstName + " " + lastName).trim();
        return fullName.isEmpty() ? "Unknown" : fullName;
    }
}
