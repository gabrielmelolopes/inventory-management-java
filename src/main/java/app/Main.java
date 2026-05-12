package app;

import exception.NegocioException;
import model.Produto;
// import repository.ProdutoRepositoryMemoria;
import repository.ConnectionFactory;
import repository.ProdutoRepositorySQL;
import service.EstoqueService;
import java.sql.Connection;

import java.util.List;
import java.util.Scanner;

public class Main
{
    private static Scanner sc = new Scanner(System.in);
    // private static ProdutoRepositoryMemoria repos = new ProdutoRepositoryMemoria();
    private static final Connection conn = ConnectionFactory.getConnection();
    private static final ProdutoRepositorySQL repos = new ProdutoRepositorySQL(conn);
    private static final EstoqueService service = new EstoqueService(repos);
    public static void main(String[] args)
    {
        int op;
        do {
            System.out.println("====================");
            System.out.println("1 - Adicionar produto ao estoque");
            System.out.println("2 - Remover produto do estoque");
            System.out.println("3 - Ver produtos no estoque");
            System.out.println("0 - Parar programa");
            System.out.println("====================");
            op = sc.nextInt();
            sc.nextLine();

            switch(op){
                case 1 ->{adicionarProduto();}
                case 2 ->{removerProduto();}
                case 3 ->{verProdutos();}
                case 0 ->{
                    System.out.println("encerrando...");}
                default ->{
                    System.out.println("Opção incorreta.");
                }
            }
        }while (op != 0);
    }

    public static void adicionarProduto(){
        try{
            System.out.print("Nome do produto: ");
            String nomeProduto = sc.nextLine();
            System.out.print("Preço do produto: ");
            double precoProduto = sc.nextDouble();
            sc.nextLine();
            System.out.print("Quantidade: ");
            int quantidadeProduto = sc.nextInt();
            sc.nextLine();
            Produto produtoADD = new Produto(nomeProduto, precoProduto, quantidadeProduto);
            service.salvarProduto(produtoADD);
        }catch(NegocioException e){
            System.out.println("Erro: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Erro crítico.");
        }
    }
    public static void removerProduto(){
        try{
            System.out.println("ID: ");
            int idProduto = sc.nextInt();
            sc.nextLine();
            service.removerProduto(idProduto);
        }catch (NegocioException e){
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro crítico.");
        }
    }

    public static void verProdutos(){
        try{
            List<Produto> produtos = service.listarEstoque();
            for(Produto p : produtos){
                System.out.println(p);
            }
        }catch(NegocioException e){
            System.out.println("Erro: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Erro crítico.");
        }
    }

}
