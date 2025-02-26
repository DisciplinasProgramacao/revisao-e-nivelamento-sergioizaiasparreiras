class ProdutoPerecivel extends Produto {
    private String dataDeValidade;

    public ProdutoPerecivel(String descricao, double precoDeCusto, double margemDeLucro, String dataDeValidade) {
        super(descricao, precoDeCusto, margemDeLucro);
        this.dataDeValidade = dataDeValidade;
    }

    @Override
    public String gerarDadosTexto() {
        return String.format("2;%s;%.2f;%.2f;%s", descricao, precoDeCusto, margemDeLucro, dataDeValidade);
    }

    @Override
    public String toString() {
        return super.toString() + " - Validade: " + dataDeValidade;
    }
}