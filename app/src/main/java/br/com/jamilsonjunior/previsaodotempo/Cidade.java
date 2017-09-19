package br.com.jamilsonjunior.previsaodotempo;

import com.orm.SugarRecord;

/**
 * Created by Jamilson on 24/08/2017.
 */

public class Cidade extends SugarRecord<Cidade> {
    String nome;
    String estado;

    public Cidade() {
    }

    public Cidade(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nome + " - " + estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
