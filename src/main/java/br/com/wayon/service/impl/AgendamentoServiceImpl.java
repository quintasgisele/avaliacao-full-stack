package br.com.wayon.service.impl;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import br.com.wayon.enums.TipoAgendamento;
import br.com.wayon.exception.AgendamentoException;
import br.com.wayon.modelo.Agendamento;
import br.com.wayon.repository.core.AgendamentoRepository;
import br.com.wayon.service.AgendamentoService;

public class AgendamentoServiceImpl implements AgendamentoService{

	@SuppressWarnings("unused")
	private AgendamentoRepository agendamentoRepository;

	@Override
	public Double calcularTaxaAgendamento(Agendamento agendamento) {
Double taxa = null;
		
		if (TipoAgendamento.A.equals(agendamento.getTipoAgendamento())) {
			taxa = calcularTaxacaoTipoA(agendamento);
		} else if (TipoAgendamento.B.equals(agendamento.getTipoAgendamento())) {
			taxa = calcularTaxacaoTipoB(agendamento);
		} else if (TipoAgendamento.C.equals(agendamento.getTipoAgendamento())) {
			taxa = calcularTaxacaoTipoC(agendamento);
		} else if (TipoAgendamento.D.equals(agendamento.getTipoAgendamento())) {
			taxa = calcularTaxacaoTipoD(agendamento);
		}
		
		return taxa;
	}

	@Override
	public void validarTaxaAgendamento(Agendamento agendamento) throws AgendamentoException {
		Double taxaReal = this.calcularTaxaAgendamento(agendamento);
		
		if (taxaReal.doubleValue() != agendamento.getTaxa()) {
			throw new AgendamentoException("Valor da taxa esta incorreto.");
		}
	}

	@Override
	public void salvarAgendamento(Agendamento agendamento) throws AgendamentoException {
		this.validarTaxaAgendamento(agendamento);
		this.agendamentoRepository.salvarNoRepositorio(agendamento);
	}

	@Override
	public Collection<Agendamento> listarAgendamentos() {
		return this.agendamentoRepository.obterListaDoRepositorio(Agendamento.class);
	}

	private Double calcularDiferencaDeDias(Date dataMenor, Date dataMaior) {
		return new Double((dataMaior.getTime() - dataMenor.getTime()) / 86400000L);
	}
	
	private Double calcularTaxacaoTipoA(Agendamento agendamento) {
		return (agendamento.getValorTransferencia() * 0.03) ;
	}

	private Double calcularTaxacaoTipoB(Agendamento agendamento) {
		Double diferencaDeDias = this.calcularDiferencaDeDias(Calendar.getInstance().getTime(), agendamento.getDataAgendamento());
		Double taxa = null;
		
		if (diferencaDeDias <= 10) {
			taxa = (12 + agendamento.getValorTransferencia());
		} 
		return taxa;
	}
	
	private Double calcularTaxacaoTipoC(Agendamento agendamento) {
		Double diferencaDeDias = this.calcularDiferencaDeDias(Calendar.getInstance().getTime(), agendamento.getDataAgendamento());
		Double taxa = null;
		
		if (diferencaDeDias > 40) {
			
			taxa = (agendamento.getValorTransferencia() * 0.017);
			
		} else if ((diferencaDeDias > 30) && (diferencaDeDias <= 39)) {
			
			taxa = (agendamento.getValorTransferencia() * 0.047);
			
		} else if ((diferencaDeDias > 20) && (diferencaDeDias <= 29)) {
			
			taxa = (agendamento.getValorTransferencia() * 0.069);
			
		} else if ((diferencaDeDias > 10) && (diferencaDeDias <= 19)) {
			
			taxa = (agendamento.getValorTransferencia() * 0.082);
			
		}
		
		return taxa;
	}
	
	private Double calcularTaxacaoTipoD(Agendamento agendamento) {
		Double taxa = null;
		Double valorTransferencia = agendamento.getValorTransferencia();
		
		if (valorTransferencia.doubleValue() <= 1000.00) {
			
			taxa = this.calcularTaxacaoTipoA(agendamento);
			
		} else if ((valorTransferencia.doubleValue() > 1000.00) && (valorTransferencia.doubleValue() <= 2000.00)) {
			
			taxa = this.calcularTaxacaoTipoB(agendamento);
			
		} else if (valorTransferencia.doubleValue() > 2000.00) {
			
			taxa = this.calcularTaxacaoTipoC(agendamento);
			
		}
		
		return taxa;
	}

}
