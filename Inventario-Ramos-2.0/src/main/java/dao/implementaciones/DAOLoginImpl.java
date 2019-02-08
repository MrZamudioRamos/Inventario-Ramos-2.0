package dao.implementaciones;

import dao.DAOLogin;
import dao.DBConnectionPool;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.PasswordHash;

public class DAOLoginImpl implements DAOLogin {

    @Override
    public boolean comprobarUser(String user, String pass) {
        boolean ok = false;
        Connection con = null;
        PasswordHash ph = new PasswordHash();
        List<User> usuarios = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            ResultSetHandler<List<User>> handler = new BeanListHandler<>(User.class);

            usuarios = qr.query(con, "SELECT * FROM usuarios ", handler);

            for (User us : usuarios) {

                String passHasheada = us.getPassword();

                if (us.getUser().equals(user) && ph.validatePassword(pass, passHasheada) && (us.isAdmin() || true && us.isInvent() == true)) {
                    ok = true;
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(DAOLoginImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
    
    
    public boolean UsuarioNormal (String user, String pass) {
        boolean validar = false;
        Connection con = null;
        PasswordHash ph = new PasswordHash();
        List<User> usuarios = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            ResultSetHandler<List<User>> handler = new BeanListHandler<>(User.class);

            usuarios = qr.query(con, "SELECT * FROM usuarios ", handler);

            for (User us : usuarios) {

                String passHasheada = us.getPassword();

                if (us.getUser().equals(user) && ph.validatePassword(pass, passHasheada) && us.isAdmin() != true && us.isInvent()!= true) {
                    validar = true;
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(DAOLoginImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }
    
    public boolean Admin (String user, String pass) {
        boolean validar = false;
        Connection con = null;
        PasswordHash ph = new PasswordHash();
        List<User> usuarios = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            ResultSetHandler<List<User>> handler = new BeanListHandler<>(User.class);

            usuarios = qr.query(con, "SELECT * FROM usuarios ", handler);

            for (User us : usuarios) {

                String passHasheada = us.getPassword();

                if (us.getUser().equals(user) && ph.validatePassword(pass, passHasheada) && us.isAdmin() == true ) {
                    validar = true;
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(DAOLoginImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }
    
    public boolean Invent (String user, String pass) {
        boolean validar = false;
        Connection con = null;
        PasswordHash ph = new PasswordHash();
        List<User> usuarios = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            ResultSetHandler<List<User>> handler = new BeanListHandler<>(User.class);

            usuarios = qr.query(con, "SELECT * FROM usuarios ", handler);

            for (User us : usuarios) {

                String passHasheada = us.getPassword();

                if (us.getUser().equals(user) && ph.validatePassword(pass, passHasheada) && us.isInvent()== true) {
                    validar = true;
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(DAOLoginImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validar;
    }


}
