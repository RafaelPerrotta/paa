package br.ufmt.co.tree;

public class Nodo {

    private Object o;
    private Nodo pai;
    private Nodo filho;
    private Nodo irmao;

    public Nodo() {
    }

    public Nodo getFilho() {
        return filho;
    }

    public void setFilho(Nodo filho) {
        this.filho = filho;
    }

    public Nodo getIrmao() {
        return irmao;
    }

    public void setIrmao(Nodo irmao) {
        this.irmao = irmao;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public Nodo getPai() {
        return pai;
    }

    public void setPai(Nodo pai) {
        this.pai = pai;
    }

    public boolean equals(Nodo nodo) {
        if (pai == nodo.getPai()) {
            if (filho == nodo.getFilho()) {
                if (irmao == nodo.getIrmao()) {
                    return true;
                }
            }
        }
        return false;
    }
}
