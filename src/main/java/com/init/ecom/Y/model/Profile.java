package com.init.ecom.Y.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysql.cj.jdbc.Blob;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
@Data
public class Profile {

    private String name;

    @Column(name = "mobile_number")
    @Id
    private Long mobileNumber;

    private String password;

    @Lob
    private byte[] picture;

}

