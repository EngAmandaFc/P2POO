/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.sql.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import br.poo.Disciplina;

/**
 *
 * @author mandi
 */
public class DbListener implements ServletContextListener {
    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:disciplinas.db";
    
    public static String exceptionMessage = null;
    
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(DB_URL);
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String etapa = "Inicialização...";
        Connection con = null;
        Statement stmt = null;
        try{
            etapa = "Registro a classe do driver...";
            Class.forName(CLASS_NAME);
            con = getConnection();
            etapa = "Preparando o statement...";
            stmt = con.createStatement();
            etapa = "Criar a tabela disciplinas...";
            stmt.execute(Disciplina.getCreateStatement());
            if(Disciplina.getList().isEmpty()){
                etapa = "Criar primeiros registros de disciplinas...";
                stmt.execute("INSERT INTO disciplinas VALUES("
                        + "'SO II',"
                        + "'Apresentação de um sistema operacional específico "
                        +"4"
                        +")");
                stmt.execute("INSERT INTO disciplinas VALUES("
                        + "'Banco de Dados',"
                        + "'Conceitos de Base de Dados.Modelos de Dados: Relacional, Redes e Hierárquicos.',"
                        + "4"
                        + ")");
                stmt.execute("INSERT INTO disciplinas VALUES("
                        + "'SEG INF',"
                        + "'Requisitos de segurança de aplicações, de base de dados e de comunicações. "
                        + "espalhamento e árvores.',"
                        + "5"
                        + ")");
                stmt.execute("INSERT INTO disciplinas VALUES("
                        + "'Programação Orientada a Objetos',"
                        + "'Conceitos e evolução da "
                        + "tecnologia de orientação a objetos. Limitações e diferenças entre o paradigma "
                        + "da programação estruturada em relação à orientação a objetos. Conceito de objeto, "
                        + "classe, métodos, atributos, herança, polimorfismo, agregação, associação, " 
                        + "dependência, encapsulamento, mensagem e suas respectivas notações na linguagem "
                        + "padrão de representação da orientação a objetos. Implementação de algoritmos "
                        + "orientado a objetos utilizando linguagens de programação. Aplicação e uso das "
                        + "estruturas fundamentais da orientação a objetos.',"
                        + "4"
                        + ")");
                stmt.execute("INSERT INTO disciplinas VALUES("
                        + "'LP IV',"
                        + "'Estudos de conceitos básicos de "
                        + "Sistemas de Informações. Estudo das aplicações dos Sistemas de Informações nas "
                        + "organizações. Potencial estratégico dos Sistemas de Informações para o negócio. "
                        + "Perspectivas do uso dos Sistemas de Informações no suporte a Processos Gerenciais "
                        + "nas organizações. A Tecnologia da Informação como infraestrutura para os sistemas "
                        + "de informações.',"
                        + "4"
                        + ")");
                stmt.execute("INSERT INTO disciplinas VALUES("
                        + "'ENG SOF III',"
                        + "'Conceitos, evolução e importância de arquitetura de software. "
                        + "4"
                        + ")");
                stmt.execute("INSERT INTO disciplinas VALUES("
                        + "'Inglês V',"
                       + "'Consolidação da compreensão e produção oral e escrita por meio da integração das "
                        + "habilidades lingüístico-comunicativas desenvolvidas na disciplina Inglês 4. Ênfase na"
                        + " oralidade, atendendo às especificidades acadêmico-profissionais da área e abordando"
                        + " aspectos sócio-culturais da língua inglesa.',"
                        + "5"
                        + ")");
                stmt.execute("INSERT INTO disciplinas VALUES("
                        + "'Inglês IV',"
                        + "'Consolidação da compreensão e produção oral e escrita por meio da integração das "
                        + "habilidades lingüístico-comunicativas desenvolvidas na disciplina Inglês 3. Ênfase na"
                        + " oralidade, atendendo às especificidades acadêmico-profissionais da área e abordando"
                        + " aspectos sócio-culturais da língua inglesa.',"
                        + "4"
                        + ")");
            }
            etapa = "Desconectar do banco de dados...";
        }catch(Exception ex){
            exceptionMessage = etapa + ": " + ex.getLocalizedMessage();
        }finally{
            try{stmt.close();}catch(Exception ex2){};
            try{con.close();}catch(Exception ex2){};
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
    




