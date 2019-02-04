package dao.implementaciones;

import dao.DAOProducto;
import dao.DBConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Producto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DAOProductoImpl implements DAOProducto {

    @Override
    public int insertar(Producto a) {
        Connection con = null;
        int filas = -1;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();

            Number id = qr.insert(con,
                    "INSERT INTO producto (nombre, categoria, marca, modelo, descripcion, ubicacion, responsable,precio, fecha_entrada, fecha_salida, estado) VALUES(?,?,?,?,?,?,?,?,?,?,?)",
                    new ScalarHandler<>(),
                    a.getNombre(),
                    a.getCategoria(),
                    a.getMarca(),
                    a.getModelo(), a.getDescripcion(),
                    a.getUbicacion(),
                    a.getResponsable(), a.getPrecio(),
                    a.getFecha_entrada(),
                    a.getFecha_salida(),
                    a.getEstado());

            a.setIdproducto(id.intValue());
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
    public int modificar(Producto a) {
        Connection con = null;
        int filas = -1;
        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "UPDATE producto set nombre = ?,  categoria = ?,  marca= ?,  modelo = ?,  descripcion= ?,  ubicacion = ?,  responsable= ?,  fecha_entrada= ?,  fecha_salida = ?,  estado= ? where idproducto=?",
                    a.getNombre(),
                    a.getCategoria(),
                    a.getMarca(),
                    a.getModelo(),
                    a.getDescripcion(),
                    a.getUbicacion(),
                    a.getResponsable(),
                    a.getFecha_entrada(),
                    a.getFecha_salida(),
                    a.getEstado(),
                    a.getIdproducto());

        } catch (Exception ex) {
            Logger.getLogger(DAOProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public int borrar(Producto a) {
        int filas = -1;
        Connection con = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "delete from producto "
                    + "where id_producto = ?", a.getIdproducto());
        } catch (SQLException e) {
            filas = -2;
            if (e.getMessage().contains("violación")) {
                filas = -2;
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public int borrarEntero(Producto a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> lista = null;
        Connection con = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            ResultSetHandler<List<Producto>> handler = new BeanListHandler<>(Producto.class);

            lista = qr.query(con, "SELECT * FROM producto", handler);

        } catch (Exception ex) {
            Logger.getLogger(DAOProductoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return lista;
    }

    @Override
    public Producto obtener(int id) {
        Producto producto = null;
        Connection con = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Producto> handler = new BeanHandler<>(Producto.class);
            producto = qr.query(con, "SELECT * FROM producto WHERE idproducto = ? ", handler, id);
        } catch (Exception ex) {
            Logger.getLogger(DAOProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return producto;
    }

}
