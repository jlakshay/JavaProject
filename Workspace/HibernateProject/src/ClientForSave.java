
import org.hibernate.*;
import org.hibernate.cfg.*;

public class ClientForSave {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Configuration cfg= new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			SessionFactory factory= cfg.buildSessionFactory();
			Session session= factory.openSession();
			Employee p= new Employee();
			
			p.setName("Tanvi");
			p.setId(11);
			p.setAge(33);
			p.setSalary(345643);
			
			Transaction tx= session.beginTransaction();
			session.save(p);
			System.out.println("Object saved Successfully...!!");
			tx.commit();
			session.close();
			factory.close();
	}

}
