package es.uned.lsi.eped.pract2019_2020;

public class LetterNode extends Node {

	private char caracter;
	
	public LetterNode() {
		super();
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.LETTERNODE;
	}

	/**
	 * @return the caracter
	 */
	public char getCaracter() {
		return caracter;
	}

	/**
	 * @param caracter the caracter to set
	 */
	public void setCaracter(char caracter) {
		this.caracter = caracter;
	}

}