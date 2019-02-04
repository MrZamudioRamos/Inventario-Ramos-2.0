/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementaciones;

import dao.DAOTipoEstado;
import dao.DBConnectionPool;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TipoEstado;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


public class DAOTipoEstadoImpl implements DAOTipoEstado {

    @Override
    public int insertar(TipoEstado a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int modificar(TipoEstado a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int borrar(TipoEstado a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int borrarEntero(TipoEstado a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
   public List<TipoEstado> getAll() {
        List<TipoEstado> lista = null;
        Connection con = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            ResultSetHandler<List<TipoEstado>> handler = new BeanListHandler<>(TipoEstado.class);

            lista = qr.query(con, "SELECT * FROM tipoestado", handler);

        } catch (Exception ex) {
            Logger.getLogger(DAOTipoEstadoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return lista;
    }

    @Override
    public TipoEstado obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
