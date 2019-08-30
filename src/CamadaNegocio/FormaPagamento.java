package CamadaNegocio;

import CamadaLogica.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 * @author モニカ
 * @author 阿武隈
 * @author 長門
 * @author 大和
 * @author 阿賀野
 * @author 矢矧
 * @author 長良
 * @author 天野
 * @author 紅葉
 */

//insert into forma_pagamento (fpg_nome, fpg_status) values ('Dinheiro', true),('Cartão Credito', true),('Cartão Débito', true),('Cheque', true), ('Fiado', true)
public class FormaPagamento {
    private int codigo;
    private String nome;
    private boolean status;

    public FormaPagamento(int codigo, String nome, boolean status) {
        this.codigo = codigo;
        this.nome = nome;
        this.status = status;
    }

    public FormaPagamento() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    //--------------------------------------------------------------------------
    public FormaPagamento buscaForma(String texto) throws SQLException
    {
        String sql = "select fpg_codigo, fpg_nome, fpg_status from forma_pagamento where fpg_nome = '"+texto+"'";
        ResultSet rs=Banco.getCon().consultar(sql);
        if(rs.next())
        {
            return new FormaPagamento(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
        }
        return null;
    }
    
    public FormaPagamento buscaForma(int codigo) throws SQLException
    {
        String sql = "select fpg_codigo, fpg_nome, fpg_status from forma_pagamento where fpg_codigo = "+codigo+"";
        ResultSet rs=Banco.getCon().consultar(sql);
        if(rs.next())
        {
            return new FormaPagamento(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
        }
        return null;
    }
    
    public ResultSet carregar() throws SQLException
    {
        String sql = "select fpg_nome from forma_pagamento where fpg_status = true";
        return Banco.getCon().consultar(sql);
    }
    //insert into forma_pagamento (fpg_nome, fpg_status) values ('Dinheiro', true), ('Catão Credito', true), ('Cartão Debito', true), ('Cheque', true), ('Fiado', true);
}
