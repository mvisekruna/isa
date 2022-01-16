package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DELETE_ACCOUNT")
public class DeleteAccount {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "reason")
    private String reason;

    @Column(name = "confirm")
    private boolean confirm;

}
