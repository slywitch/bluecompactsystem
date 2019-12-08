/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Jeff
 */
public class Funcoes {

    private ConexaoPostgresql Conexao;

    public String NomeCidade(int Codigo){

        String SQL, strCidade=null;
        SQL = "select nome from t_cidade where codigo = " + Codigo + " and exclusao = 0 ";
        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);
        ResultSet RS = Conexao.Consultar();

        try{
            if (RS.next()){
                strCidade = (RS.getString("nome"));
            }
        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação da função cidade: " + Erro);
        }

        //System.out.println(Conexao.getSQL());
        Conexao.Desconectar();

        return strCidade;

    }

    public String NomeEstado(int Codigo){

        String SQL, strEstado=null;
        SQL = "select nome from t_estado where codigo = " + Codigo;
        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);
        ResultSet RS = Conexao.Consultar();

        try{
            if (RS.next()){
                strEstado = (RS.getString("nome"));
            }
        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação da função Estado: " + Erro);
        }

        //System.out.println(Conexao.getSQL());
        Conexao.Desconectar();

        return strEstado;

    }

    public String Proximo(String Campo, String Tabela, String Condicao){

        String SQL;
        String Codigo = null;

//        SQL = "select (max(" + Campo + ") + 1) as prox from " + Tabela + " " + Condicao; //PostgreSQL
        SQL = "select (count(" + Campo + ") + 1) as prox from " + Tabela + " " + Condicao; //MySQL

        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);

        ResultSet RS = Conexao.Consultar();

        try{
            if (RS.next()){
                Codigo = (RS.getString("prox"));
            }
        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação da função: " + Erro);
        }

        Conexao.Desconectar();

        return Codigo;

    }

    public String SiglaEstado(int Codigo){

        String SQL, strSigla=null;

        SQL = "select tes.sigla ";
        SQL = SQL + "from t_estado tes ";
        SQL = SQL + "inner join t_cidade tcd on tcd.estado_cod = tes.codigo ";
        SQL = SQL + "where tcd.codigo = " + Codigo;

        System.out.println(SQL);

        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);
        ResultSet RS = Conexao.Consultar();

        try{
            if (RS.next()){
                strSigla = (RS.getString("sigla"));
            }
        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação da função sigla: " + Erro);
        }

        Conexao.Desconectar();

        return strSigla;

    }

