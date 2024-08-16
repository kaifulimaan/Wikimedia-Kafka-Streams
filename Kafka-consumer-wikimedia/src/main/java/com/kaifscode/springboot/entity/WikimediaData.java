package com.kaifscode.springboot.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wikimedia_recentChange")
@Getter
@Setter
public class WikimediaData {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Lob
    private String wikiEventData;


    public void setWikiEventData(String wikiEventData) {
        this.wikiEventData=wikiEventData;
    }
}