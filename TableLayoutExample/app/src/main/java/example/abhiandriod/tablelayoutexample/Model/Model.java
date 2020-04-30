package example.abhiandriod.tablelayoutexample.Model;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;

public class Model implements Serializable {

    public Model(){
        this.initModel();
    }

    private User loggedUser;
    private ArrayList<User> listaUsuarios;
    private ArrayList<Formulario> listaFormularios;

    private void initModel(){
        this.loggedUser = null;
        this.listaUsuarios = new ArrayList<User>();
        this.listaFormularios = new ArrayList<Formulario>();
        this.listaUsuarios.add(new User("1", "Adrian Ch", "@adrian.com", "adrian123", 1));
        this.listaUsuarios.add(new User("2", "Antonio Q", "@antonio.com", "antonio123", 1));
        this.listaUsuarios.add(new User("3", "Admin", "@admin.com", "admin", 0));

        this.listaFormularios.add(new Formulario("Antonio","Quesada","Cabuyal","Frente a la plaza","Alajuela","Poas","20802","Costa Rica","+506",89792734,"4/27/2020","1","Programador"));
        this.listaFormularios.add(new Formulario("Adrian","Chavarria","Centro","200 Sur","San Jose","Ciudad Colon","10202","Costa Rica","+506",88888888,"4/27/2020","2","Programador"));
    }

    public ArrayList<User> getListaUsuarios() {
        return listaUsuarios;
    }

    public ArrayList<Formulario> getListaFormularios() {return listaFormularios;}

    public Formulario getFormularioIndex (int index){return listaFormularios.get(index);}

    public User getUsuariosIndex(int index) {
        return listaUsuarios.get(index);
    }


    public Formulario getFormularioById(String formID){
        for (Formulario form:this.listaFormularios){
            if (form.getId().toLowerCase().equals(formID.toLowerCase()))
                return  form;
        }
        return null;
    }

    public User getUsuarioById(String userId) {
        for (User user:this.listaUsuarios){
            if (user.getUserId().toLowerCase().equals(userId.toLowerCase()))
                return  user;
        }
        return null;
    }

    public boolean addForm(Formulario form){
        for (Formulario f : this.listaFormularios){
            if (f.getId().toLowerCase().equals(form.getId().toLowerCase()))
                return false;
        }
        this.listaFormularios.add(form);
        return true;
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

