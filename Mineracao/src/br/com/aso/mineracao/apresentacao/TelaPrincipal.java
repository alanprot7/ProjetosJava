package br.com.aso.mineracao.apresentacao;

import br.com.aso.mineracao.entidade.*;
import br.com.aso.mineracao.negocio.*;
import java.util.Random;

public class TelaPrincipal {

	private static Service service;
	private static final int COLETA = 10;

	public static void main(String[] args) {

		Random rd = new Random();

		//Classe de Servicos
		service = new Service();

		//Objetos principais
		Tanque tanque = new Tanque();
		Metal metal1 = new Metal();
		Metal metal2 = new Metal();
		Metal metal3 = new Metal();
		Metal metal4 = new Metal();
		Metal metal5 = new Metal();
		Solo solo = new Solo();
		Broca broca = new Broca();

		//Laço Principal		
		for(int i=1;i<=3;i++) {
			if(i==1){
				service.limpaTanque(tanque);
				service.constroiBroca(broca, "Aco", 600, 5, 0);
				tanque.setBroca(broca);
			}
			else
				if(i==2){
					service.limpaTanque(tanque);
					service.constroiBroca(broca, "Diamante", 700, 20, 0);
					tanque.setBroca(broca);
				}
				else{
					service.limpaTanque(tanque);
					service.constroiBroca(broca, "Adamantium", 2147483647, 50, 0);
					tanque.setBroca(broca);
				}

			for(int cont=0, j=0; ;j+=COLETA){
				if(j>tanque.getBroca().getLimiteProfundidade()||j>solo.PROFUNDIDADE) {
					service.publicaTotal(tanque,
							service.calculaValorTotal(tanque),
							service.calculaQtd(tanque),
							service.calculaTempo(tanque.getBroca().getVelocidade() , (j-COLETA)));
					break;
				}
				if(j==0){
					service.constroiMetal(metal1, "Prata", 10, 2);
					service.constroiSolo(solo, 0,100 ,metal1);
				}
				if(j==110){
					service.constroiMetal(metal2, "Ouro", 120, 3);
					service.constroiSolo(solo, 100,250 ,metal2);
				}
				if(j==260){
					service.constroiMetal(metal3, "Platina", 370, 7);
					service.constroiSolo(solo, 250,450 ,metal3);
				}
				if(j==460){
					service.constroiMetal(metal4, "Diamante", 500, 11);
					service.constroiSolo(solo, 450,850 ,metal4);
				}
				if(j==860){
					service.constroiMetal(metal5, "Unobitanium", 2700, 81);
					service.constroiSolo(solo, 850,1600 ,metal5);
				}
				if(j>0)
					cont = service.coletaMetal(tanque, solo, cont, rd.nextInt());

			}

		}

	}

}
