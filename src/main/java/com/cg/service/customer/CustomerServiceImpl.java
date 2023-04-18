package com.cg.service.customer;

import com.cg.model.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {

//    public static List<Customer> customers = new ArrayList<Customer>();
//    public static Long customerId = 1L;
//
//    static {
//        customers.add(new Customer(customerId++, "NVA", "nva@co.cc", "2345", "28 Nguyễn Tri Phương"));
//        customers.add(new Customer(customerId++, "NVB", "nvb@co.cc", "3456", "29 Nguyễn Tri Phương"));
//        customers.add(new Customer(customerId++, "NVC", "nvc@co.cc", "4567", "38 Nguyễn Tri Phương"));
//        customers.add(new Customer(customerId++, "NVD", "nvd@co.cc", "5678", "39 Nguyễn Tri Phương"));
//    }
//
//    public static List<Customer> init() {
//        return customers;
//    }
//
//    public static Customer findById(Long id) {
//        Customer customer = new Customer();
//
//        for (Customer item : customers) {
//            if (item.getId().equals(id)) {
//                customer = item;
//            }
//        }
//
//        return customer;
//    }

    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        String queryStr = "SELECT c FROM Customer AS c";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer getOne(Long id) {
        String queryStr = "SELECT * FROM customers AS c WHERE c.id = :id";
        Query query = entityManager.createNativeQuery(queryStr, Customer.class);
        query.setParameter("id", id);
        return (Customer) query.getSingleResult();
    }

    @Override
    public Customer findOne(Long id) {
        String queryStr = "SELECT c FROM Customer AS c WHERE c.id = :id";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Customer save(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if (customer.getId() == null) {
                session.saveOrUpdate(customer);
                transaction.commit();
                return customer;
            }
            else {
                Customer origin = findOne(customer.getId());
                origin.setFullName(customer.getFullName());
                origin.setEmail(customer.getEmail());
                origin.setAddress(customer.getAddress());
                session.saveOrUpdate(origin);
                transaction.commit();
                return origin;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
