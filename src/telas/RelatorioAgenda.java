/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telas;

import classes.Combo;
import classes.ConexaoPostgresql;
import java.awt.Dimension;
import java.io.InputStream;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jeferson
 */
public class RelatorioAgenda extends javax.swing.JInternalFrame {
    
    private ConexaoPostgresql Conexao;

    /**
     * Creates new form RelatorioAgenda
     */
    public RelatorioAgenda() {
        initComponents();
        JogaOpcaoRelatorio();
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RelatorioAgenda().setVisible(true);
            }
        });
    }

    public void centralizar(JDesktopPane dp) {
        Dimension ds = dp.getSize();
        Dimension dw = this.getSize();
        this.setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
    }
    
    public void BuscaAgenda(){
        
        try{
            
            String SQL;
            String Opcao = String.valueOf(((Combo)jcbOpcoes.getSelectedItem()).getKey());
            
            /*
        
            SQL = "select tag.codigo, tag.nome, tag.apelidofantasia, tag.rgie, tag.cpfcnpj, tag.endereco, tag.numero, tag.bairro, ";
            SQL = SQL + "(tci.nome) as cidade, tag.cep, tag.fone1, tag.fone2, tag.cel1, tag.cel2, to_char(tag.dtnasc,'DD/MM/YYYY') as data, ";
            SQL = SQL + "tag.email, tag.obs ";
            SQL = SQL + "from t_pessoa tag ";
            SQL = SQL + "inner join t_cidade tci on tci.codigo = tag.cidade_cod ";
            SQL = SQL + "where tag.exclusao = 0 ";
            
            */            
            
            SQL = "select tag.codigo, tag.nome, tag.apelidofantasia, (tpt.nome) as tipo_pessoa, tag.rgie, tag.cpfcnpj, tag.endereco, ";
            SQL = SQL + "tag.numero, tag.bairro, (tci.nome) as cidade, tag.cep, tag.fone1, tag.fone2, tag.cel1, tag.cel2, ";
            SQL = SQL + "to_char(tag.dtnasc,'DD/MM/YYYY') as data, tag.email, tag.obs, ";
            SQL = SQL + "(CASE tag.chkcliente WHEN 1 THEN 'SIM' ELSE 'NÃO' END) as cliente, ";
            SQL = SQL + "(CASE tag.chkfornecedor WHEN 1 THEN 'SIM' ELSE 'NÃO' END) as fornecedor, ";
            SQL = SQL + "(CASE tag.chkfuncionario WHEN 1 THEN 'SIM' ELSE 'NÃO' END) as funcionario, ";
            SQL = SQL + "(CASE tag.bloqueio WHEN 1 THEN 'ESTE CADASTRO ENCONTRA-SE BLOQUEADO NO CADASTRO' ELSE '' END) as block ";
            SQL = SQL + "from t_pessoa tag ";
            SQL = SQL + "inner join t_cidade tci on tci.codigo = tag.cidade_cod ";
            SQL = SQL + "inner join t_pessoa_tipo tpt on tpt.codigo = tag.tipo_pessoa ";
            SQL = SQL + "where tag.exclusao = 0 ";
            
            switch (Integer.parseInt(Opcao)){
                case 1:
                    SQL = SQL + "and tag.codigo = " + jtfPesquisa.getText() + " ";
                    break;
                case 2:
                    SQL = SQL + "and UPPER(tag.nome) like '%" + jtfPesquisa.getText().toUpperCase() + "%' ";
                    break;
                case 3:
                    SQL = SQL + "and UPPER(tag.apelidofantasia) like '%" + jtfPesquisa.getText().toUpperCase() + "%' ";
                    break;
                case 4:
                    SQL = SQL + "and tag.fone1 like '%" + jtfPesquisa.getText() + "%' ";
                    break;
                case 5:
                    SQL = SQL + "and tag.cel1 like '%" + jtfPesquisa.getText() + "%' ";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!");
            }


            Conexao = new ConexaoPostgresql();
            Conexao.Conectar() ;
            Conexao.setSQL(SQL);
            
            System.out.println(SQL);

            JRResultSetDataSource jrRS = new JRResultSetDataSource(Conexao.Consultar());
            InputStream is = getClass().getResourceAsStream("/relatorios/RelAgenda.jasper");

            JasperPrint print = JasperFillManager.fillReport(is, new HashMap(), jrRS);
            JasperViewer viewer = new JasperViewer(print, false);            

            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            viewer.setVisible(true);

            Conexao.Desconectar();
            
        } catch (Exception Erro) {
            JOptionPane.showMessageDialog(null, "Erro na busca: " + Erro);
        }
        
    }

    private void JogaOpcaoRelatorio(){
        
        jcbOpcoes.removeAllItems();
        Combo T1 = new Combo(1, "Codigo");
        Combo T2 = new Combo(2, "Nome");
        Combo T3 = new Combo(3, "Apelido");
        Combo T4 = new Combo(4, "Telefone");
        Combo T5 = new Combo(5, "Celular");
        DefaultComboBoxModel Modelo = new DefaultComboBoxModel();
        Modelo.addElement(T1);
        Modelo.addElement(T2);
        Modelo.addElement(T3);
        Modelo.addElement(T4);
        Modelo.addElement(T5);
        jcbOpcoes.setModel(Modelo);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtfPesquisa = new javax.swing.JFormattedTextField();
        jcbOpcoes = new javax.swing.JComboBox();
        jlbOpcao = new javax.swing.JLabel();
        jlbPesquisa = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setClosable(true);
        setTitle("Relatório da Agenda");

        jcbOpcoes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlbOpcao.setText("Selecione a opção para a pesquisa");

        jlbPesquisa.setText("Digite o parâmetro para a pesquisa");

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addComponent(jcbOpcoes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbOpcao)
                            .addComponent(jlbPesquisa))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbOpcao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbPesquisa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed

        BuscaAgenda();
        
    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox jcbOpcoes;
    private javax.swing.JLabel jlbOpcao;
    private javax.swing.JLabel jlbPesquisa;
    private javax.swing.JFormattedTextField jtfPesquisa;
    // End of variables declaration//GEN-END:variables
}
