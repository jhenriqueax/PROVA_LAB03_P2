package Prova_P2;

public class Urna {

	private int qntEleitores;

	private int qntCandidatos;

	private Boolean statusVotacao;

	private Eleitor[] eleitores;

	private Candidato[] candidatos;

	
	
	
	
	public Urna(int qntEleitores, int qntCandidatos) {
		this.qntEleitores = qntEleitores;
		this.qntCandidatos = qntCandidatos;

		this.eleitores = new Eleitor[this.qntEleitores];
		this.candidatos = new Candidato[this.qntCandidatos + 2]; // +2 para branco e nulo

		this.candidatos[this.qntCandidatos -1] = new Candidato("nulo"); // -1 para salva na ultima posição do array
		this.candidatos[this.qntCandidatos -2] = new Candidato("branco"); // -2 para salva na ultima posição do array
		
		this.statusVotacao = false;
	}

	

	
	
	public void cadastrarEleitor(String nome, String cpf) {
		for (int i = 0; i < eleitores.length; i++) {
			if (eleitores[i] != null) {

				this.eleitores[0] = new Eleitor(nome, cpf, false);

			}
		}
	}

	private boolean comparaCanditado(String nome) {

		boolean retorno = false;

		Candidato candidatoNome = new Candidato(nome);

		for (int i = 0; i < this.candidatos.length; i++) {
			if (candidatos[i] != null) {

				if (candidatos[i].equals(candidatoNome)) {
					retorno = true;
					break;
				}

			}
		}
		return retorno;
	}

	public void cadastrarCandidato(String nome) {

		if (comparaCanditado(nome) == false) {
			for (int i = 0; i < candidatos.length; i++) {
				if (candidatos[i] != null) {
					this.candidatos[i] = new Candidato(nome);
					break;
				}
			}

		}
	}

	
	
	
	
	private boolean todosEleitores(){
		
		boolean fullEleitores = true;
		
		for (int i = 0; i < eleitores.length; i++) {
			if(eleitores[i] == null){
				fullEleitores = false;
				break;
			}
		}
		
		return fullEleitores;
	}
	
	private boolean todosCandidatos(){
		
		boolean fullCandidatos = true;
		
		for (int i = 0; i < eleitores.length; i++) {
			if(eleitores[i] == null){
				fullCandidatos = false;
				break;
			}
		}
		
		return fullCandidatos;
	}
	
	public void iniciarVotacao(){
		if(statusVotacao == false) {
			if(todosCandidatos() == true && todosEleitores() == true){
				
				for (int i = 0; i < eleitores.length; i++) {
					eleitores[i].setStatusVotacao(false);
				}
				for (int j = 0; j < candidatos.length; j++) {
					candidatos[j].zeraVotos();
				}
				
				this.statusVotacao = true;
				
			}
			
		}
		
	}
	
	
	
	
	
	public void RegistrarVota() {
		
		

	}

	
	
	
	
	
	public String represenEleitor() {
		return null;

	}

	public String represenCandidato() {
		return null;

	}



	public void encerrar() {

	}

}