package passeio_cavalo;

import model.Tabuleiro;

/**
 *
 * @author Geam
 */
public class Movimentos {

    

    public final static boolean analisarColuna(int colunaAtual, int movimentoVertical) {
        if ((colunaAtual + movimentoVertical < 0) || (colunaAtual + movimentoVertical > (Tabuleiro.DIMENSAO_TABULEIRO - 1))) {
            return false;
        }
        return true;
    }

    public final static boolean analisarLinha(int linhaAtual, int movimentoHorizontal) {
        if ((linhaAtual + movimentoHorizontal < 0) || (linhaAtual + movimentoHorizontal > (Tabuleiro.DIMENSAO_TABULEIRO - 1))) {
            return false;
        }
        return true;
    }

    
}
