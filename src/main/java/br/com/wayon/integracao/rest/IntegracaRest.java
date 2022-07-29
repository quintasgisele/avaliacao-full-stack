package br.com.wayon.integracao.rest;

import java.net.http.HttpResponse;
import java.util.Collection;

import br.com.wayon.exception.AgendamentoException;
import br.com.wayon.modelo.Agendamento;
import br.com.wayon.service.AgendamentoService;

public class IntegracaRest<HttpServletResponse> {
	

	private HttpServletResponse	response;

	private AgendamentoService agendamentoService;
	
	public Double calcularTaxa(Agendamento agendamento) {
		return this.agendamentoService.calcularTaxaAgendamento(agendamento);
	}		

	public void salvarAgendamento(Agendamento agendamento) throws AgendamentoException {
		this.agendamentoService.salvarAgendamento(agendamento);
	}		

	public Collection<Agendamento> listarAgendamentos() throws AgendamentoException {
		return this.agendamentoService.listarAgendamentos();
	}				
	
}
