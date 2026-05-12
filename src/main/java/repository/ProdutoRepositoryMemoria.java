package repository;

import model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoRepositoryMemoria implements ProdutoRepository{
    private Map<Integer, Produto> dados = new HashMap<>();
    @Override
    public void salvar(Produto produto) {
    dados.put(produto.getId(), produto);
    }

    @Override
    public Produto buscarPorId(int id) {
        return dados.get(id);
    }

    @Override
    public List<Produto> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public void removerPorId(int id){dados.remove(id);}

}
