public class Player {
    private double saldo;
    private int vitorias;
    private int derrotas;

    Player(double saldo, int vitorias, int derrotas) {
        this.saldo = saldo;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getVitorias() {
        return this.vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return this.derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public void adicionarVitorias(){
        this.vitorias++;
    }

    public void adicionarDerrotas(){
        this.derrotas++;
    }

}
