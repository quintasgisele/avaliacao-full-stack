package br.com.wayon.repository.core;

import java.util.Collection;

import br.com.wayon.modelo.Base;

public interface AgendamentoRepository {
	
	public <T extends Base> Collection<T> obterListaDoRepositorio(Class<T> classe);

	public <T extends Base> void salvarNoRepositorio(T base);
}
