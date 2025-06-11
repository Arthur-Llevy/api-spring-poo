package br.unicap.si.poo.project.demo.abstractsClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    public Long id;
    public String name;
    public String email;
    public String phoneNumber;
    public String cpf;
    public String birthDate;
    public String password;
}
