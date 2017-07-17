import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;


public class ManageEmployee {
	String first_name, last_name;
	int salary;
	private static SessionFactory factory; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try{
	         factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure()).addAnnotatedClass(Employee.class).buildSessionFactory();
		 	}catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
		 	}			
		 ManageEmployee ME= new ManageEmployee();
		 Integer empID1 = ME.addEmployee("Lakshay", "Jain", 5000);
}//main method
	
	 public Integer addEmployee(String fname, String lname, int salary){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer employeeID = null;
	      try{
	    	  this.first_name=fname;
	    	  this.last_name=lname;
	    	  this.salary=salary;
	         tx = session.beginTransaction();
	         Employee employee = new Employee();
	         employee.setFirst_name(fname);
	         employee.setLast_name(lname);
	         employee.setSalary(salary);
	         employeeID = (Integer) session.save(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return employeeID;
	   }
}//