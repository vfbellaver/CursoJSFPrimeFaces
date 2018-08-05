package br.com.vfb.hibernate.session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

import br.com.vfb.implementacao.crud.VariavelConexaoUtil;

/**
 * Responsavel por estabelecer conexão com HIBERNATE.
 * @author vfbellaver
 *
 */
@ApplicationScoped
public class HibernateUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
	
	private static Session sessionFactory = buildSessionFactory();

	/**
	 * Responsável por ler o arquivo de configuração hibernate.cfg.xml
	 * @return SessionFactory
	 */
	private static Session buildSessionFactory() {
		try {
			if(sessionFactory == null) {
				sessionFactory = (Session) new Configuration().configure().buildSessionFactory();
			}
			
			return sessionFactory;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conexão SessionFactory");
		}
	}
	
	/**
	 * Retorna o SessionFactory corrente
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return (SessionFactory) sessionFactory;
	}
	
	/**
	 * Retorna a sessão do SessionFactory
	 * @return Session
	 */
	public static Session getCurrentSession(){
		return getSessionFactory().getCurrentSession(); 
	}
	
	/**
	 * Abre uma nova sessão do SessionFactory
	 * @return
	 */
	public static Session openSession(){
		if(sessionFactory == null) {
			buildSessionFactory();
		}
		
		return ((SessionFactory) sessionFactory).openSession();
	} 
	
	/**
	 * Obter a conexão do provedor de conexões configurado 
	 * @return Connection SQL 
	 * @throws SQLException
	 */
	public static Connection getConnectionProvider() throws SQLException{
		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
	}
	
	/**
	 * 
	 * @return Connection no InitialContext java:/comp/env/jdbc/datasource
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(JAVA_COMP_ENV_JDBC_DATA_SOURCE);
		
		return ds.getConnection();
	}
	
	/**
	 * Retorna DataSource
	 * @return DataSource JDNI Tomcat
	 * @throws NamingException
	 */
	public DataSource getDataSourceJNDI() throws NamingException{
		InitialContext context = new InitialContext();
		return (DataSource) context.lookup(VariavelConexaoUtil.JAVA_COMP_ENV_JDBC_DATA_SOURCE);
	}
}
	
