package es.uned.lsi.eped.pract2019_2020;

import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.ListIF;

public class WordList {
	private ListIF<WordListN> wordList;
	
	public WordList() {
		this.wordList = new List<WordListN>();
	}
	
	public void add(String word) 
	{
		boolean contains = false;
		int pos = 1;
		
		for (int i = 1; i <= this.wordList.size(); i++) 
		{
			if(this.wordList.get(i).getWordSize() == word.length()) 
			{
				contains = true;
				pos = i;
			}
		}

		if( contains == true )
		{
			this.wordList.get(pos).add(word);
		}
		else
		{			
			boolean mayor = true;
			int menor = 1;
						
			for (int i = 1; i <= this.wordList.size(); i++) 
			{
				WordListN wl = this.wordList.get(i);
				
				if(wl.getWordSize() > word.length())
				{
					mayor = false;
					menor = i;
				}
			}
			
			WordListN wList = new WordListN(word.length());	
			wList.add(word);

			if(mayor)
			{				
				this.wordList.insert(1, wList);
			}
			else
			{				
				this.wordList.insert(menor + 1, wList);
			}
		}
	}
	
	public String toString() {
		StringBuilder salida = new StringBuilder();
		for ( int pos = 1 ; pos <= this.wordList.size() ; pos++ ) {
			salida.append(this.wordList.get(pos).toString());
		}
		return salida.toString();
	}
}
