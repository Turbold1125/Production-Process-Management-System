//package com.example.Backend.Model.Entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Set;
//
//@Data
//@Entity(name = "Roles")
//@AllArgsConstructor
//@NoArgsConstructor
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//
//    @OneToMany(mappedBy = "role")
//    private Set<UserRole> userRoles;
//}

package com.example.Backend.Model.Const;

public enum Role {
    USER,
    ADMIN,
    RECEIVER
}