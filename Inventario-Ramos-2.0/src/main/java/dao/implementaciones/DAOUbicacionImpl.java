package dao.implementaciones;

import dao.DAOUbicacion;
import dao.DBConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ubicacion;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


public class DAOUbicacionImpl implements DAOUbicacion {

    @Override
    public int insertar(Ubicacion a) {
        Connection con = null;
        int filas = -1;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();

            Number id = qr.insert(con,
                    "INSERT INTO ubicacion (nombre, descripcion, fechacreacion) VALUES(?,?,?)",
                    new ScalarHandler<>(),
                    a.getNombre(),
                    a.getDescripcion(),
                    a.getFechecreacion());

            a.setIdubicacion(id.intValue());
            con.commit();

        } catch (SQLException ex) {
            filas = -2;
            try {
                Logger.getLogger(DAOUbicacionImpl.class.getName()).log(Level.SEVERE, null, ex);
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(DAOUbicacionImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOUbicacionImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }

        return filas;
    }

    @Override
    public int modificar(Ubicacion a) {
        Connection con = null;
        int filas = -1;
        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "UPDATE ubicacion set nombre = ?,  descripcion = ?,  fechacreacion= ? where idubicacion=?",
                    a.getNombre(),
                    a.getDescripcion(),
                    a.getFechecreacion(),
                    a.getIdubicacion());

        } catch (Exception ex) {
            Logger.getLogger(DAOUbicacionImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public int borrar(Ubicacion a) {
        int filas = -1;
        Connection con = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "DELETE FROM ubicacion WHERE idubicacion = ?", a.getIdubicacion());
        } catch (SQLException e) {
            if (e.getMessage().contains("violación")) {
                filas = -2;
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOUbicacionImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public int borrarEntero(Ubicacion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ubicacion> getAll() {
        List<Ubicacion> lista = null;
        Connection con = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            ResultSetHandler<List<Ubicacion>> handler = new BeanListHandler<>(Ubicacion.class);

            lista = qr.query(con, "SELECT * FROM ubicacion", handler);

        } catch (Exception ex) {
            Logger.getLogger(DAOUbicacionImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return lista;
    }

    @Override
    public Ubicacion obtener(int id) {
        Ubicacion ubicacion = null;
        Connection con = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Ubicacion> handler = new BeanHandler<>(Ubicacion.class);
            ubicacion = qr.query(con, "SELECT * FROM ubicacion WHERE idubicacion = ? ", handler, id);
        } catch (Exception ex) {
            Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return ubicacion;
    }
    
}
