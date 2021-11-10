package com.chat.websocket.testWebsock.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidUser {
    private Integer id;
    @NotEmpty(message = "email не может быть пустым")
    @Email(message = "Введите корректный email")
    private String email;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String firstname;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String lastname;
    @NotEmpty(message = "Это поле не может быть пустым")
    @Size(min = 5, max = 50, message = "Минимальный размер должен составлять больше 5 символов")
    private String password;
}
