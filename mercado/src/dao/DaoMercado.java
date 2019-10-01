/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Mercado;
import java.sql.Date ;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Administrador
 */
public class DaoMercado {
    
    public static boolean inserir(Mercado objeto) {
        String sql = "INSERT INTO mercado (nome_fantasia, razao_social, fundacao, nr_funcionarios, valor_bolsa) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome_fantasia());
            ps.setString(2, objeto.getRazao_social());
            ps.setDate(3, Date.valueOf(objeto.getFundacao()));
            ps.setInt(4, objeto.getNr_funcionarios());
            ps.setDouble(5, objeto.getValor_bolsa());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static void main(String[] args) {
        Mercado objeto = new Mercado();
        objeto.setNome_fantasia("BR");
        objeto.setRazao_social("Brasil");
        objeto.setFundacao(LocalDate.parse("11/01/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setNr_funcionarios(3);
        objeto.setValor_bolsa(222.2);
        
        
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    
      public static boolean alterar(Mercado objeto) {
        String sql = "UPDATE mercado SET nome_fantasia = ?, razao_social = ?, fundacao = ?, nr_funcionarios = ?, valor_bolsa = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome_fantasia());
            ps.setString(2, objeto.getRazao_social());
            ps.setDate(3, Date.valueOf(objeto.getFundacao()));
            ps.setInt(4, objeto.getNr_funcionarios());
            ps.setDouble(5, objeto.getValor_bolsa());
            ps.setInt(6, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
       public static boolean excluir(Mercado objeto) {
        String sql = "DELETE FROM produto WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
       public static List<Mercado> consultar() {
        List<Mercado> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome_fantasia, razao_social, fundacao, nr_funcionarios, valor_bolsa FROM mercado";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mercado objeto = new Mercado();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome_fantasia(rs.getString("nome_fantasia"));
                objeto.setRazao_social(rs.getString("razao_social"));
                objeto.setFundacao(rs.getDate("fundacao").toLocalDate());
                objeto.setNr_funcionarios(rs.getInt("nr_funcionarios"));
                objeto.setValor_bolsa(rs.getDouble("valor_bolsa"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
    
       
        public static Mercado consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome_fantasia, razao_social, fundacao, nr_funcionarios, valor_bolsa FROM mercado WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mercado objeto = new Mercado();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome_fantasia(rs.getString("nome_fantasia"));
                objeto.setRazao_social(rs.getString("razao_social"));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
