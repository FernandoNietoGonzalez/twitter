package com.fernando.nieto.acciona.infrastructure.repository.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class TweetEntity implements Serializable {

    private static final long serialVersionUID = -3898061962049947269L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CODE", nullable = false)
    private Long id;
    @Column(name = "ID_TWEET_ORIGIN", nullable = false, unique = true)
    private Long tweetId;
    @Column(name = "DT_CREATE", nullable = false)
    private LocalDateTime created_at;
    @Column(name = "DE_USER", nullable = false)
    private String user;
    @Column(name = "DE_TEXT", length = 5000)
    private String text;
    @Column(name = "DE_LOCATION")
    private String location;
    @Column(name = "DE_LANGUAGE", nullable = false)
    private String language;
    @Column(name = "IN_VALIDATE")
    private boolean validated;
}
