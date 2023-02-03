package model;

/**
 *
 * @author Geam
 */
public class Tabuleiro {

    public static final int DIMENSAO_TABULEIRO = 8;
    public static final int NUMERO_CELULAS = DIMENSAO_TABULEIRO * DIMENSAO_TABULEIRO;
    public static int[][] tabuleiro = new int[Tabuleiro.DIMENSAO_TABULEIRO][Tabuleiro.DIMENSAO_TABULEIRO];

    /**
     * Preenche todas as posições do tabuleiro(matriz tabuleiro)
     * com valor 0(zero).
     */
    public final static void inicializarTabuleiro() {
        for (int i = 0; i < Tabuleiro.DIMENSAO_TABULEIRO; i++) {
            for (int j = 0; j < Tabuleiro.DIMENSAO_TABULEIRO; j++) {
                tabuleiro[i][j] = 0;
            }
        }
    }

}
