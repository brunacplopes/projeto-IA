import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] estadoInicial = {
            {1, 2, 3},
            {0, 5, 6},
            {4, 7, 8}
        };

        int[][] estadoObjetivo = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };

        // Criação do nó inicial
        Node nodoInicial = new Node(estadoInicial, null); // Sem pai, pois é o estado inicial

        // Instância da classe Busca
        Busca busca = new Busca();

        // Limite de profundidade para a busca
        int limite = 30; // Você pode ajustar este limite conforme necessário

        // Chamada do método de busca
        Node resultado = busca.IDS(nodoInicial, limite, estadoObjetivo);

        // Verifica o resultado
        if (resultado != null) {
            System.out.println("Objetivo encontrado!");
            int movimentos = busca.getDepth(resultado);
            System.out.println("Número de movimentos para alcançar o estado objetivo: " + movimentos);
        } else {
            System.out.println("Objetivo não encontrado dentro do limite.");
        }
    }
}
