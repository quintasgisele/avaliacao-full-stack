package br.com.wayon.service;

import java.util.Collection;

import br.com.wayon.exception.AgendamentoException;
import br.com.wayon.modelo.Agendamento;

public interface AgendamentoService {

	public Double calcularTaxaAgendamento(Agendamento agendamento);

	public void validarTaxaAgendamento(Agendamento agendamento)
			throws AgendamentoException;

	public void salvarAgendamento(Agendamento agendamentol)
			throws AgendamentoException;

	public Collection<Agendamento> listarAgendamentos();
	
}
