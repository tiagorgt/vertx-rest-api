package io.github.tiagorgt.vertx.api.entity;

import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tiago on 07/10/2017.
 */

@Entity
@Table(name = "usuario_externo")
public class User implements Serializable {
    @Id
    @Column(unique = true, name = "nu_cpf")
    private String cpf;

    @Column(name = "no_usuario", nullable = false)
    private String name;

    @Column(name = "de_email", nullable = false)
    private String email;

    @Column(name = "ic_situacao", nullable = false)
    private String status;

    @Column(name = "ic_perfil_acesso", nullable = false)
    private int profile;

    @ManyToOne
    @JoinColumn(name = "co_funcao", referencedColumnName = "co_funcao", nullable = false)
    private Position position;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "nu_telefone")
    private String phone;


}
