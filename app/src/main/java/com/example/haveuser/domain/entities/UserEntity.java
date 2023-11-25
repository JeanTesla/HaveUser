package com.example.haveuser.domain.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.haveuser.domain.enums.SexoEnum;
import com.example.haveuser.domain.enums.TipoPessoaEnum;

@Entity
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String nome;

    @ColumnInfo(name = "user_name")
    private String userName;

    private String password;

    private String foto;

    private String endereco;

    private String email;

    @ColumnInfo(name = "data_nascimento")
    private String dataNascimento;

    private SexoEnum sexo;

    private TipoPessoaEnum tipo;

    @ColumnInfo(name = "cpf_cnpj")
    private String cpfCnpj;

    public int getUid() {
        return uid;
    }

    public UserEntity() {
    }

    public UserEntity(String name, String email, TipoPessoaEnum type) {
        this.nome = name;
        this.email = email;
        this.tipo = type;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public TipoPessoaEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoaEnum tipo) {
        this.tipo = tipo;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
}
