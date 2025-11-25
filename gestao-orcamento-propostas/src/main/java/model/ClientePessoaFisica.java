package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "cliente_pf") 
public class ClientePessoaFisica extends Pessoa {

    @Column(unique = true, nullable = false, length = 14) 
    private String cpf;

    
    public ClientePessoaFisica() {
        super(); 
    }


    public ClientePessoaFisica(String nome, String email, String telefone, String cpf) {
        super(nome, email, telefone);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
