package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	
	
	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void deveCriarUmaSessaoQuandoDataInicioEDataFimForemIguaisEPeriodicidadeForDiaria() {
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> criaSessoes = espetaculo.criaSessoes(new LocalDate(), new LocalDate(), new LocalTime(), Periodicidade.DIARIA);
		
		Assert.assertEquals(1, criaSessoes.size());
	}
	
	@Test
	public void deveCriarUmaSessaoQuandoDataInicioEDataFimForemIguaisEPeriodicidadeForSemanal() {
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> criaSessoes = espetaculo.criaSessoes(new LocalDate(), new LocalDate(), new LocalTime(), Periodicidade.SEMANAL);
		
		Assert.assertEquals(1, criaSessoes.size());
	}
	
	@Test
	public void deveCriarOnzeSessaoQuandoDataInicioEDataFimForemDiferentesEPeriodicidadeForDiaria() {
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> criaSessoes = espetaculo.criaSessoes(new LocalDate(), new LocalDate().plusDays(10), new LocalTime(), Periodicidade.DIARIA);
		
		Assert.assertEquals(11, criaSessoes.size());
	}
	
	@Test
	public void deveCriarDuasSessaoQuandoDataInicioEDataFimForemDiferentesEPeriodicidadeForSemanal() {
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> criaSessoes = espetaculo.criaSessoes(new LocalDate(), new LocalDate().plusDays(10), new LocalTime(), Periodicidade.SEMANAL);
		
		Assert.assertEquals(2, criaSessoes.size());
	}
	
	@Test
	public void naoDeveCriarSessaoQuandoDataInicioForMaiorQueDataFim() {
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> criaSessoes = espetaculo.criaSessoes(new LocalDate().plusDays(1), new LocalDate(), new LocalTime(), Periodicidade.SEMANAL);
		
		Assert.assertEquals(0, criaSessoes.size());
	}
	
	
	
	
	
}
