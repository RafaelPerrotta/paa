package br.ufmt.co.tree;

import br.ufmt.co.utils.Arquivo;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class ArvoreGenerica {

    private Nodo raiz, busca;
    private String exibir = new String();
    private ArrayList<String> list = new ArrayList<>();
    private boolean chave = true;
    private final Logger logger = Logger.getLogger(ArvoreGenerica.class);

    /**
     * Class constructor.
     */
    public ArvoreGenerica() {
    }

    /**
     * Define o nó raiz da estrutura
     *
     * @param raiz nó raiz da árvore
     * @see Nodo
     */
    public void inicializar(Nodo raiz) {
        this.raiz = raiz;
    }

    /**
     * Insere um novo filho ao nó pai informado
     *
     * @param pai nó pai a ser atribuído ao novo filho
     * @param o novo objeto a ser salvo na estrutura (será encapsulado em um
     * objeto Node)
     * @see Nodo
     */
    public void inserirFilho(Nodo pai, Object o) {
        logger.debug("Início método inserirFilho");
        Nodo nodo = new Nodo();
        nodo.setO(o);
        nodo.setFilho(null);
        nodo.setIrmao(null);
        nodo.setPai(pai);
        if (pai == null) {
            logger.error("Não foi possível adicionar filho ao nó " + pai);
        } else {
            if (pai.getFilho() == null) {
                pai.setFilho(nodo);
            } else {
                inserirIrmao(pai.getFilho(), o);
            }
            logger.debug("Nó filho adicionado");
        }
    }

    /**
     * Insere um novo irmão ao nó pai informado
     *
     * @param filho nó filho a ser atribuído ao novo irmão
     * @param o novo objeto a ser salvo na estrutura (será encapsulado em um
     * objeto Node)
     * @see Nodo
     */
    public void inserirIrmao(Nodo filho, Object o) {
        logger.debug("Início método inserirIrmao");
        Nodo nodo = new Nodo();
        nodo.setO(o);
        nodo.setFilho(null);
        nodo.setIrmao(null);
        nodo.setPai(filho.getPai());
        if (filho == null) {
            logger.error("Não foi possível adicionar irmão ao nó " + filho);
        } else {
            if (filho.getIrmao() == null) {
                filho.setIrmao(nodo);
            } else {
                Nodo aux = filho.getIrmao();
                while (aux.getIrmao() != null) {
                    aux = aux.getIrmao();
                }
                if (aux.getIrmao() == null) {
                    aux.setIrmao(nodo);
                } else {
                    logger.error("Não foi possível adicionar irmão ao nó " + filho);
                }
            }
        }
        logger.debug("Irmão adicionado");
    }

    /**
     * Cria um novo nó e o atribui como pai do nó informado
     *
     * @param filho nó filho a ser atribuído ao novo pai
     * @param o novo objeto a ser salvo na estrutura (será encapsulado em um
     * objeto Node)
     * @see Nodo
     */
    public void inserirPai(Nodo filho, Object o) {
        logger.debug("Início método inserirPai");
        if (filho == null) {
            logger.error("Não foi possível adicionar pai ao nó: " + filho);
        } else {
            if (filho.getPai() == null) {
                Nodo nodo = new Nodo();
                nodo.setO(o);
                nodo.setFilho(filho);
                nodo.setIrmao(null);
                nodo.setPai(null);
                filho.setPai(nodo);
            } else {
                logger.error("Pai existente, Não foi possível adicionar pai ao nó: ");

            }
        }
        logger.debug("Pai adicionado");
    }

    /**
     * Imprime a estrutura na forma de lista a partir do nó informado
     *
     * @param nodo nó raiz
     * @see Nodo
     */
    public void exibir(Nodo nodo) {
        if (nodo != null) {
            exibir = exibir + nodo.getO() + "\n ";
            exibir(nodo.getIrmao());
            exibir(nodo.getFilho());
        }
    }

    /**
     * Imprime a estrutura para armazenamento
     *
     * @param nodo nó raiz
     * @see Nodo
     */
    private void exibirSalvar(Nodo nodo) {
        if (nodo != null) {
            String text;
            if (nodo.getPai() == null) {
                text = nodo.getO().toString() + " - NULL";
            } else {
                text = nodo.getO().toString() + " - " + nodo.getPai().getO();
            }
            list.add(text);
            exibirSalvar(nodo.getIrmao());
            exibirSalvar(nodo.getFilho());
        }
    }

    /**
     * Salva a estrutura em um arquivo texto
     *
     * @param nodo nó raiz
     * @param nome nome do arquivo onde será salvo a estrutura (pasta local do
     * projeto)
     * @see Nodo
     */
    public boolean salvar(Nodo nodo, String nome) {
        limparList();
        exibirSalvar(nodo);
        Arquivo arquivo = new Arquivo();
        return arquivo.gravar(nome, list);
    }

    /**
     * Apaga toda a árvore
     *
     * @param nodo nó raiz
     * @see Nodo
     */
    public void apagarArvore(Nodo nodo) {
        nodo = null;
        raiz = nodo;
    }

    /**
     * Busca por um objeto na árvore
     *
     * @param nodo nó raiz
     * @param o objeto a ser encontrado
     * @see Nodo
     */
    public Nodo buscar(Nodo nodo, Object o) {
        busca = null;
        procurar(nodo, o);
        return busca;
    }

    /**
     * Realiza a busca recursiva pelo nó informado
     *
     * @param nodo nó raiz
     * @param o objeto a ser encontrado
     * @see Nodo
     */
    private void procurar(Nodo nodo, Object o) {
        if (nodo != null) {
            if (nodo.getO() == o) {
                logger.debug("Encontrei o nó procurado!");
                busca = nodo;
            }
            procurar(nodo.getIrmao(), o);
            procurar(nodo.getFilho(), o);
        }
    }

    /**
     * Remove um nó da árvore informada
     *
     * @param nodo nó a ser removido
     * @see Nodo
     */
    public void remove(Nodo nodo) {
        chave = false;
        boolean key = true; //  Operação não realizada
        boolean erro = false;

        if (key && nodo == null) { // O nodo é nulo
            logger.debug("O nodo é nulo");
            key = false;
            erro = true;
        }
        if (key && buscar(raiz, nodo.getO()) == null) { // O nodo não existe na árvore
            logger.debug("O nodo não existe na árvore");
            key = false;
            erro = true;
        }

        if (key && raiz == nodo) { // A raiz deve ser removida
            logger.debug("A raiz deve ser removida");
            if (key && nodo.getFilho() == null && nodo.getIrmao() == null) { // A árvore só tem o nodo raiz!
                logger.debug("A árvore só tem o nodo raiz");
                nodo = null;
                raiz = nodo; // Atualizando a raiz
                key = false;
            }
            if (key && nodo.getIrmao() == null && nodo.getFilho() != null) { // A raiz é um elemento único
                logger.debug("A raiz é um elemento único");
                raiz = nodo.getFilho();
                nodo = raiz; // Atualizando a raiz
                while (nodo != null) {
                    nodo.setPai(null);
                    nodo = nodo.getIrmao();
                }
                key = false;
            }
            if (key && nodo.getIrmao() != null && nodo.getFilho() == null) { // A raiz possui apenas irmão
                logger.debug("A raiz possui apenas irmão");
                nodo = nodo.getIrmao();
                raiz = nodo; // Atualizando a raiz
                key = false;
            }
            if (key && nodo.getIrmao() != null && nodo.getFilho() != null) { // A raiz possui irmão e filho, ou seja, é composta
                logger.debug("A raiz é composta e não será removida por NÃO TER SIDO IMPLEMENTADA");

                key = false;
            }
        }

        if (key && nodo.getPai() == null) { // É um elemento da raiz composta, ou seja, é um irmão da verdadeira raiz
            logger.debug("É um irmão da raiz");
            Nodo auxRaiz = raiz;
            while (auxRaiz.getIrmao() != nodo) {
                auxRaiz = auxRaiz.getIrmao();
            }
            if (key && nodo.getFilho() == null && nodo.getIrmao() == null) { // Se o nodo é uma folha
                nodo = null;
                auxRaiz.setIrmao(nodo);
                key = false;
            }
            if (key && nodo.getFilho() != null && nodo.getIrmao() == null) { // Se o nodo possui filho e não possui irmão
                Nodo filho = nodo.getFilho();
                auxRaiz.setIrmao(nodo.getFilho());
                while (filho != null) {
                    filho.setPai(null);
                    filho = filho.getIrmao();
                }
                key = false;
            }
            if (key && nodo.getFilho() == null && nodo.getIrmao() != null) { // Se o nodo não possui filho e possui irmão
                nodo = nodo.getIrmao();
                auxRaiz.setIrmao(nodo);
                key = false;
            }
            if (key && nodo.getFilho() != null && nodo.getIrmao() != null) { // Se o nodo possui filho e irmão
                logger.debug("O nodo é irmão de uma raiz composta e não será removida por NÃO TER SIDO IMPLEMENTADA");
                key = false;
            }
        }


        if (key && nodo.getFilho() == null && nodo.getIrmao() == null) { // O nodo é uma folha
            logger.debug("O nodo é uma folha");
            Nodo pai = nodo.getPai(); // Recebendo o pai do nodo a ser removido
            Nodo filho = pai.getFilho(); // Recebendo o primeiro filho do pai do nodo a ser removido
            if (filho == nodo) { // Se o nodo for o único filho de seu pai
                nodo = null;
                pai.setFilho(nodo);
            } else { // Se o nodo tiver irmãos mais velhos
                while (filho.getIrmao() != nodo) { // Enquanto não achar o irmão mais velho mais próximo ao nodo a ser removido
                    filho = filho.getIrmao(); // Avançar a linha de irmãos na árvore
                }
                nodo = null;
                filho.setIrmao(nodo); // Removendo a FOLHA
            }
            key = false;
        }

        if (key && nodo.getFilho() != null && nodo.getIrmao() == null) { // O nodo possui filho e não possui irmão
            logger.debug("O nodo possui filho e não possui irmão");
            Nodo pai = nodo.getPai();
            Nodo filho = pai.getFilho();
            if (filho == nodo) { // O nodo é filho único
                filho = filho.getFilho();
                pai.setFilho(filho);
                nodo = filho;
                while (nodo != null) {
                    nodo.setPai(pai);
                    nodo = nodo.getIrmao();
                }
                key = false;
            } else { // O nodo não é filho único
                while (filho.getIrmao() != nodo) {
                    filho = filho.getIrmao();
                }
                filho.setIrmao(nodo.getFilho());
                nodo = nodo.getFilho();
                while (nodo != null) {
                    nodo.setPai(pai);
                    nodo = nodo.getIrmao();
                }
                key = false;
            }
        }

        if (key && nodo.getFilho() == null && nodo.getIrmao() != null) { // O nodo não possui filho e possui irmão
            logger.debug("O nodo não possui filho e possui irmão");
            Nodo pai = nodo.getPai();
            Nodo filho = pai.getFilho();
            if (filho == nodo) { // O nodo é o primeiro filho
                nodo = nodo.getIrmao();
                pai.setFilho(nodo);
                key = false;
            } else { // Ele não é o primeiro filho nem o último
                while (filho.getIrmao() != nodo) {
                    filho = filho.getIrmao();
                }
                nodo = nodo.getIrmao();
                filho.setIrmao(nodo);
                key = false;
            }
        }

        if (key && nodo.getFilho() != null && nodo.getIrmao() != null) { // O nodo possui filho e irmão
            logger.debug("O nodo possui filho e irmão");
            Nodo pai = nodo.getPai();
            logger.debug(pai.getO());
            Nodo filho = pai.getFilho();
            if (filho == nodo) { // O nodo é o primeiro filho
                pai.setFilho(filho.getIrmao());
                filho = pai.getFilho();
                nodo = nodo.getFilho();
                Nodo aux = filho.getFilho();
                filho.setFilho(nodo);
                while (nodo.getIrmao() != null) {
                    nodo = nodo.getIrmao();
                }
                nodo.setIrmao(aux);
                key = false;
            } else { // O nodo é um dos intermediários da lista
                while (filho.getIrmao() != nodo) {
                    filho = filho.getIrmao();
                }
                filho.setIrmao(nodo.getIrmao());
                nodo = nodo.getFilho();
                Nodo aux = filho.getFilho();
                if (aux == null) {
                    filho.setFilho(nodo);
                } else {
                    while (aux.getIrmao() != null) {
                        aux = aux.getIrmao();
                    }
                    aux.setIrmao(nodo);
                }
                key = false;
            }
        }

        chave = key;

        if (erro) {
            chave = true;
        }
    }

    /**
     * Remove um nó da árvore informada que possui o objeto
     *
     * @param nodo nó a ser removido
     * @param o objeto a ser removido
     * @see Nodo
     */
    public boolean remove(Nodo nodo, Object o) {
        Nodo buscado = buscar(nodo, o);
        if (buscado != null) {
            remove(buscado);
            if (isChave()) {
                return true;
            }
        }
        return false;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public void resetExibir() {
        exibir = new String();
    }

    public String getExibir() {
        return exibir;
    }

    private boolean isChave() {
        return !chave;
    }

    public void limparList() {
        while (list.size() > 0) {
            list.remove(0);
        }
    }
}