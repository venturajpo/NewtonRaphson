/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 */


 /*
  * Classe NewtonRaphson: Calcula juros aproximados baseado no metodo de aproximação de Newton-Raphson
  * depositos[]:array de double que armazena os valores de deposito. O último valor deve ser o saldo.
  * datas[]:array de int que armazena o número do mês em que foi feito cada um dos depósitos. Todos os valores dever ser no mesmo ano, ou adicionar 12 para cada ano seguinte
  * f_funcao	:calcula somatória da fórmula de Newton-Raphson - saldo a partir dos valores de datas e depósitos e retorna como double
  * f_derivada	:calcula derivada a partir de f_funcao e retorna como double
  * newton		:calcula o os juros e retorna como double
  */ 
class NewtonRaphson{
	static double[] depositos;
	static int[] datas;
	static double f_funcao(double j){
		double soma = 0;
		for(int i = 0;i < datas.length-1;i++){
			soma += depositos[i] * Math.pow(1 + j,datas[datas.length-1] - datas[i]);
		}
		return soma - depositos[depositos.length-1];
	}
	static double f_derivada(double j){
		double soma = 0;
		for(int i = 0;i < datas.length-1;i++){
			soma +=  (datas[datas.length-1]-datas[i]) * depositos[i] * Math.pow(1 + j,((datas[datas.length-1] - datas[i])-1));
		}
		return soma;
	}
	static double newton(double epsilon){
		if(epsilon >= 1 || epsilon <= 0){
			return -1;
		}else{
			double jota = 0.5;
			double jota_anterior;
			do{
				jota_anterior = jota;
				jota = jota_anterior - (f_funcao(jota_anterior)/f_derivada(jota_anterior));
			}while(Math.sqrt(Math.pow(jota-jota_anterior,2)) >= epsilon);
			return jota;
		}
	}
}
