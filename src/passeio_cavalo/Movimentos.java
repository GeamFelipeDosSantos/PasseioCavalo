package passeio_cavalo;

import model.Tabuleiro;

/**
 *
 * @author Geam
 */
public class Movimentos {

    

    public final static boolean analisarColuna(int colunaAtual, int movimentoVertical) {
        return !((colunaAtual + movimentoVertical < 0) || (colunaAtual + movimentoVertical > (Tabuleiro.DIMENSAO_TABULEIRO - 1)));
    }

    public final static boolean analisarLinha(int linhaAtual, int movimentoHorizontal) {
        return !((linhaAtual + movimentoHorizontal < 0) || (linhaAtual + movimentoHorizontal > (Tabuleiro.DIMENSAO_TABULEIRO - 1)));
    }

    
}
