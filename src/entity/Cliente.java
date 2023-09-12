package entity;

import enums.TipoPessoa;

public abstract class Cliente {

    private TipoPessoa tipoPessoa;

    public Cliente(TipoPessoa tipoPessoa){
        this.tipoPessoa = tipoPessoa;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }
}
