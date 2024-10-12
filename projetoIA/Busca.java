import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.HashSet;

public class Busca {
    // Verificando se o estado é o estado objetivo
    public boolean isGoal(int[][] estadoInicial, int[][] estadoObjetivo) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (estadoInicial[i][j] != estadoObjetivo[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Método para copiar um estado 2D
    public int[][] copiarEstado(int[][] estado) {
        int[][] novoEstado = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(estado[i], 0, novoEstado[i], 0, 3);
        }
        return novoEstado;
    }

    // Movimentação do zero
    public boolean Mover(int[][] estado, int linha, int coluna, String opcao) {
        switch (opcao) {
            case "cima":
                if (linha > 0) {
                    int aux = estado[linha - 1][coluna];
                    estado[linha - 1][coluna] = estado[linha][coluna];
                    estado[linha][coluna] = aux;
                    return true;
                }
                break;

            case "baixo":
                if (linha < 2) {
                    int aux = estado[linha + 1][coluna];
                    estado[linha + 1][coluna] = estado[linha][coluna];
                    estado[linha][coluna] = aux;
                    return true;
                }
                break;

            case "direita":
                if (coluna < 2) {
                    int aux = estado[linha][coluna + 1];
                    estado[linha][coluna + 1] = estado[linha][coluna];
                    estado[linha][coluna] = aux;
                    return true;
                }
                break;

            case "esquerda":
                if (coluna > 0) {
                    int aux = estado[linha][coluna - 1];
                    estado[linha][coluna - 1] = estado[linha][coluna];
                    estado[linha][coluna] = aux;
                    return true;
                }
                break;
        }

        return false;
    }

    // Método de Busca em Profundidade Iterativa (IDS)
    public Node IDS(Node estadoAtual, int limite, int[][] estadoObjetivo) {
        Stack<Node> frontier = new Stack<>();
        Set<Node> visitedStates = new HashSet<>();
        frontier.push(estadoAtual); 

        while (!frontier.isEmpty()) {
            Node noAtual = frontier.pop(); 
           // System.out.println("Expandindo o nó com estado:");
            //printState(noAtual.estado); 

           
            if (isGoal(noAtual.estado, estadoObjetivo)) {
                return noAtual; // Encontrou o nó objetivo
            }

            if (noAtual.pai == null || getDepth(noAtual) < limite) {
                List<Node> possibilidadesJogo = acoesPossiveis(noAtual);

                for (Node possibilidade : possibilidadesJogo) {
                    if (!visitedStates.contains(possibilidade)) {
                        frontier.push(possibilidade); 
                        visitedStates.add(possibilidade); /
                    }
                }
            }
        }
        return null; 
    }

   
    public void printState(int[][] estado) {
        for (int[] linha : estado) {
            for (int numero : linha) {
                System.out.print(numero + " ");
            }
            System.out.println();
        }
    }

    public int getDepth(Node node) {
        int depth = 0;
        while (node.pai != null) {
            depth++;
            node = node.pai;
        }
        return depth;
    }

    
    public List<Node> acoesPossiveis(Node node) {
        List<Node> filhos = new ArrayList<>();
        int linhaZero = -1, colunaZero = -1;

        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (node.estado[i][j] == 0) {
                    linhaZero = i;
                    colunaZero = j;
                }
            }
        }

        
        if (linhaZero > 0) { 
            int[][] novoEstado = copiarEstado(node.estado);
            Mover(novoEstado, linhaZero, colunaZero, "cima");
            filhos.add(new Node(novoEstado, node));
        }
        if (linhaZero < 2) { 
            int[][] novoEstado = copiarEstado(node.estado);
            Mover(novoEstado, linhaZero, colunaZero, "baixo");
            filhos.add(new Node(novoEstado, node));
        }
        if (colunaZero > 0) { 
            int[][] novoEstado = copiarEstado(node.estado);
            Mover(novoEstado, linhaZero, colunaZero, "esquerda");
            filhos.add(new Node(novoEstado, node));
        }
        if (colunaZero < 2) { 
            int[][] novoEstado = copiarEstado(node.estado);
            Mover(novoEstado, linhaZero, colunaZero, "direita");
            filhos.add(new Node(novoEstado, node));
        }

        return filhos;
    }
}
