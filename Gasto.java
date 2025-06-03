package com.example.provaterca;

import androidx.room.ColumInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
gasto.getId();
gasto.setId(1);
@Entity(tableName = "gastos")
public class Gasto {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    @ColumInfo(name = "descricao")
    private String descricao;

    @ColumInfo(name = "valor")
    private float valor;

    public Gasto(int id, String descricao, float valor){
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }
}
