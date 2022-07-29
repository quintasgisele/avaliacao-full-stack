package br.com.wayon.repository.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.wayon.modelo.Base;

public class AgendamentoRepositoryImpl implements AgendamentoRepository {

	private Map<Class<? extends Base>, Collection<? extends Base>> mapaRepositorioMemoria = 
			new HashMap<Class<? extends Base>, Collection<? extends Base>>();
	
	@SuppressWarnings("unchecked")
	public <T extends Base> Collection<T> obterListaDoRepositorio(Class<T> classe) {
		return (Collection<T>) this.getMapaRepositorioMemoria().get(classe);
	}

	@SuppressWarnings("unchecked")
	public <T extends Base> void salvarNoRepositorio(T base) {
		Class<T> classe = (Class<T>) base.getClass();
		Collection<T> colecao = this.obterListaDoRepositorio(classe);
		
		if (colecao == null) {
			
			colecao = new ArrayList<T>();
			colecao.add(base);
			this.getMapaRepositorioMemoria().put(classe, colecao);
			
		} else {
			
			colecao.add(base);
			
		}
	}

	// Get & Set
	public Map<Class<? extends Base>, Collection<? extends Base>> getMapaRepositorioMemoria() {
		return mapaRepositorioMemoria;
	}

	public void setMapaRepositorioMemoria(
			Map<Class<? extends Base>, Collection<? extends Base>> mapaRepositorioMemoria) {
		this.mapaRepositorioMemoria = mapaRepositorioMemoria;
	}
	
}
