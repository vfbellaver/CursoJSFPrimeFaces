package br.com.vfb.implementacao.crud;

import java.util.ArrayList;
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
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {

	private static final long serialVersionUID = 1L;

	private static SessionFactory sessionFactory = HibernateUtil
			.getSessionFactory();

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
		validaSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	@Override
	public void persist(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().persist(obj);
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();
	}

	@Override
	public void update(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();
	}

	@Override
	public void delete(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();
	}

	@Override
	public T merge(T obj) throws Exception {
		validaSessionFactory();
		obj = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		return obj;
	}

	@Override
	public List<T> findList(Class<T> entidade) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();

		query.append("select distinct(entity) from")
			 .append(entidade.getSimpleName())
			 .append("entity");

		List<T> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();

		return lista;
	}

	@Override
	public T findById(Class<T> entidade, Long id) throws Exception {
		validaSessionFactory();
		T obj = (T)sessionFactory.getCurrentSession().load(getClass(),id);
		
		return obj;
	}

	@Override
	public List<T> findListByQueryDinamica(String s) throws Exception {
		validaSessionFactory();
		
		List<T>  lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(s).list();
		
		return lista;
	}

	@Override
	public void executeUpdateQueryDinamica(String hql) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void executeUpdateSQLDinamica(String sql) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();

	}

	@Override
	public void evict(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().evict(obj);
	}

	@Override
	public Session getSession() throws Exception {
		validaSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<?> getListSQLDinamica(String sql) throws Exception {
		validaSessionFactory();
		List<T> lista = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return null;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTempImp;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		
		return simpleJdbcImp;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		
		return simpleJdbcInsert;
	}

	@Override
	public Long totalRegistro(String table) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from").append(table);
		return jdbcTempImp.queryForLong(sql.toString());
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		validaSessionFactory();
		Query queryReturn = (Query) sessionFactory.getCurrentSession().createQuery(query.toString());
		return queryReturn;
	}

	@Override
	public List<T> findByListQueryDinamica(String query, int inicioRegistro,int maximoResultado) throws Exception {
		validaSessionFactory();
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(query).setFirstResult(inicioRegistro)
																	 .setMaxResults(maximoResultado)
																	 .list();
		return lista;
	}

	private void validaSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}

		validaTransaction();
	}

	private void validaTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}

	private void commitProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	private void rollBackAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}

	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}

	
	public List<Object[]> getListaDinamica(String sql) throws Exception {
		validaSessionFactory();
		List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		
		return lista;
	}
}
