package SO;

import java.util.concurrent.Semaphore;

public class saque extends Thread {
	private int conta;
	private double saldoConta;
	private double valorTransacao;
	private Semaphore limitacao;

	public saque(int conta, double saldoConta, double valorTransacao, Semaphore limitacao) {
		this.conta = conta;
		this.saldoConta = saldoConta;
		this.valorTransacao = valorTransacao;
		this.limitacao = limitacao;
	}

	@Override
	public void run() {
		try {
			limitacao.acquire();
			debitar();
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			limitacao.release();
		}
	}

	public void debitar() {
		System.out.format("Conta %d - Saldo Anterior: R$ %.2f Saque no valor de: R$ %.2f Novo saldo: R$ %.2f%n",
				conta, saldoConta, valorTransacao, saldoConta + valorTransacao);
		this.saldoConta += valorTransacao;
	}
}