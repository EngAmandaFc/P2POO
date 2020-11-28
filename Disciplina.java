/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.poo;
import java.util.ArrayList;
import web.DbListener;
import java.sql.*;
/**
 *
 * @author mandi
 */
public class Disciplina {
    long rowId;
    String nome, ementa;
    int ciclo;
    double nota;
    
    
    public Disciplina(long rowId, String nome, String ementa, int ciclo){
        this.rowId = rowId;
        this.nome = nome;
        this.ementa = ementa;
        this.ciclo = ciclo;
    }
    
    public static ArrayList<Disciplina> getList()throws Exception{
        ArrayList<Disciplina> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT rowId, * FROM disciplinas");
            while(rs.next()){
                list.add(new Disciplina(
                        rs.getLong("rowId"),
                        rs.getString("nome"),
                        rs.getString("ementa"),
                        rs.getInt("ciclo")
                ));
            }
        }catch(Exception ex){
            methodException = ex;
        }finally{
            try{rs.close();}catch(Exception ex2){}
            try{stmt.close();}catch(Exception ex2){}
            try{con.close();}catch(Exception ex2){}
        }
        if(methodException != null) throw methodException;
        return list;
    }
    
    public static void insert(String nome, String ementa, int ciclo) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.prepareStatement("INSERT INTO disciplinas VALUES (?,?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, ementa);
            stmt.setInt(3, ciclo);
            stmt.execute();
        }catch(Exception ex){
            methodException = ex;
        }finally{
            try{rs.close();}catch(Exception ex2){}
            try{stmt.close();}catch(Exception ex2){}
            try{con.close();}catch(Exception ex2){}
        }
        if(methodException != null) throw methodException;
    }
    
    public static void update(long rowId, String nome, String ementa, int ciclo) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.prepareStatement("UPDATE disciplinas SET nome=?, ementa=?, ciclo=? "
                    + "WHERE rowId=?");
            stmt.setString(1, nome);
            stmt.setString(2, ementa);
            stmt.setInt(3, ciclo);
            stmt.setLong(4, rowId);
            stmt.execute();
        }catch(Exception ex){
            methodException = ex;
        }finally{
            try{rs.close();}catch(Exception ex2){}
            try{stmt.close();}catch(Exception ex2){}
            try{con.close();}catch(Exception ex2){}
        }
        if(methodException != null) throw methodException;
    }
    
    public static void delete(long rowId) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.prepareStatement("DELETE FROM disciplinas WHERE rowId=?");
            stmt.setLong(1, rowId);
            stmt.execute();
        }catch(Exception ex){
            methodException = ex;
        }finally{
            try{rs.close();}catch(Exception ex2){}
            try{stmt.close();}catch(Exception ex2){}
            try{con.close();}catch(Exception ex2){}
        }
        if(methodException != null) throw methodException;
    }

    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
    public void setEmenta(String ementa){
        this.ementa = ementa;
    }
    public String getEmenta(){
        return ementa;
    }
    public void setCiclo(int ciclo){
        this.ciclo = ciclo;
    }
    public int getCiclo(){
        return ciclo;
    }
    public void setNota(double nota){
        this.nota = nota;
    }
    public double getNota(){
        return nota;
    }
    
    public static String getCreateStatement(){
        return "CREATE TABLE IF NOT EXISTS disciplinas("
                + "nome VARCHAR(50) UNIQUE NOT NULL,"
                + "ementa VARCHAR(500) NOT NULL,"
                + "ciclo NUMBER(2) NOT NULL"
                + ")";
    }
}



