package com.example.dbc.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public List<Acesso> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<Acesso> acessos) {
        this.acessos = acessos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_acesso",
            uniqueConstraints = @UniqueConstraint (columnNames = {"usuario_id", "acesso_id"} , name = "unique_acesso_user"),

            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id",
                    table = "usuario", unique = false, foreignKey = @ForeignKey(name = "usuario_fk",
                    value = ConstraintMode.CONSTRAINT)),

            inverseJoinColumns = @JoinColumn(name = "acesso_id", unique = false, referencedColumnName = "id",
                    table = "acesso", foreignKey = @ForeignKey(name = "acesso_fk", value = ConstraintMode.CONSTRAINT)))
    private List<Acesso> acessos;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.acessos;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
