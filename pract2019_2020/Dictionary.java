package es.uned.lsi.eped.pract2019_2020;

import es.uned.lsi.eped.DataStructures.GTree;
import es.uned.lsi.eped.DataStructures.GTreeIF;
import es.uned.lsi.eped.pract2019_2020.Node.NodeType;

public class Dictionary {

	private GTree<Node> dict; /* El diccionario es un árbol general de nodos */
	
	/* Constructor de la clase */
	public Dictionary() {
		
		// TODO
		
		this.dict = new GTree<Node>();
				
		// Se crea un nodo raiz
		this.dict.setRoot(new RootNode());

	}
	
	/* Método de inserción de una nueva palabra en el diccionario */
	public void insert(String word) {
		/* Insertamos la palabra a partir del nodo raíz del árbol */
		
		insertInTree(word,this.dict);
	}
	
	/* Método privado llamado por el anterior */
	private void insertInTree(String word, GTreeIF<Node> node) {
		
		// TODO

		// Se crea un nuevo arbol
		GTree<Node> newNode = new GTree<Node>();
		
		// Se crea un nodo intermedio
		LetterNode intermedio = new LetterNode();
	
		// Quedan letras?
		if ( ! word.isEmpty() ) 
		{
			//System.out.println("Procesando letra " + word.charAt(0));
			
			// Se guarda la primera letra
			char letter = word.charAt(0);
			
			// Se guardan el resto de letras
			String restoWord = word.substring(1);

			// Se fija la letra del nodo intermedio
			intermedio.setCaracter(letter);

			// No tiene hijos
			if (node.getChildren().isEmpty())
			{
				newNode.setRoot(intermedio);
				node.addChild(1, newNode);
				
				insertInTree(restoWord, node.getChild(1));
			}
			// Tiene hijos
			else
			{	
				boolean existeLetra = false;
				int posLetter = 1;
				
				for (int i = 1; i <= node.getNumChildren(); i++) 
				{
					Node child = node.getChild(i).getRoot();
					
					if(child.getNodeType().equals(NodeType.LETTERNODE))
					{
						LetterNode letterNode = (LetterNode) child;
						
						if(letter == letterNode.getCaracter())
						{
							existeLetra = true;
							posLetter = i;
						}						
					}
				}		
				
				// La letra NO existe
				if( existeLetra == false )
				{					
					newNode.setRoot(intermedio);
					
					// TODO Orden!!!
					
					int menor = 1;
					boolean mayor = true;
					char letraNodo = 0;
						
					for (int j = node.getNumChildren(); j > 0; j--) 
					{
						Node child = node.getChild(j).getRoot();
						
						if(child.getNodeType().equals(NodeType.LETTERNODE))
						{
							LetterNode letterNode = (LetterNode) child;
							letraNodo = letterNode.getCaracter();
							
							//System.out.println("\tPOS " + j + ", char " + letraNodo);

							// Letra de secuencia menor que la del nodo
							if(letter < letraNodo)
							{
								//System.out.println("\t\t" + letter + " va antes de " + letraNodo);
								mayor = false;
								menor = j;
							}
						}
					}
					
					if(mayor)
					{
						//System.out.println("\t\tEs el elemento mayor");
						
						node.addChild(node.getNumChildren()+1, newNode);
						
						this.insertInTree(restoWord, node.getChild(node.getNumChildren()));
					}
					else
					{
						// TODO ORDEN
						/*
						System.out.println("\t\tColocar antes de elemento en pos " + menor + 
								" con letra " + letraNodo);
						*/						

						node.addChild(menor, newNode);
						
						this.insertInTree(restoWord, node.getChild(menor));
					}

				}
				// La letra SI existe
				else
				{					
					this.insertInTree(restoWord, node.getChild(posLetter));
				}
			}
			
		}
		// No quedan letras
		else
		{
			/*
			System.out.println();
			
			for (int i = 1; i <= this.dict.getNumChildren(); i++) 
			{
				System.out.println("-------------------------------\n");
				
				Node dic = this.dict.getChild(i).getRoot();
				
				if(dic.getNodeType().equals(NodeType.LETTERNODE))
				{
					LetterNode ln = (LetterNode) dic;
					
					System.out.println("POS " + i + " char " + ln.getCaracter());
				}
				
				System.out.println("-------------------------------\n");
			}
			*/
			
			WordNode hoja = new WordNode();
			newNode.setRoot(hoja);
			node.addChild(1, newNode);
		}
	}

	/* Método público de búsqueda de todas las palabras a partir de una secuencia */
	public WordList search(String sequence) {
		WordList salida = new WordList();           /* Variable donde construiremos la salida */
		searchInTree(sequence,"",this.dict,salida); /* Construimos la salida recursivamente */
		return salida;
	}
	
	/* Método privado llamado por el anterior */
	private void searchInTree(String sequence, String word,
							  GTreeIF<Node> node, WordList salida) {
		// TODO
		
		// Para cada hijo del nodo actual
		for (int i = 1; i <= node.getNumChildren(); i++)
		{	
			Node child = node.getChild(i).getRoot();
			
			if(child.getNodeType().equals(NodeType.LETTERNODE))
			{
				LetterNode letterNode = (LetterNode) child;
				char letter = letterNode.getCaracter();

				if(sequence.contains(String.valueOf(letter)))
				{					
					int pos = sequence.indexOf(String.valueOf(letter));
					
					String left = sequence.substring(0, pos);
					String right = sequence.substring(pos+1, sequence.length());
					
					String subSequence = left + right;
										
					this.searchInTree(subSequence, word + letter, node.getChild(i), salida);					
				}
			}
			else
			{				
				salida.add(word);
			}
		}
	}
	
	/* Método público de búsqueda de todas las palabras de tamaño size a partir de una secuencia */
	public WordListN search(String sequence, int size) {
		WordListN salida = new WordListN(size);           /* Variable donde construiremos la salida */
		searchInTreeN(sequence,"",this.dict,salida,size); /* Construimos la salida recursivamente */
		return salida;
	}
	
	/* Método privado llamado por el anterior */
	private void searchInTreeN(String sequence, String word,
							   GTreeIF<Node> node, WordListN salida,
							   int size) {
		// TODO	
		
		// Para cada hijo del nodo actual
		for (int i = 1; i <= node.getNumChildren(); i++)
		{	
			Node child = node.getChild(i).getRoot();
			
			if(child.getNodeType().equals(NodeType.LETTERNODE))
			{
				LetterNode letterNode = (LetterNode) child;
				char letter = letterNode.getCaracter();
				
				if(sequence.contains(String.valueOf(letter)))
				{					
					int pos = sequence.indexOf(String.valueOf(letter));
					
					String left = sequence.substring(0, pos);
					String right = sequence.substring(pos+1, sequence.length());
					
					String subSequence = left + right;
										
					this.searchInTreeN(subSequence, word + letter, node.getChild(i), salida, size);					
				}
			}
			else
			{
				if(word.length() == size)
				{					
					salida.add(word);
				}
			}
		}
	}	
}
