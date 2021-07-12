package com.twitter.infrastructure.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.io.Serializable;

@Data
@Entity
public class HashtagEntity implements Serializable {

    private static final long serialVersionUID = -3101892510357407530L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CODE", nullable = false)
    private Long id;
    @Column(name = "DE_TEXT", nullable = false)
    private String text;
    @Column(name = "NM_COUNTER", nullable = false, columnDefinition = "integer default 1")
    private Integer counter;

    @PrePersist
    void prePersist() {
        if (this.counter== null)
            this.counter = 1;
    }
}