package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false, unique = true) 
    private String descricao;


    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL)
    private List<Preco> historicoPrecos = new ArrayList<>();


    public Servico() {
    }

    public Servico(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Método auxiliar para descobrir qual o preço atual desse serviço.
     * Varre a lista e retorna o valor do preço que está marcado como ativo.
     */
    public Double getValorAtual() {
        for (Preco preco : this.historicoPrecos) {
            if (preco.isAtivo()) {
                return preco.getValor();
            }
        }
        return 0.0; 
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Preco> getHistoricoPrecos() {
        return historicoPrecos;
    }

    public void setHistoricoPrecos(List<Preco> historicoPrecos) {
        this.historicoPrecos = historicoPrecos;
    }
}
