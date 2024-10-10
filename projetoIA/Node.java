import java.util.Arrays;
public class Node{
   int [][] estado; //criei o estado linha e coluna
   Node pai; // referencia pai
    int movimentos;

   public Node(int[][] estado, Node pai) {
       this.estado = estado; // contrutoress 
       this.pai = pai;
       this.movimentos = 0;
   }

    // Implementação de hashCode e equals
    
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Node)) return false;
        Node other = (Node) obj;
        return Arrays.deepEquals(this.estado, other.estado);
    }

    public int hashCode() {
        return Arrays.deepHashCode(estado);
    }

    
 }


