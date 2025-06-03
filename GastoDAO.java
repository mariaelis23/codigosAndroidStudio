package com.example.provaterca;

import java.util.List;

@Dao
public interface GastoDAO {
    @Insert
    void insert(Gasto gasto);
    @Query("SELECT * FROM gastos")
    List<Gasto> buscaTodosGastos();
    @Query("SELECT * FROM gastos ORDER BY valor DESC LIMIT 1")
    Gasto buscaMaiorGasto();
}
