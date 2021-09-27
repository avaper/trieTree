package es.uned.lsi.eped.pract2019_2020;

import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.ListIF;

public class WordListN {
	/* Atributos de la clase con la estructura adecuada */
	private ListIF<String> words;
	private int size;
	/* Atributos de la clase con la estructura adecuada */

	public WordListN(int size) 
	{
		this.words = new List <String>();
		this.size = size;
	}
	
	public void add(String word) 
	{
		if( word.length() == size )
		{
			this.words.insert(words.size() + 1, word);			
		}
	}
	
	public int getWordSize() {
		return this.size;
	}
	
	public String toString() {
		StringBuilder salida = new StringBuilder();
		int numPalabras = this.words.size(); /* Longitud de la secuencia de palabras */
		salida.append("-Palabras de ");
		salida.append(this.getWordSize());
		salida.append(" letra");
		if ( this.getWordSize() > 1 ) { salida.append('s'); }
		salida.append(": ");
		for (int pos = 1 ; pos <= numPalabras ; pos++) {
			/* Estas lineas dependen de la estructura escogida */
			String word = this.words.get(pos); /* Obtener la siguiente palabra */
			/* Avanzar a la siguiente sin destruir la estructura */
				
			// TODO No hace falta debido a la estructura elegida (Lista)
			//...
					
			/* Estas lineas dependen de la estructura escogida */
			salida.append(word);
			if ( pos < numPalabras ) {
				salida.append(", ");
			}
		}
		salida.append('\n');
		return salida.toString();
	}
}
