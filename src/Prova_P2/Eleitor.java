package Prova_P2;

public class Eleitor {

    private String nome;

    private String cpf;

    private boolean statusVotacao;

    public Eleitor(String nome, String cpf, boolean status) {
    	this.nome = nome;
    	this.cpf = cpf;

    }

    public String getNome(){
    	return nome;

    }

    public String getCpf() {
    	return cpf;

    }

    public boolean getStatus(){
    	return statusVotacao;
    	


    }

	public void setStatusVotacao(boolean statusVotacao) {
		this.statusVotacao = statusVotacao;
	}    
    
}