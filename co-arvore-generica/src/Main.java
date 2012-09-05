
import br.ufmt.co.tree.ArvoreGenerica;
import br.ufmt.co.tree.Nodo;
import org.apache.log4j.Logger;

public class Main {

    static ArvoreGenerica ag = new ArvoreGenerica();
    static Nodo raiz = new Nodo();
    static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        raiz.setO("NOMES");
        ag.inserirFilho(raiz, "A");
        ag.inserirIrmao(raiz.getFilho(), "Andréia");
        ag.inserirIrmao(raiz.getFilho(), "Ana");
        ag.inserirIrmao(raiz.getFilho(), "Alberto");
        ag.inserirFilho(raiz.getFilho(), "B");
        ag.inserirIrmao(raiz.getFilho().getFilho(), "Bianca");
        ag.inserirFilho(raiz.getFilho().getFilho(), "C");
        ag.inserirIrmao(raiz.getFilho().getFilho().getFilho(), "Carla");
        ag.inserirIrmao(raiz.getFilho().getFilho().getFilho(), "Cristina");
        ag.inserirFilho(raiz.getFilho().getFilho().getFilho(), "D");
        ag.inserirIrmao(raiz.getFilho().getFilho().getFilho().getFilho(), "Diana");
        ag.inserirFilho(raiz.getFilho().getFilho().getFilho().getFilho().getIrmao(), "Dianinha");
        ag.inserirFilho(raiz.getFilho().getFilho().getFilho().getFilho(), "E");

        Nodo nodo = ag.buscar(raiz, "Alberto");
        if (nodo != null) {
            logger.info("Nodo Encontrado: " + nodo.getO());
        } else {
            logger.info("Nodo não encontrado");
        }

        ag.setRaiz(raiz);
        if (ag.remove(raiz, "Dianinha")) {
            logger.info("Nodo removido com sucesso");
        }
        
        raiz = ag.getRaiz();
        if (ag.salvar(raiz, "teste")) {
            logger.info("Nó salvo com sucesso");
        }

        ag.resetExibir();
        ag.exibir(raiz);
        logger.info(ag.getExibir());
    }
}
