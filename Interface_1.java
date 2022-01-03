package InterfaceEx1;

class Reacao {
    private int X;
    private int Y;
    private int A;
    private int B;
    private int C;
    
    public Reacao(int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.A = 0;
        this.B = 0;
        this.C = 0;
    }
    
    public void adicionarA(int quantidade) {
        this.A += quantidade;
    }
    
    public void adicionarB(int quantidade) {
        this.B += quantidade;
    }
    
    public void agitar () { 
        int moduloA = this.A % this.X;
        int moduloB = this.B % this.Y;
        int quocienteA = this.A/this.X;
        int quocienteB = this.B/this.Y;
        int quantidadeC = 0;
        if (moduloA == 0 && moduloB == 0) {
            if (quocienteA < quocienteB) {
                quantidadeC = quocienteA;
            } else if (quocienteB < quocienteA) {
                quantidadeC = quocienteB;
            } else {
                quantidadeC = quocienteA;
            }
            this.C+= quantidadeC;
            this.A = this.A - this.X*quantidadeC;
            this.B = this.B - this.Y*quantidadeC;
        }
    }
    
    public int getC() {
        return this.C;
    }
    
    
}

public class InterfaceEx1 {
    private static void testarReacao(){
        int totalTestes = 7;
        int testesCorretos = 0;
        System.out.println("### Reacao\n");
        Reacao r = new Reacao(1, 1);
        testesCorretos += rodarTeste("Valores iniciais",r.getC() == 0);
        r.adicionarA(1);
        r.adicionarB(1);
        System.out.println(r.getC());
        testesCorretos += rodarTeste("Se não agitar, continua igual", r.getC() == 0);
        r.agitar();
        testesCorretos += rodarTeste("Se agitar, produz C", r.getC() == 1);
        r.agitar();
        testesCorretos += rodarTeste("Se agitar de novo, não produz mais C", r.getC() == 1);

        r = new Reacao(2, 3);
        r.adicionarA(6);
        r.adicionarB(6);
        r.agitar();
        testesCorretos += rodarTeste("C produzido na quantidade correta", r.getC() == 2);
        r.adicionarB(6);
        r.agitar();
        testesCorretos += rodarTeste("Sobra de A pode ser reutilizada", r.getC() == 3);
        r.adicionarA(2);
        r.agitar();
        testesCorretos += rodarTeste("Sobra de B pode ser reutilizada", r.getC() == 4);

        mostrarResultado(testesCorretos, totalTestes);
    }

    private static int rodarTeste(String titulo, boolean resultado){
        System.out.println("- " + (resultado ? "OK" : "X ") + "\t" + titulo);
        return resultado ? 1 : 0;
    }

    private static void mostrarResultado(int testesCorretos, int totalTestes){
        System.out.println("\n> Testes corretos: " + testesCorretos + "/" + totalTestes + " ("+(100*testesCorretos/totalTestes)+"%)");
        if(testesCorretos == totalTestes){
            System.out.println("> Tudo certo!!!");
        } else {
            System.out.println(">  Ainda falta um pouquinho, mas você consegue!");
        }
    }

    public static void main(String[] args) {
        testarReacao();
        System.out.println();
    }
}