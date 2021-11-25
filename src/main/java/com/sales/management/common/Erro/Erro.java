package com.sales.management.common.Erro;

public class Erro {

    private String msgUsuario;
    private String msgDeselvolvedor;
    
    public Erro(String msgUsuario, String msgDeselvolvedor) {
        this.msgUsuario = msgUsuario;
        this.msgDeselvolvedor = msgDeselvolvedor;
    }

    public String getMsgUsuario() {
        return msgUsuario;
    }

    public void setMsgUsuario(String msgUsuario) {
        this.msgUsuario = msgUsuario;
    }

    public String getMsgDeselvolvedor() {
        return msgDeselvolvedor;
    }

    public void setMsgDeselvolvedor(String msgDeselvolvedor) {
        this.msgDeselvolvedor = msgDeselvolvedor;
    }
    
}
