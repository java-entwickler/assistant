package com.lera.assistant.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Job {
    @Id
    @GeneratedValue
    private Long jobId;
    private String title;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    private Boolean completed;
    private Boolean paid;
    private Date dateCreated;
    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
    }
}
