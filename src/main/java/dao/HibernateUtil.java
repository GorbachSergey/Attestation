package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			// создаёт и предоставляет сервисы, которые нужны Hibernate для старта и
			// дальнейшей работы
			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			try {
				// MetadataSource настраивает связь между классами и таблицами
				sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			} catch (Exception e) {
				StandardServiceRegistryBuilder.destroy(registry);
				throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
			}
		}
		return sessionFactory;

	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
