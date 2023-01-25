package com.niit.Soulmateservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SoulmateUser {
    @Id
    private String emailId;
    private String name;
    private int age;
    private String gender;
    private String city;
    private Binary image;
}
