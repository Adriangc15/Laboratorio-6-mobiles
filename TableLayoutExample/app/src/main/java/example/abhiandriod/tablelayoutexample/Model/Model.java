package example.abhiandriod.tablelayoutexample.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {

    public Model(){
        this.initModel();
    }

    private User loggedUser;
    private ArrayList<User> listaUsuarios;

    private void initModel(){
        this.loggedUser = null;
        this.listaUsuarios = new ArrayList<User>();
        this.listaUsuarios.add(new User("1", "Adrian Ch", "@adrian.com", "adrian123", 1));
        this.listaUsuarios.add(new User("2", "Antonio Q", "@antonio.com", "antonio123", 1));
        this.listaUsuarios.add(new User("3", "Admin", "@admin.com", "admin", 0));
    }

    public ArrayList<User> getListaUsuarios() {
        return listaUsuarios;
    }

    public User getListaUsuariosIndex(int index) {
        return listaUsuarios.get(index);
    }

    public User getUsuarioById(String userId) {
        for (User user:this.listaUsuarios){
            if (user.getUserId().toLowerCase().equals(userId.toLowerCase()))
                return  user;
        }
        return null;
    }

    public boolean addUser(User user){
        for (User u : this.listaUsuarios){
            if (u.getUserId().toLowerCase().equals(user.getUserId().toLowerCase()))
                return false;
        }
        this.listaUsuarios.add(user);
        return true;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
    }
}

