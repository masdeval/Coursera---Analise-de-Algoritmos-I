/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursera.sort;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

/**
 *
 * @author masdeval
 */
public class RSelect<T extends Comparable>{

	private T medium;

	public T find(Collection<T> array, int posicao)
	{
		Comparable[] aux = array.toArray(new Comparable[0]);
		rselect(aux, 0, aux.length - 1, posicao);

		return medium;
	}

	private Comparable[] rselect(Comparable[] array, int i, int j, int posicao)
	{
		int indiceFinal = j;
		int arrayLength = j - i + 1; //nao eh array.length porque em cada execucao passo uma parte do array
		int indiceInicial = i;

		if (j < i )
		{
			return array;
		}
		else if(i == j) //eh o caso onde ha apenas um elemento para ser analisado
		{
			if(posicao == i)
			{
				medium = (T) array[i];
			}

			return array;
		}
		else
		{

			Random x = new Random();
			int pivot = x.nextInt(j - i) + i; //pivot aleatorio => nextInt irá retornar um inteiro entre 0 e o tamanho do array.
			//Ocorre que em algumas chamadas o indice inical do array não é 0, por isso o + i
			//faz o pivot ser o primeiro elemento
			Comparable saved = array[i];
			array[i] = array[pivot];
			array[pivot] = saved;

			//particiona in-place em torno do pivot
			while (i < j)
			{
				if (array[i].compareTo(array[i + 1]) > 0)
				{
					Comparable aux = array[i];
					array[i] = array[i + 1];
					array[i + 1] = aux;
					i++;
				} else if (array[i].compareTo(array[i + 1]) < 0)
				{
					Comparable aux = array[i + 1];
					array[i + 1] = array[j];
					array[j] = aux;
					j--;
				} else
				{
					i++;
				}
			}
			//quando chega aqui i == j e o pivot esta na sua posicao correta

			//o i é o indice onde parou o pivot
			//o intervalo do array esquerdo é de 0 a i-1, ou seja, tem o tamanho i


			//se a posicao procurada é a mesma do pivot, retorna o elemento do pivot
			if(posicao == i)
			{
				medium = (T) array[i];
			}
			else if(i > posicao)//continua procurando no lado esquerdo
			{
				rselect(array, indiceInicial, i - 1, posicao);
			}
			else
			{
				rselect(array, j + 1, indiceFinal, posicao); //procura no array direito
			}

			return array;
		}

	}



	/*
	 *
	 *
	 *public T findMedium(Collection<T> array)
	{
		Comparable[] aux = array.toArray(new Comparable[0]);
		tamanhoArrayOriginal = aux.length;
		Arrays.asList(sort(aux, 0, aux.length - 1, 0, 0));

		return medium;
	}

	private Comparable[] sort(Comparable[] array, int i, int j, int acumuladoEsquerdo, int acumuladoDireito)
	{
		int indiceFinal = j;
		int arrayLength = j - i + 1; //nao eh array.length porque em cada execucao passo uma parte do array
		int indiceInicial = i;

		if (i == j || j < i || i > j)
		{
			return array;
		} else
		{
			//particiona in-place em torno do pivot
			while (i < j)
			{
				if (array[i].compareTo(array[i + 1]) > 0)
				{
					Comparable aux = array[i];
					array[i] = array[i + 1];
					array[i + 1] = aux;
					i++;
				} else if (array[i].compareTo(array[i + 1]) < 0)
				{
					Comparable aux = array[i + 1];
					array[i + 1] = array[j];
					array[j] = aux;
					j--;
				} else
				{
					i++;
				}
			}
			//quando chega aqui, ou i == j

			//o i é o indice onde parou o pivot
			//o intervalo do array esquerdo é de 0 a i-1, ou seja, tem o tamanho i

			int tamanhoArrayEsquerdo = i - indiceInicial;
			int tamanhoArrayDireito = indiceFinal - i;

			//Se a diferenca do que ja foi particionado entre o lado esquerdo e direito for 1 ou se
			// forem iguais, o atual pivot eh o elemento mediano
			if(  (acumuladoEsquerdo + tamanhoArrayEsquerdo) == (acumuladoDireito + tamanhoArrayDireito) ||
				 (acumuladoEsquerdo + tamanhoArrayEsquerdo + 1) ==  (acumuladoDireito + tamanhoArrayDireito)
				)
			{
				medium = (T) array[i];
			}
			//Se tudo que ja acumulou no lado esquerdo + o que falta considerar + o pivot
			//for maior que o equivalente do lado direito, entao o pivot esta no lado esquerdo
			else if(acumuladoEsquerdo + tamanhoArrayEsquerdo > acumuladoDireito + tamanhoArrayDireito )
			{

				//Tenho que ir para o lado esquerdo mas so tem um elemento no array
				if (indiceInicial == (i - 1)) //nao tem mais para onde ir, esse tem que ser a mediana
				{
					medium = (T) array[indiceInicial];
				} else
				{
					//vai para o lado esquerdo
					acumuladoDireito += tamanhoArrayDireito + 1;
					sort(array, indiceInicial, i - 1, acumuladoEsquerdo, acumuladoDireito);
				}
			}
			else if (acumuladoEsquerdo + tamanhoArrayEsquerdo  < acumuladoDireito + tamanhoArrayDireito )
			{
				//Tenho que ir para o lado direito mas so tem um elemento no array
				if ((j + 1) == indiceFinal)//nao tem mais para onde ir, esse tem que ser a mediana
				{
					medium = (T) array[indiceFinal];
				} else
				{
					acumuladoEsquerdo += tamanhoArrayEsquerdo + 1;
					sort(array, j + 1, indiceFinal, acumuladoEsquerdo, acumuladoDireito);
				}
			}
			return array;
		}

	}
	 */












}
