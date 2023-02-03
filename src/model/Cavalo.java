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
     * Método (ação) do cavalo, este método executa os processos
     * necessários para a resolução do problema do passeio do cavalo.
     * @param linha int
     * @param coluna int 
     */
    public static void passear(int linha, int coluna) {
        tempoInicioPasseio = System.currentTimeMillis();
        
        /*Os vetores "horizontal" e "vertical" são os 
        possíveis deslocamentos para o cavalo. No nosso código, 
        quando fazemos uma jogada x, pegamos o inteiro da posição x em 
        cada um dos vetores e este será o nosso movimento no tabuleiro. 
        Parafraseando, temos 8 movimentos possíveis
        (desconsiderando a situação da casa destino)*/
        int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] vertical = {- 1, -2, -2, -1, 1, 2, 2, 1};

        /*Para a resolução do problema proposto, foi escolhida uma heurística a 
        tempoFimPasseio de escolher o destino do cavalo, isto é, para qual casa se deslocar 
        a partir da atual. A escolha é feita a partir da seguinte matriz que 
        representa a acessibilidade de cada célula do tabuleiro. Em outras 
        palavras, de quantas formas consigo ter a célula como destino. 
        O algoritmo consiste basicamente em escolher a casa com menor 
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
        int linhaAtual, colunaAtual, jogada, /*maxTab, minTab,*/ linhaAcessibilidade, colunaAcessibilidade;

        /*Como exigido, a casa inicial é escolhida de forma randômica*/
        //int a = linAt = random.nextInt(Tabuleiro.DIMENSAO_TABULEIRO);        
        //int b = colAt = random.nextInt(Tabuleiro.DIMENSAO_TABULEIRO);
        
        linhaAtual = linha--;
        colunaAtual = coluna--;

        /*a--;
        b--;
        linhaAtual--;
        colunaAtual--;
        */
        /*Como temos um tabuleiro com 8 linhas e 8 colunas (8 *8), temos que 
        o índice máximo será 7 para linha e para coluna, uma vez que a 
        contagem começa de 0.*/
        //maxTab = dimensoes.dimensaoTabuleiro - 1;
        //minTab = 0;

        /*Inicialização do tabuleiro*/
        Tabuleiro.inicializarTabuleiro();
        matrizAcessibilidade[linha][coluna]--;
        Tabuleiro.tabuleiro[linha][coluna] = 1;
        for (int i = 1; i < Tabuleiro.NUMERO_CELULAS; i++) {
            jogada = 2;
            /*Atualiza a acessibilidade das casas conforme nos movimentamos no 
            tabuleiro*/
            for (int x = 0; x < Tabuleiro.DIMENSAO_TABULEIRO; x++) {
                if (Movimentos.analisarLinha(linhaAtual, horizontal[x]) && Movimentos.analisarColuna(colunaAtual, vertical[x])) {
                    if (matrizAcessibilidade[linhaAtual + horizontal[x]][colunaAtual + vertical[x]] > 0) {
                        matrizAcessibilidade[linhaAtual + horizontal[x]][colunaAtual + vertical[x]]--;
                    }
                }
            }
            /*Aqui encontra-se a implementação da parte mais importante do 
            algoritmo. Nesse momento verificamos as casas que podemos ir e 
            escolhemos a com menor acessibilidade*/
            do {
                int celulaMenosAcessivel = Tabuleiro.DIMENSAO_TABULEIRO + 1;
                /*int minAcLin, minAcCol;
                
                linhaAcessibilidade = linhaAtual;
                colunaAcessibilidade = colunaAtual;
                minAcLin = linhaAcessibilidade;
                minAcCol = colunaAcessibilidade;*/
                
                for (int x = 0; x < Tabuleiro.DIMENSAO_TABULEIRO; x++) {
                    contagem += 1;
                    if (Movimentos.analisarLinha(linhaAtual, horizontal[x]) && Movimentos.analisarColuna(colunaAtual, vertical[x])) {
                        linhaAcessibilidade = linhaAtual + horizontal[x];
                        colunaAcessibilidade = colunaAtual + vertical[x];
                        if (matrizAcessibilidade[linhaAcessibilidade][colunaAcessibilidade] <= celulaMenosAcessivel) {
                            if (Tabuleiro.tabuleiro[linhaAcessibilidade][colunaAcessibilidade] == 0) {
                                celulaMenosAcessivel = matrizAcessibilidade[linhaAcessibilidade][colunaAcessibilidade];
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
        //Movimentos.imprimirTabuleiro(Tabuleiro.tabuleiro, a, b);
        //System.out.println("O número de casas analisadas foi: " + contagem);
        tempoFimPasseio = System.currentTimeMillis();
        //System.out.println("O código foi executado em " + (tempoFimPasseio - tempoInicioPasseio) + "ms");
        System.out.println(tempoInicioPasseio);
        System.out.println(tempoFimPasseio);
        System.out.println("O código foi executado em " + (tempoFimPasseio - tempoInicioPasseio) + "ms");
    }
}
