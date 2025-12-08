package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proposta")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Pessoa cliente;

    @OneToMany(mappedBy = "proposta", cascade = CascadeType.ALL)
    private List<ItemProposta> itens = new ArrayList<>();

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Column(name = "valor_total")
    private Double valorTotal = 0.0; 

    @Enumerated(EnumType.STRING)
    private StatusProposta status;

    private String observacoes;


    public Proposta() {
    }

    public Proposta(Pessoa cliente, LocalDate dataCriacao) {
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.status = StatusProposta.EM_ELABORACAO;
    }


    public void calcularValorTotal() {
        this.valorTotal = 0.0;
        
        for (ItemProposta item : this.itens) {
            if (item.getSubtotal() != null) {
                this.valorTotal += item.getSubtotal();
            }
        }
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public List<ItemProposta> getItens() {
        return itens;
    }

    public void setItens(List<ItemProposta> itens) {
        this.itens = itens;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public void setStatus(StatusProposta status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}