package dao.implementaciones;

import dao.DAOTipoUsuario;
import dao.DBConnectionPool;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TipoUsuario;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class DAOTipoUsuarioImpl implements DAOTipoUsuario {

    @Override
    public int insertar(TipoUsuario a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int modificar(TipoUsuario a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int borrar(TipoUsuario a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int borrarEntero(TipoUsuario a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoUsuario> getAll() {
        List<TipoUsuario> lista = new ArrayList<>();
        Connection con = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            ResultSetHandler<List<TipoUsuario>> handler = new BeanListHandler<>(TipoUsuario.class);

            lista = qr.query(con, "SELECT * FROM tipousuario", handler);

        } catch (Exception ex) {
            Logger.getLogger(DAOTipoUsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            DBConnectionPool.getInstance().cerrarConexion(con);
        }
        return lista;
    }

    @Override
    public TipoUsuario obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
