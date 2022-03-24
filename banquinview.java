package view;

import java.util.concurrent.Semaphore;

import SO.deposito;
import SO.saque;

public class banquinview {

	public static void main(String[] args) {
		Semaphore limitacaoDeposito = new Semaphore(1);
		Semaphore limitacaoSaque = new Semaphore(1);
			for (int i = 0; i < 20; i++) {
				int tipo = (int) (Math.random() * 2);
				int conta = (int) (Math.random() * 10000);
					double sldcnt = Math.round((Math.random() * 10000) * 100d) / 100d;
					double valorTransacao = Math.round((Math.random() * 10000) * 100d) / 100d;
					if (tipo == 0) {
						deposito deposito = new deposito(conta, sldcnt, valorTransacao, limitacaoSaque);
						deposito.start();
			} 	else if (tipo == 1) {
					saque saque = new saque(conta, sldcnt, valorTransacao, limitacaoDeposito);
					saque.start();
			}
		}

	}

}