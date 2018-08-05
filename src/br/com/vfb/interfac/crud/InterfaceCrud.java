package br.com.vfb.interfac.crud;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.postgresql.jdbc4.Jdbc4Array;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable{

	//salva os dados
	void save(T obj) throws Exception;
	
	void persist(T obj) throws Exception;
	
	// salva ou atualiza
	void saveOrUpdate(T obj) throws Exception;
	
	// atualiza os dados
	void update(T obj) throws Exception;
	
	//deleta os dados
	void delete(T obj) throws Exception;
	
	// salva/atualiza e retorna os dados
	T merge(T obj) throws Exception;
	
	// carrega lista de dados
	List<T> findList(Class<T> classe) throws Exception;
	
	// busca objeto e retorna
	T findById(Class<T> entidade,Long Id) throws Exception;
	
	// traz lista dinamica
	List<T> findListByQueryDinamica(String s) throws Exception;
	
	// executa update com HQL
	void executeUpdateQueryDinamica(String hql) throws Exception;
	
	// executa update com SQL
	void executeUpdateSQLDinamica(String sql) throws Exception;
	
	// limpa sessão do hibernate
	void clearSession() throws Exception;
	
	// retira o objeto da sessão do hibernate
	void evict(T obj) throws Exception;
	
	// retorna sessão dentro da interface
	Session getSession() throws Exception;
	
	List<?> getListSQLDinamica(String sql) throws Exception;
	
	// trabalha com JDBC do Spring
	JdbcTemplate getJdbcTemplate();
	
	SimpleJdbcTemplate getSimpleJdbcTemplate();
	
	SimpleJdbcInsert getSimpleJdbcInsert();
	
	//retorna quantidade de registro de uma tabela
	Long totalRegistro(String table) throws Exception;
	
	Query obterQuery(String query) throws Exception;
	
	// busca dados com limite de dados, passando registro inicial;
	List<T> findByListQueryDinamica(String query,int inicioRegistro, int MaximoResultado) throws Exception;
}
