abstract class Produto {
    protected String descricao;
    protected double precoDeCusto;
    protected double margemDeLucro;

    public Produto(String descricao, double precoDeCusto, double margemDeLucro) {
        this.descricao = descricao;
        this.precoDeCusto = precoDeCusto;
        this.margemDeLucro = margemDeLucro;
    }

    public abstract String gerarDadosTexto();

    public static Produto criarDoTexto(String linha) {
        String[] partes = linha.split(";");
        int tipo = Integer.parseInt(partes[0]);
        String descricao = partes[1];
        double precoDeCusto = Double.parseDouble(partes[2]);
        double margemDeLucro = Double.parseDouble(partes[3]);
        
        if (tipo == 1) {
            return new ProdutoNaoPerecivel(descricao, precoDeCusto, margemDeLucro);
        } else if (tipo == 2) {
            String dataDeValidade = partes[4];
            return new ProdutoPerecivel(descricao, precoDeCusto, margemDeLucro, dataDeValidade);
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Produto) {
            Produto outro = (Produto) obj;
            return this.descricao.equalsIgnoreCase(outro.descricao);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s - Preço de Custo: %.2f - Margem de Lucro: %.2f", descricao, precoDeCusto, margemDeLucro);
    }
}
