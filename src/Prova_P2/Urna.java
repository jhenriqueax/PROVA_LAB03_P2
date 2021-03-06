package Prova_P2;

public class Urna {

	private int qntEleitores;

	private int qntCandidatos;
	
	private int faltantes;

	private int statusVotacao;

	private Eleitor[] eleitores;

	private Candidato[] candidatos;

	private int contaVotos;

	public Urna(int qntEleitores, int qntCandidatos) {
		this.qntEleitores = qntEleitores;
		this.qntCandidatos = qntCandidatos;

		this.eleitores = new Eleitor[this.qntEleitores];
		this.candidatos = new Candidato[this.qntCandidatos + 2]; // +2 para branco e nulo

		this.candidatos[this.qntCandidatos - 1] = new Candidato("nulo"); // -1 para salva na ultima posição do array
		this.candidatos[this.qntCandidatos - 2] = new Candidato("branco"); // -2 para salva na penultima posição do
																			// array

		this.statusVotacao = 1;
		this.contaVotos = 0;
	}

	public void cadastrarEleitor(String nome, String cpf) {
		for (int i = 0; i < eleitores.length; i++) {
			if (eleitores[i] != null) {

				this.eleitores[0] = new Eleitor(nome, cpf);

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
					candidatos[i].setId(i);
					break;
				}
			}

		}
	}

	private boolean todosEleitores() {

		boolean fullEleitores = true;

		for (int i = 0; i < eleitores.length; i++) {
			if (eleitores[i] == null) {
				fullEleitores = false;
				break;
			}
		}

		return fullEleitores;
	}

	private boolean todosCandidatos() {

		boolean fullCandidatos = true;

		for (int i = 0; i < eleitores.length; i++) {
			if (eleitores[i] == null) {
				fullCandidatos = false;
				break;
			}
		}

		return fullCandidatos;
	}

	public void iniciarVotacao() {
		if (statusVotacao == 1) {
			if (todosCandidatos() == true && todosEleitores() == true) {

				for (int i = 0; i < eleitores.length; i++) {
					eleitores[i].setStatusVotacao(false);
				}
				for (int j = 0; j < candidatos.length; j++) {
					candidatos[j].zeraVotos();
				}

				this.statusVotacao = 2;

			}

		}

	}

	private int pegaEleitor(String cpfEleitor) {

		int posicaoEleitor = 404;

		Eleitor pegaEleitor = new Eleitor("nome", cpfEleitor);

		for (int i = 0; i < eleitores.length; i++) {
			if (eleitores[i].equals(pegaEleitor)) {
				posicaoEleitor = i;
				break;
			}
		}

		return posicaoEleitor;

	}

	private int pegaCandidato(String nomeCandidato) {

		int posicaoCandidato = 404;

		Candidato pegaCandidato = new Candidato(nomeCandidato);

		for (int j = 0; j < candidatos.length; j++) {
			if (candidatos[j].equals(pegaCandidato)) {
				posicaoCandidato = j;
				break;
			}
		}

		return posicaoCandidato;
	}

	public String RegistrarVotos(String cpfEleitor, String nomeCandidato) {

		String retorno = "";

		int posicaoEleitor;
		int posicaoCandidato;

		if (statusVotacao == 2) {

			posicaoEleitor = pegaEleitor(cpfEleitor);
			posicaoCandidato = pegaCandidato(nomeCandidato);

			if (posicaoCandidato == 404) {
				retorno = "Candidato não cadastrado - Voto não Cadastrado";
			} else {
				if (eleitores[posicaoEleitor].getStatus() == true) {
					retorno = "Eleitor já votou anteriormente - Voto desconsiderado";
				}

				else {
					eleitores[posicaoEleitor].setStatusVotacao(true);
					candidatos[posicaoCandidato].SetVotos();
					contaVotos += 1;
					retorno = "Voto cadastrado corretamente";
				}
			}
		}
		return retorno;
	}

	public int faltantes(){

		
		for (int i = 0; i < eleitores.length; i++) {
			if(eleitores[i] != null){
				if (eleitores[i].getStatus() == false){
					faltantes += 1;
				} 
				
			}
		}
		return this.faltantes;
	}
	
 	public String toStringUrna() {

		String status = "";

		if (statusVotacao == 1) {
			status = "NÃO INICIADA";
		}
		if (statusVotacao == 2) {
			status = "EM ANDAMENTO";
		}
		if (statusVotacao == 3) {
			status = "ENCERRADA";
		}

		String retorno = "Quantidade de Eleitores Permitidos: " + qntEleitores + " - "
				+ "Quantidade de Candidatos Permitidos: " + qntCandidatos + " - " + status
				+ "Quantidade de Votos Depositados: " + contaVotos;

		return retorno;

	}

	public String toStringIniciaVotacao() {

		String candidatosRetorno = "";
		String eleitoresRetorno = "";

		for (int i = 0; i < candidatos.length; i++) {
			if (candidatos != null) {
				candidatosRetorno += candidatos[i].toStringCandidato() + "\n";
			}

		}

		for (int j = 0; j < eleitores.length; j++) {
			if (eleitores != null) {
				eleitoresRetorno += eleitores[j].toStringEleitor() + "\n";
			}

		}

		String retorno = "-----------" + "Candidatos" + "-----------" + "\n" + candidatosRetorno + "\n" + "-----------"
				+ "Eleitores" + "-----------" + eleitoresRetorno;

		return retorno;

	}

	public void encerrar() {
		this.statusVotacao = 3;

	}

}