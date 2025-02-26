class ProdutoNaoPerecivel extends Produto {
    public ProdutoNaoPerecivel(String descricao, double precoDeCusto, double margemDeLucro) {
        super(descricao, precoDeCusto, margemDeLucro);
    }

    @Override
    public String gerarDadosTexto() {
        return String.format("1;%s;%.2f;%.2f", descricao, precoDeCusto, margemDeLucro);
    }
}
