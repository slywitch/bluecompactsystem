/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SubPesquisaES_Cliente.java
 *
 * Created on 02/01/2011, 20:32:01
 */

package telas;

import classes.ConexaoPostgresql;
import classes.TextDocument;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jeff
 */
public class SubPesquisaES_Cliente extends javax.swing.JInternalFrame {

    private ConexaoPostgresql Conexao;
    private TextDocument TD;
    
    String Meleca;

    /** Creates new form SubPesquisaES_Cliente */
    public SubPesquisaES_Cliente() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfPesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setTitle("Pesquisa Fornecedor");

        jtfPesquisa.setDocument(TD = new TextDocument(20));
        jtfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfPesquisaKeyPressed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(400);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jtfPesquisa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Pesquisa Fornecedor");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPesquisaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            
            if (Meleca.equals("Cliente")){
                CarregaCliente();
            } else if (Meleca.equals("Produto")) {
                CarregaProduto();
            } else if (Meleca.equals("OS_Cliente")) {
                CarregaCliente();
            } else if (Meleca.equals("OS_Produto")) {
                CarregaProduto();
            } else if (Meleca.equals("IN_Cliente")) {
                CarregaCliente();
            } else if (Meleca.equals("IN_Produto")) {
                CarregaProduto();
            } else if (Meleca.equals("CC_Cliente")) {
                CarregaCliente();
            } else if (Meleca.equals("CP_ContasPagar")) {
                CarregaCliente();
            } else if (Meleca.equals("CP_ContasReceber")) {
                CarregaCliente();
            } else if (Meleca.equals("ControleCartao")) {
                CarregaCliente();
            } else if (Meleca.equals("CPanel")) {
                CarregaCliente();
            }
            
        }
}//GEN-LAST:event_jtfPesquisaKeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

//        if (Meleca.equals("CP_ContasPagar")) {
//            CP.PuxaCliente(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
//        } else if (Meleca.equals("CP_ContasReceber")) {
//            CR.PuxaCliente(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
//        } else if (Meleca.equals("ControleCartao")) {
//            Cartao.PuxaCliente(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
//        } else if (Meleca.equals("CPanel")) {
//            CPanel.PuxaCliente(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
//        }

        this.dispose();
}//GEN-LAST:event_jTable1MouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SubPesquisaES_Cliente().setVisible(true);
            }
        });
    }

    public void centralizar(JDesktopPane dp) {
        Dimension ds = dp.getSize();
        Dimension dw = this.getSize();
        this.setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
    }
    
    public void Validador(String Parametro){
        this.Meleca =  Parametro;
    }

//    public void SetSubPedido(OOSP Ped){
//        this.P = Ped;
//    }
//    
//    public void SetSubContasPagar(ContasPagar ContasPagar){
//        this.CP = ContasPagar;
//    }
//    
//    public void SetSubContasReceber(ContasReceber ContasReceber){
//        this.CR = ContasReceber;
//    }
//    
//    public void SetSubConsultaEntrada(ConsultaEntradaDeProdutos ConsEP){
//        this.CEP = ConsEP;
//    }
//    
//    public void SetSubControleCartao(ControleCartao CCartao){
//        this.Cartao = CCartao;        
//    }
//    
//    public void SetSubControlePainel(ContasPainel CPainel){
//        this.CPanel = CPainel;        
//    }
    
    private void CarregaCliente(){

        String SQL;

        SQL = "select codigo, nome from t_agenda ";
        SQL = SQL + "where nome like '%" + jtfPesquisa.getText() + "%' and exclusao = 0";

        ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
        jTable1.updateUI();

        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);

        ResultSet RS = Conexao.Consultar();

        try{

            while (RS.next()){
                DefaultTableModel Tabela = (DefaultTableModel) jTable1.getModel();
                Object[] CarLinha = {RS.getString("codigo"),RS.getString("nome")};
                Tabela.addRow(CarLinha);
            }

        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação: " + Erro);
        }

        Conexao.Desconectar();

    }

    private void CarregaProduto(){

        String SQL;

        SQL = "select codigo, nome from t_produto ";
        SQL = SQL + "where nome like '%" + jtfPesquisa.getText() + "%' and exclusao = 0";

        ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
        jTable1.updateUI();

        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);

        ResultSet RS = Conexao.Consultar();

        try{

            while (RS.next()){
                DefaultTableModel Tabela = (DefaultTableModel) jTable1.getModel();
                Object[] CarLinha = {RS.getString("codigo"),RS.getString("nome")};
                Tabela.addRow(CarLinha);
            }

        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação: " + Erro);
        }

        Conexao.Desconectar();

    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtfPesquisa;
    // End of variables declaration//GEN-END:variables

}
