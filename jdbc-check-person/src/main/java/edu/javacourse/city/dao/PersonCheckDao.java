package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckException;

import java.sql.*;


public class PersonCheckDao {
    private static final String SQL_REQUEST ="select temporal from cr_address_person ap\n" +
            "inner join cr_person p on p.person_id = ap.person_id\n" +
            "inner join cr_address a on a.address_id = ap.address_id\n" +
            "where\n"+
            "CURRENT_DATE >= ap.start_date and (CURRENT_DATE <= ap.end_date or ap.end_date is null) \n"+
            "and upper(p.sur_name) = upper (?) \n" +
            "and upper(p.given_name) = upper(?) \n" +
            "and upper(p.patronymic) = upper(?)\n" +
            "and p.date_of_birth = ? \n" +
            "and a.street_code = ? \n" +
            "and upper(a.building) = upper(?)\n";

    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }
    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }
    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();
        String sql = SQL_REQUEST;
        if (request.getExtension() != null){
            sql+= "and upper(a.extension) = upper(?)\n";
        }else {
            sql+= "and a.extension is null\n";
        }
        if (request.getApartment() !=null){
            sql+= "and upper(a.apartment) = upper(?)\n";
        }
        else {
            sql+= "and a.apartment is null\n";
        }
        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            int count = 1;

            stmt.setString(count++, request.getSurName());
            stmt.setString(count++, request.getGivenName());
            stmt.setString(count++, request.getPatronymic());
            stmt.setDate(count++, Date.valueOf(request.getDateOfBirth()));
            stmt.setInt(count++, request.getStreetCode());
            stmt.setString(count++, request.getBuilding());
            if (request.getExtension() !=null){
                stmt.setString(count++, request.getExtension());
            }
            if (request.getApartment() != null) {
                stmt.setString(count++, request.getApartment());
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }
        }catch (SQLException ex){
            throw new PersonCheckException(ex);
        }
        return response;
    }
}
