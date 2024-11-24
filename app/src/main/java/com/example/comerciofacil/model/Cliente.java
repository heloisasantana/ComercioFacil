package com.example.comerciofacil.model;

public class Cliente extends EntidadeBase implements Comparable<Cliente> {
    private String nome;
    private String telefone;
    private String email;
    private String historicoDeCompras;

    public Cliente(String nome, String telefone, String email, String historicoDeCompras) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.historicoDeCompras = historicoDeCompras;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHistoricoDeCompras() {
        return historicoDeCompras;
    }

    public void setHistoricoDeCompras(String historicoDeCompras) {
        this.historicoDeCompras = historicoDeCompras;
    }

    // Sobrescrita de toString
    @Override
    public String toString() {
        return "Cliente: " + nome + " | Telefone: " + telefone + " | Email: " + email +
                " | Histórico: " + historicoDeCompras;
    }

    // Sobrescrita de equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Cliente cliente = (Cliente) obj;
        return nome.equals(cliente.nome) && telefone.equals(cliente.telefone);
    }

    // Sobrescrita de hashCode
    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + telefone.hashCode();
        return result;
    }

    // Implementação de Comparable para ordenar pelo nome
    @Override
    public int compareTo(Cliente outroCliente) {
        return this.nome.compareTo(outroCliente.nome);
    }

    // Método para clonar o cliente
    @Override
    public Cliente clone() {
        return new Cliente(this.nome, this.telefone, this.email, this.historicoDeCompras);
    }

    // Método resumo
    public String resumo() {
        return "Cliente: " + nome + " | Contato: " + telefone;
    }
}
