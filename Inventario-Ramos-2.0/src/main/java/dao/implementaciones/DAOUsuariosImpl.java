package dao.implementaciones;

import dao.DAOUsuarios;
import dao.DBConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DAOUsuariosImpl implements DAOUsuarios {

    @Override
    public int insertar(User a) {
        Connection con = null;
        int filas = -1;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();

            Number id = qr.insert(con,
                    "INSERT INTO usuarios (nombre, apellido, telefono, mail, password, dni, tipo) VALUES(?,?,?,?,?,?,?)",
                    new ScalarHandler<>(),
                    a.getNombre(),
                    a.getApellido(),
                    a.getTelefono(),
                    a.getMail(), a.getPassword(),
                    a.getDni(),
                    a.getTipo());

            a.setIdUsuario(id.intValue());
            con.commit();

        } catch (SQLException ex) {
            filas = -2;
            try {
                Logger.getLogger(DAOMarcaImpl.class.getName()).log(Level.SEVERE, null, ex);
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(DAOMarcaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOMarcaImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }

        return filas;
    }

    @Override
    public int modificar(User a) {
        Connection con = null;
        int filas = -1;
        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "UPDATE items set nombre = ?,  apellido = ?,  telefono= ?,  e-mail = ?,  password= ?,  dni = ?,  tipo= ? where idusuario=?",
                    a.getNombre(),
                    a.getApellido(),
                    a.getTelefono(),
                    a.getMail(),
                    a.getPassword(),
                    a.getDni(),
                    a.getTipo(),
                    a.getIdUsuario());

        } catch (Exception ex) {
            Logger.getLogger(DAOUsuariosImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public int borrar(User a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int borrarEntero(User a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAll() {
        List<User> lista = null;
        Connection con = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            ResultSetHandler<List<User>> handler = new BeanListHandler<>(User.class);

            lista = qr.query(con, "SELECT * FROM usuarios", handler);

        } catch (Exception ex) {
            Logger.getLogger(DAOUsuariosImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return lista;
    }

    @Override
    public User obtener(int id) {
        User usuarios = null;
        Connection con = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<User> handler = new BeanHandler<>(User.class);
            usuarios = qr.query(con, "SELECT * FROM usuarios WHERE idusuario = ? ", handler, id);
        } catch (Exception ex) {
            Logger.getLogger(DAOUsuariosImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return usuarios;
    }

}
