package com.example.comerciofacil.model;

public class Produto extends EntidadeBase implements Persistente, Comparable<Produto> {
    private String nome;
    private String categoria;
    private double preco;
    private int quantidade;
    private String validade;

    public Produto(String nome, String categoria, double preco, int quantidade, String validade) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = quantidade;
        this.validade = validade;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    // Sobrescrita de toString
    @Override
    public String toString() {
        return "Produto: " + nome + " | Categoria: " + categoria + " | Preço: " + preco +
                " | Quantidade: " + quantidade + " | Validade: " + validade;
    }

    // Sobrescrita de equals para comparar produtos pelo nome e categoria
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Mesma instância
        if (obj == null || getClass() != obj.getClass()) return false; // Verifica classe

        Produto produto = (Produto) obj;
        return nome.equals(produto.nome) && categoria.equals(produto.categoria);
    }

    // Sobrescrita de hashCode, consistente com equals
    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + categoria.hashCode();
        return result;
    }

    // Sobrescrita de clone para criar cópias de Produto
    @Override
    public Produto clone() {
        return new Produto(this.nome, this.categoria, this.preco, this.quantidade, this.validade);
    }

    // Sobrescrita de compareTo para ordenar produtos pelo preço
    @Override
    public int compareTo(Produto outroProduto) {
        return Double.compare(this.preco, outroProduto.preco);
    }

    // Implementação dos métodos da interface Persistente
    @Override
    public void salvar() {
        System.out.println("Salvando produto: " + nome);
        // Lógica de persistência (ex.: salvar no banco de dados)
    }

    @Override
    public void atualizar() {
        System.out.println("Atualizando produto: " + nome);
        // Lógica de persistência (ex.: atualizar no banco de dados)
    }

    @Override
    public void excluir() {
        System.out.println("Excluindo produto: " + nome);
        // Lógica de persistência (ex.: excluir do banco de dados)
    }

    // Método adicional: resumo do produto
    public String resumo() {
        return "Produto: " + nome + " | Preço: R$" + preco + " | Quantidade: " + quantidade;
    }
}
