package com.example.demo.analytics.model;

import jakarta.persistence.*;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "analytics")
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
}
