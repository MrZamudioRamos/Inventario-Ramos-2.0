package dao.implementaciones;

import dao.DAOMarca;
import dao.DBConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Marca;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DAOMarcaImpl implements DAOMarca {

    @Override
    public int insertar(Marca a) {
        Connection con = null;
        int filas = -1;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();

            Number id = qr.insert(con,
                    "INSERT INTO marca (nombre, descripcion) VALUES(?,?)",
                    new ScalarHandler<>(),
                    a.getNombre(),
                    a.getDescripcion());

            a.setIdmarca(id.intValue());
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
    public int modificar(Marca a) {
        Connection con = null;
        int filas = -1;
        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "UPDATE marca set nombre = ?,  descripcion = ? where idmarca=?",
                    a.getNombre(),
                    a.getDescripcion(),
                    a.getIdmarca());

        } catch (Exception ex) {
            Logger.getLogger(DAOMarcaImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public int borrar(Marca a) {
        int filas = -1;
        Connection con = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "DELETE FROM marca WHERE idmarca = ?", a.getIdmarca());
        } catch (SQLException e) {
            if (e.getMessage().contains("violación")) {
                filas = -2;
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOMarcaImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public int borrarEntero(Marca a) {
        int filas = -1;
        Connection con = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "DELETE FROM marca WHERE idmarca = ?", a.getIdmarca());
        } catch (SQLException e) {
            if (e.getMessage().contains("violación")) {
                filas = -2;
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOMarcaImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public List<Marca> getAll() {
        List<Marca> lista = null;
        Connection con = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            ResultSetHandler<List<Marca>> handler = new BeanListHandler<>(Marca.class);

            lista = qr.query(con, "SELECT * FROM marca", handler);

        } catch (Exception ex) {
            Logger.getLogger(DAOMarcaImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return lista;
    }

    @Override
    public Marca obtener(int id) {
        Marca marca = null;
        Connection con = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Marca> handler = new BeanHandler<>(Marca.class);
            marca = qr.query(con, "SELECT * FROM marca WHERE idmarca = ? ", handler, id);
        } catch (Exception ex) {
            Logger.getLogger(DAOMarcaImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return marca;
    }

}
