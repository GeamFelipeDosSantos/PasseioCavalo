package model;

import passeio_cavalo.Movimentos;

/**
 *
 * @author Geam
 */
public class Cavalo {

    public static int contagem = 0;
    public static long tempoInicioPasseio;
    public static long tempoFimPasseio;

    /**
     * Método (ação) do cavalo, este método executa os processos necessários
     * para a resolução do problema do passeio do cavalo.
     *
     * @param linhaP int
     * @param colunaP int
     */
    public static void passear(int linhaP, int colunaP) {
        
        tempoInicioPasseio = System.currentTimeMillis();
        contagem = 0;
        
        /*Os vetores "horizontal" e "vertical" são os 
        possíveis deslocamentos para o cavalo. No nosso código, 
        quando fazemos uma jogada x, pegamos o inteiro da posição x em 
        cada um dos vetores e este será o nosso movimento no tabuleiro. 
        Parafraseando, temos 8 movimentos possíveis
        (desconsiderando a situação da casa destino)*/
        int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] vertical = {- 1, -2, -2, -1, 1, 2, 2, 1};

        /*A escolha das casas a percorrer, é feita a partir da seguinte matriz 
        de acessibilidade que representa a acessibilidade de cada célula do 
        tabuleiro. O algoritmo consiste basicamente em escolher a casa com menor 
        acessibilidade.*/
        int[][] matrizAcessibilidade = {
            {2, 3, 4, 4, 4, 4, 3, 2},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {2, 3, 4, 4, 4, 4, 3, 2}
        };
        int linhaAtual, colunaAtual, jogada, linhaAcessibilidade, colunaAcessibilidade;

        /*A casa inicial é a casa que o usuário informar via tela!
        (-1 pois o usuário não informará 0-Zero para primeiras posições)*/
        int a = linhaAtual = linhaP - 1;
        int b = colunaAtual = colunaP - 1;

        /*Inicialização do tabuleiro*/
        Tabuleiro.inicializarTabuleiro();
        matrizAcessibilidade[a][b]--;
        Tabuleiro.tabuleiro[a][b] = 1;
        for (int i = 1; i < Tabuleiro.NUMERO_CELULAS; i++) {
            jogada = 2;
            /*Atualiza a matriz de acessibilidade das casas conforme nos 
            movimentamos pelo tabuleiro*/
            for (int x = 0; x < Tabuleiro.DIMENSAO_TABULEIRO; x++) {
                if (Movimentos.analisarLinha(linhaAtual, horizontal[x]) && Movimentos.analisarColuna(colunaAtual, vertical[x])) {
                    if (matrizAcessibilidade[linhaAtual + horizontal[x]][colunaAtual + vertical[x]] > 0) {
                        matrizAcessibilidade[linhaAtual + horizontal[x]][colunaAtual + vertical[x]]--;
                    }
                }
            }
            /*Nesse momento verificamos as casas que podemos ir e 
            escolhemos as células menos acessíveis, de acordo com a
            matriz de acessibilidade*/
            do {
                int minAc = Tabuleiro.DIMENSAO_TABULEIRO + 1;                
                for (int x = 0; x < Tabuleiro.DIMENSAO_TABULEIRO; x++) {
                    contagem += 1;
                    if (Movimentos.analisarLinha(linhaAtual, horizontal[x]) && Movimentos.analisarColuna(colunaAtual, vertical[x])) {
                        linhaAcessibilidade = linhaAtual + horizontal[x];
                        colunaAcessibilidade = colunaAtual + vertical[x];
                        if (matrizAcessibilidade[linhaAcessibilidade][colunaAcessibilidade] <= minAc) {
                            if (Tabuleiro.tabuleiro[linhaAcessibilidade][colunaAcessibilidade] == 0) {
                                minAc = matrizAcessibilidade[linhaAcessibilidade][colunaAcessibilidade];
                                jogada = x;
                            }
                        }
                    }
                }
            } while (!Movimentos.analisarLinha(linhaAtual, horizontal[jogada]) || !Movimentos.analisarColuna(colunaAtual, vertical[jogada]));
            linhaAtual += horizontal[jogada];
            colunaAtual += vertical[jogada];
            Tabuleiro.tabuleiro[linhaAtual][colunaAtual] = i + 1;
        }
        /*Impressão dos resultados*/
        //Movimentos.ImprimeTab(tab, a, b);
        System.out.println("O número de casas analisadas foi: " + contagem);
        tempoFimPasseio = System.currentTimeMillis();
        System.out.println("O código foi executado em " + (tempoFimPasseio - tempoInicioPasseio) + "ms");
    }

}
