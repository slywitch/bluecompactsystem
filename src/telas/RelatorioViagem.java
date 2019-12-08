/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import classes.ConexaoPostgresql;
import classes.Funcoes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Estoque
 */
public class RelatorioViagem extends javax.swing.JInternalFrame {
    
    private ConexaoPostgresql Conexao;
    private Funcoes Fun;
    
    int CapturarLinha;

    /**
     * Creates new form RelatorioViagem
     */
    public RelatorioViagem() {
        initComponents();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RelatorioViagem().setVisible(true);
            }
        });
    }

    public void centralizar(JDesktopPane dp) {
        Dimension ds = dp.getSize();
        Dimension dw = this.getSize();
        this.setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
    }
    
    private void BuscaViagem(){
        
        String SQL;
        String CodigoTecnico = String.valueOf(jTable1.getValueAt(CapturarLinha, 0));
        
        SQL = "select tv.codigo, tv.data_abrir, tv.data_finalizar, tv.destino, (tp.nome) as tecnico, (tpr.nome) as produto, ";
        SQL = SQL + "tvi.qtde_liberada, tvi.qtde_devolvida ";
        SQL = SQL + "from t_viagem tv ";
        SQL = SQL + "inner join t_pessoa tp on tp.codigo = tv.tecnico_cod ";
        SQL = SQL + "inner join t_viagem_item tvi on tvi.codigo_viagem = tv.codigo ";
        SQL = SQL + "inner join t_produto tpr on tpr.codigo = tvi.produto_cod ";
        SQL = SQL + "where tv.exclusao = 0 ";
        SQL = SQL + "and tvi.exclusao = 0 ";
	SQL = SQL + "and tv.tecnico_cod = " + CodigoTecnico + " ";
        
        try {
            
            Conexao = new ConexaoPostgresql();
            Conexao.Conectar() ;
            Conexao.setSQL(SQL);
            
            System.out.println(SQL);

            JRResultSetDataSource jrRS = new JRResultSetDataSource(Conexao.Consultar());
            InputStream is = getClass().getResourceAsStream("/relatorios/RelViagem.jasper");

            JasperPrint print = JasperFillManager.fillReport(is, new HashMap(), jrRS);
            JasperViewer viewer = new JasperViewer(print, false);            

            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            viewer.setVisible(true);

            Conexao.Desconectar();
            
        } catch (Exception Erro) {
            JOptionPane.showMessageDialog(null, "Erro na busca: " + Erro);
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtfCodigo = new javax.swing.JFormattedTextField();
        jtfTecnico = new javax.swing.JFormattedTextField();
        jbtBusca = new javax.swing.JButton();
        jbtListaViagem = new javax.swing.JButton();

        setClosable(true);
        setTitle("Relatório de Viagem");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Técnico", "Destino"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        jLabel1.setText("Técnico (F12)");

        jtfCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCodigoFocusLost(evt);
            }
        });
        jtfCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfCodigoKeyPressed(evt);
            }
        });

        jtfTecnico.setEnabled(false);

        jbtBusca.setText("Busca");
        jbtBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBuscaActionPerformed(evt);
            }
        });

        jbtListaViagem.setText("Listar!");
        jbtListaViagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtListaViagemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtfCodigo))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtfTecnico)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtListaViagem, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtListaViagem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtBusca)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCodigoKeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){

            jtfTecnico.setText(NomeFornecedor(Integer.parseInt(jtfCodigo.getText())));

        }

        if(evt.getKeyCode() == KeyEvent.VK_F12){

            SubPesquisaPessoa A = new SubPesquisaPessoa();
            A.setVisible(true);
            JDesktopPane JDP = this.getDesktopPane();
            JDP.add(A);
            A.centralizar(JDP);
            try{
                A.setSelected(true);
                A.SetSubRelatorioViagem(this);
                A.Validador("RelatorioViagem");
            }catch (Exception E){
                JOptionPane.showMessageDialog(null, "Erro: " + E);
            }
            A.setVisible(true);

        }
        
    }//GEN-LAST:event_jtfCodigoKeyPressed

    private void jtfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCodigoFocusLost

        if (!(jtfCodigo.getText().trim().equals(""))) {

            jtfTecnico.setText(NomeFornecedor(Integer.parseInt(jtfCodigo.getText())));
            
        }
        
    }//GEN-LAST:event_jtfCodigoFocusLost

    private void jbtBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBuscaActionPerformed
        
//        if (CapturarLinha != 0){
            BuscaViagem();
//        } else {
//            JOptionPane.showMessageDialog(null, "Nenhum dado da tabela selecionado!");
//        }
        
    }//GEN-LAST:event_jbtBuscaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        CapturarLinha = jTable1.getSelectedRow();
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jbtListaViagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtListaViagemActionPerformed

        if (!(jtfCodigo.getText().equals(""))) {
            Tabela();
        } else { 
            JOptionPane.showMessageDialog(null, "Precisa selecionar um técnico!");
        }
        
    }//GEN-LAST:event_jbtListaViagemActionPerformed

    public String NomeFornecedor (int CodigoFabricante){
    
        String Fabricante = null;
        
        Fun = new Funcoes();
        Fabricante = Fun.NomePessoa(CodigoFabricante);
        
        return Fabricante;        
    
    }
    
    public void NomeFornecedorViaF12 (int CodigoFabricante){
        
        jtfCodigo.setText(String.valueOf(CodigoFabricante));
        jtfTecnico.setText(NomeFornecedor(CodigoFabricante));
        
    }
    
    public void Tabela(){
        
        String SQL;
        
        SQL = "select tv.codigo, (tp.nome) as tecnico, tv.destino ";
        SQL = SQL + "from t_viagem tv ";
        SQL = SQL + "inner join t_pessoa tp on tp.codigo = tv.tecnico_cod ";
        SQL = SQL + "where tv.tecnico_cod = " + jtfCodigo.getText() + " ";
        
        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);

        ResultSet RS = Conexao.Consultar();
        
        try{
            if (RS.next()){
                DefaultTableModel Tabela = (DefaultTableModel) jTable1.getModel();
                Object[] CarLinha = {RS.getString("codigo"),RS.getString("tecnico"),RS.getString("destino")};
                Tabela.addRow(CarLinha);
            }
        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação: " + Erro);
        }
        
        Conexao.Desconectar();
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtBusca;
    private javax.swing.JButton jbtListaViagem;
    private javax.swing.JFormattedTextField jtfCodigo;
    private javax.swing.JFormattedTextField jtfTecnico;
    // End of variables declaration//GEN-END:variables
}
