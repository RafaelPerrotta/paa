package br.ufmt.co.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class Arquivo {

    private File file;
    private final Logger logger = Logger.getLogger(Arquivo.class);

    public Arquivo() {
    }

    /**
     * Grava o conteúdo no arquivo informado
     *
     * @param nome nome do arquivo na pasta local do projeto
     * @param conteudo texto a ser salvo no arquivo
     * @return true se conseguir gravar os dados no arquivo
     */
    public boolean gravar(String nome, String conteudo) {
        file = new File(nome);
        logger.info("Salvando conteúdo em: " + file.getAbsolutePath());
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file, true);
            fos.write(conteudo.getBytes());
            fos.close();
        } catch (Exception ex) {
            logger.error("Falha ao salvar elemento: " + nome, ex);
            return false;
        }
        return true;

    }

    /**
     * Grava o lista de strings no arquivo informado
     *
     * @param nome nome do arquivo na pasta local do projeto
     * @param list lista de nomes a ser salvo no arquivo
     * @return true se conseguir gravar os dados no arquivo
     */
    public boolean gravar(String nome, ArrayList<String> list) {
        file = new File(nome);
        logger.info("Salvando " + list.size() + " elementos");
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            while (list.size() > 0) {
                try {
                    fos.write(list.get(0).getBytes());
                    String s = " # ";
                    fos.write(s.getBytes());
                } catch (IOException ex) {
                    logger.error("Falha ao escrever no arquivo: " + file.getAbsolutePath(), ex);
                    return false;
                }
                list.remove(0);
            }
        } catch (Exception ex) {
            logger.error("Falha ao gravar dados: ", ex);
            return false;
        }
        return true;
    }

    /**
     * Recupera conteúdo do arquivo informado
     *
     * @param nome nome do arquivo na pasta local do projeto
     * @return conteúdo do arquivo
     */
    public String ler(String nome) {
        file = new File(nome);
        FileInputStream fis;
        String saida = new String();
        try {
            fis = new FileInputStream(file);
            int ln;
            while ((ln = fis.read()) != -1) {
                saida = saida + (char) ln;
            }
            fis.close();
        } catch (Exception ex) {
            logger.error("Falha ao ler arquivo: " + file.getAbsolutePath(), ex);
        }
        return saida;
    }
}
