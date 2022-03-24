package SO;

import java.util.concurrent.Semaphore;

public class deposito extends Thread {
	private int conta;
	private double sldcnt;
	private double valorTransacao;
	private Semaphore limitacao;

	public deposito(int idConta, double saldoConta, double valorTransacao, Semaphore limitacao) {
		this.conta = idConta;
		this.sldcnt = saldoConta;
		this.valorTransacao = valorTransacao;
		this.limitacao = limitacao;
	}

	@Override
	public void run() {
		try {
			limitacao.acquire();
			creditar();
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			limitacao.release();
		}
	}

	public void creditar() {
		System.out.format("Conta %d - Saldo Anterior: R$ %.2f Deposito no valor de: R$ %.2f Novo saldo: R$ %.2f%n",
				conta, sldcnt, valorTransacao, sldcnt + valorTransacao);
		this.sldcnt += valorTransacao;
	}

}