//    public String Proximo (String Tabela){
//
//        String SQL, strProximo=null;
//
//        SQL = "select (count(*) + 1) as prox from " + Tabela;
//
//        Conexao = new ConexaoPostgresql();
//        Conexao.Conectar() ;
//        Conexao.setSQL(SQL);
//        ResultSet RS = Conexao.Consultar();
//
//        try{
//            if (RS.next()){
//                strProximo = (RS.getString("prox"));
//            }
//        }catch (Exception Erro){
//            JOptionPane.showMessageDialog(null, "Erro na validação da função próximo: " + Erro);
//        }
//
//        Conexao.Desconectar();
//
//        return strProximo;
//
//    }

    public static DefaultFormatterFactory setFormatoData(){

        MaskFormatter comFoco = null;
        try{
            comFoco = new MaskFormatter("##/##/####");
        }
        catch (Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar o campo data! " + erro);
        }
        DefaultFormatterFactory factory = new DefaultFormatterFactory(comFoco, comFoco);

        return factory;

     }

    public static DefaultFormatterFactory setFormatoTelefone(){

        MaskFormatter comFoco = null;
        try{
            comFoco = new MaskFormatter("(##) #### - ####");
        }
        catch (Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar o campo telefone! " + erro);
        }
        DefaultFormatterFactory factory = new DefaultFormatterFactory(comFoco, comFoco);

        return factory;

     }

    public static DefaultFormatterFactory setFormatoHora(){

        MaskFormatter comFoco = null;
        try{
            comFoco = new MaskFormatter("##:##");
        }
        catch (Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar o campo hora! " + erro);
        }
        DefaultFormatterFactory factory = new DefaultFormatterFactory(comFoco, comFoco);

        return factory;

     }

    public static DefaultFormatterFactory setFormatoMoeda(){

        MaskFormatter comFoco = null;
        try{
            comFoco = new MaskFormatter("R$ ###,###,##0.00");
        }
        catch (Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar o campo moeda! " + erro);
        }
        DefaultFormatterFactory factory = new DefaultFormatterFactory(comFoco, comFoco);

        //java.text.DecimalFormat df = new java.text.DecimalFormat("R$ ###,###,##0.00");

        return factory;

    }

    public static DefaultFormatterFactory setFormatoCep(){

        MaskFormatter comFoco = null;
        try{
            comFoco = new MaskFormatter("#####-###");
        }
        catch (Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar o campo CEP! " + erro);
        }
        DefaultFormatterFactory factory = new DefaultFormatterFactory(comFoco, comFoco);

        //java.text.DecimalFormat df = new java.text.DecimalFormat("R$ ###,###,##0.00");

        return factory;

    }
    
    public String FuncaoSQL (String SQL){

        String Resultado = null;

        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);
        ResultSet RS = Conexao.Consultar();

        try{
            if (RS.next()){
                Resultado = RS.getString("retorno");
            }
        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação da função SQL: " + Erro);
        }

        Conexao.Desconectar();

        return Resultado;

    }
    
    public String RetornaData () {
        
        String RetData = null;        
        String SQL = null;
        
        SQL = "SELECT (CURRENT_DATE) as data_atual ";
        
        return RetData = FuncaoSQL(SQL);
        
    }

    public String VerificaCampoFormatado(String Texto){

        String Resultado = null;

        System.out.println("Texto: " + Texto);

        if ((Texto.equals("  /  /    ")) || (Texto.equals("(  )      -     "))){
            Resultado = null;
            System.out.println("Resultado: " + Resultado);
        }

        return Resultado;

    }

    public boolean isValidDate(String inDate) {

        if (inDate == null) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (inDate.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }

        dateFormat.setLenient(false);

        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }

        return true;

    }

    public boolean isValidTime(String horario) {

        String hora = horario.toString();

        if (hora.equals("  :  ")) {
            //JOptionPane.showMessageDialog(null, "Digite a hora" ,"Operador",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String horas = null;
        String minutos = null ;
        int conta_horas = 0;
        int conta_minutos = 0;
        horas  = hora.substring(0,2);
        minutos  = hora.substring(3,5);
        conta_horas = Integer.parseInt(horas);
        conta_minutos = Integer.parseInt(minutos);

        if(conta_horas > 23) {
            //JOptionPane.showMessageDialog(null, "Hora digitada inválida" ,"Operador",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(conta_minutos > 59) {
            //JOptionPane.showMessageDialog(null, "Hora digitada inválida" ,"Operador",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;

    }
    
    public Double CasaDecimal(String Valor){
        
        double valordecimal = 0;
        
        Valor = Valor.replaceAll(",", ".");  
        valordecimal = Double.parseDouble(Valor); 
        
        return valordecimal;
        
    }
    
    public Double FormatDecimal(Double Valor){
    
        //Float numero = 0;
        
        DecimalFormat df = new DecimalFormat("#,###.00");  
        df.format(Valor);
//        numero = CasaDecimal(String.valueOf(Valor));
        
        return Valor;
  
    
    }
    
    public String NomeProduto(int Codigo){

        String SQL, strProduto=null;
        
        SQL = "select (codigo_fornecedor || ' - ' || nome) as produto from t_produto where codigo = " + Codigo + " and exclusao = 0 ";
        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);
        ResultSet RS = Conexao.Consultar();

        try{
            if (RS.next()){
                strProduto = (RS.getString("produto"));
            }
        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação da função produto: " + Erro);
        }
        
        Conexao.Desconectar();

        return strProduto;

    }

    public String NomePessoa(int Codigo){

        String SQL, strFabricante=null;
        SQL = "select nome from t_pessoa where codigo = " + Codigo + " and exclusao = 0 ";
        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);
        ResultSet RS = Conexao.Consultar();

        try{
            if (RS.next()){
                strFabricante = (RS.getString("nome"));
            }
        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação da função fabricante: " + Erro);
        }
        
        Conexao.Desconectar();

        return strFabricante;

    }
    
    public Boolean EstoqueOrcamento(){
        
        Boolean Verificacao = false, temp_verifica = false;
        String SQL, strEstoque = "0";
        
        SQL = "select estoque_orcamento from t_preferencia where codigo = 1";
        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);
        ResultSet RS = Conexao.Consultar();
        
        try{
            if (RS.next()){
                strEstoque = (RS.getString("estoque_orcamento"));
            }
        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação da função estoque orçamento: " + Erro);
        }
        
        Conexao.Desconectar();
        
        if (strEstoque.equals("1")){        
            temp_verifica = true;
        }
        
        return Verificacao = temp_verifica;
        
    }
    
}