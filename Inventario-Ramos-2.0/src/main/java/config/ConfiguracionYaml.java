package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author dam2
 */
public class ConfiguracionYaml {

    private static ConfiguracionYaml config;

    private ConfiguracionYaml() {

    }

    
    public static ConfiguracionYaml getInstance() {

        if (config == null) {
            try {
                Yaml yaml = new Yaml();
                config = (ConfiguracionYaml) yaml.loadAs(
                        new FileInputStream("config/configuracionUsuario.yml"),
                        ConfiguracionYaml.class);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConfiguracionYaml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return config;
    }

    private String user;
    private String pass;
    private String urlDB;
    private String driverDB;
    private String userDB;
    private String passDB;
    private int tipoDao;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUrlDB() {
        return urlDB;
    }

    public void setUrlDB(String urlDB) {
        this.urlDB = urlDB;
    }

    public String getDriverDB() {
        return driverDB;
    }

    public void setDriverDB(String driverDB) {
        this.driverDB = driverDB;
    }

    public String getUserDB() {
        return userDB;
    }

    public void setUserDB(String userDB) {
        this.userDB = userDB;
    }

    public String getPassDB() {
        return passDB;
    }

    public void setPassDB(String passDB) {
        this.passDB = passDB;
    }

    public int getTipoDao() {
        return tipoDao;
    }

    public void setTipoDao(int tipoDao) {
        this.tipoDao = tipoDao;
    }
    
}
