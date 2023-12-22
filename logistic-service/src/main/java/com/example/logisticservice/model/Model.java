package com.example.logisticservice.model;



import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class Model implements Serializable {
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
