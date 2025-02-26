import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Comercio {
    static final int MAX_NOVOS_PRODUTOS = 10;
    static String nomeArquivoDados = "dadosProdutos.csv";
    static Scanner teclado = new Scanner(System.in, StandardCharsets.ISO_8859_1);
    static List<Produto> produtosCadastrados = new ArrayList<>();

    static void pausa() {
        System.out.println("Digite enter para continuar...");
        teclado.nextLine();
    }

    static int menu() {
        System.out.println("\nAEDII COMÉRCIO DE COISINHAS");
        System.out.println("===========================");
        System.out.println("1 - Listar todos os produtos");
        System.out.println("2 - Procurar um produto");
        System.out.println("3 - Cadastrar novo produto");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        return Integer.parseInt(teclado.nextLine());
    }

    static void lerProdutos() {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivoDados))) {
            int n = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < n; i++) {
                produtosCadastrados.add(Produto.criarDoTexto(br.readLine()));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler o arquivo de dados.");
        }
    }

    static void listarTodosOsProdutos() {
        if (produtosCadastrados.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto p : produtosCadastrados) {
            System.out.println(p);
        }
    }

    static void localizarProdutos() {
        System.out.print("Digite o nome do produto a ser buscado: ");
        String busca = teclado.nextLine();
        boolean encontrado = false;
        for (Produto p : produtosCadastrados) {
            if (p.descricao.equalsIgnoreCase(busca)) {
                System.out.println(p);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Produto não encontrado.");
        }
    }

    static void cadastrarProduto() {
        System.out.print("Digite o tipo do produto (1 - Não perecível, 2 - Perecível): ");
        int tipo = Integer.parseInt(teclado.nextLine());
        System.out.print("Descrição: ");
        String descricao = teclado.nextLine();
        System.out.print("Preço de Custo: ");
        double precoDeCusto = Double.parseDouble(teclado.nextLine());
        System.out.print("Margem de Lucro: ");
        double margemDeLucro = Double.parseDouble(teclado.nextLine());
        
        Produto novoProduto;
        if (tipo == 2) {
            System.out.print("Data de Validade (dd/mm/aaaa): ");
            String dataDeValidade = teclado.nextLine();
            novoProduto = new ProdutoPerecivel(descricao, precoDeCusto, margemDeLucro, dataDeValidade);
        } else {
            novoProduto = new ProdutoNaoPerecivel(descricao, precoDeCusto, margemDeLucro);
        }
        
        produtosCadastrados.add(novoProduto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    static void salvarProdutos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivoDados))) {
            bw.write(String.valueOf(produtosCadastrados.size()));
            bw.newLine();
            for (Produto p : produtosCadastrados) {
                bw.write(p.gerarDadosTexto());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os produtos no arquivo.");
        }
    }
}
