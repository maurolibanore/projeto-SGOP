package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cliente_pj") 
public class ClientePessoaJuridica extends Pessoa {

    @Column(unique = true, nullable = false, length = 18) 
    private String cnpj;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    public ClientePessoaJuridica() {
        super();
    }


    public ClientePessoaJuridica(String nome, String email, String telefone, String cnpj, String razaoSocial) {
        super(nome, email, telefone);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}