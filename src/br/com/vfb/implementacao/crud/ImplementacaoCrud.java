package br.com.vfb.implementacao.crud;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.vfb.hibernate.session.HibernateUtil;
import br.com.vfb.interfac.crud.InterfaceCrud;

@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T>{

	private static final long serialVersionUID = 1L;
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Autowired
	private JdbcTemplateImp jdbcTempImp;
	
	@Autowired
	private SimpleJdbcImp simpleJdbcImp;
	
	@Autowired
	private SimpleJdbcInsert simpleJdbcInsert;
	
	@Autowired
	private SimpleJdbcClassImp SimpleJdbcClassImp;

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public JdbcTemplateImp getJdbcTempImp() {
		return jdbcTempImp;
	}

	public SimpleJdbcImp getSimpleJdbcImp() {
		return simpleJdbcImp;
	}

	public SimpleJdbcClassImp getSimpleJdbcClassImp() {
		return SimpleJdbcClassImp;
	}

	@Override
	public void save(T obj) throws Exception {
		
		
	}

	@Override
	public void persist(T obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(T obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T merge(T obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findList(Class<T> classe) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findById(Class<T> entidade, Long Id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findListByQueryDinamica(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeUpdateQueryDinamica(String hql) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeUpdateSQLDinamica(String sql) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearSession() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evict(T obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Session getSession() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getListSQLDinamica(String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long totalRegistro(String table) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByListQueryDinamica(String query, int inicioRegistro,int MaximoResultado) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void validaSessionFactory() {
		if(sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		
		validaTransaction();
	}
	
	private void validaTransaction(){
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}
	
	private void commitProcessoAjax (){
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	private void rollBackAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}
	
	
}
