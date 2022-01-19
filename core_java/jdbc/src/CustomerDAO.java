package ca.jrvs.apps.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ca.jrvs.apps.jdbc.util.DataAccessObject;

public class CustomerDAO extends DataAccessObject<Customer> {

    private static final String INSERT = "INSERT INTO customer (first_name, last_name, email, phone, address, city, state, zipcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ONE = "SELECT customer_id, first_name, last_name, email, phone, address, city, state, zipcode FROM customer WHERE customer_id=?";
    private static final String UPDATE = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, phone = ?, address = ?, city = ?, state = ?, zipcode = ?, WHERE customer_id = ?";
    private static final String DELETE = "DELETE FROM customer WHERE customer_id = ?";

    public CustomerDAO(Connection connection){
        super(connection);
    }

    @Override
    public Customer findById(long id){
        Customer customer = new Customer();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                customer.setId(rs.getLong("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZipCode(rs.getString("zipcode"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> findAll(){
        return null;
    }

    @Override
    public Customer update(Customer dto){
        Customer customer = null;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE);) {
            statements.setString(1, dto.getFirstName());
            statements.setString(2, dto.getLastName());
            statements.setString(3, dto.getEmail());
            statements.setString(4, dto.getPhone());
            statements.setString(5, dto.getAddress());
            statements.setString(6, dto.getCity());
            statements.setString(7, dto.getState());
            statements.setString(8, dto.getZipCode());
            statement.setLong(9, dto.getId());
            statement.execute();
            customer = this.findById(dto.getId());
        } catch (SQLException e) {
           e.printStackTrce();
           throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public Customer create(Customer dto){
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statements.setString(1, dto.getFirstName());
            statements.setString(2, dto.getLastName());
            statements.setString(3, dto.getEmail());
            statements.setString(4, dto.getPhone());
            statements.setString(5, dto.getAddress());
            statements.setString(6, dto.getCity());
            statements.setString(7, dto.getState());
            statements.setString(8, dto.getZipCode());
            statement.execute();
            int id = this.getLastVal(CUSTOMER_SEQUENCE);
            return this.findbyId(id);
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id){
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE);){
            statement.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}