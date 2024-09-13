package com.sangwook.shoppingmall.domain.user;

import com.sangwook.shoppingmall.constant.Gender;
import com.sangwook.shoppingmall.domain.user.dto.UserRegister;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    private LocalDate birth;

    public static User register(UserRegister userRegister, String encoded) {
        User user = new User();
        user.email = userRegister.getEmail();
        user.name = userRegister.getNickname();
        user.password = encoded;
        user.age = userRegister.getAge();
        user.gender = userRegister.getGender();
        user.phoneNumber = userRegister.getPhoneNumber();
        user.birth = birthConverter(userRegister.getBirth());
        return user;
    }

    public User() {

    }

    private static LocalDate birthConverter(Integer intBirth) {
        String stringBirth = intBirth.toString();
        int year = Integer.parseInt(stringBirth.substring(0, 4));
        int month = Integer.parseInt(stringBirth.substring(4, 6));
        int day = Integer.parseInt(stringBirth.substring(6));

        return LocalDate.of(year, month, day);
    }
}
