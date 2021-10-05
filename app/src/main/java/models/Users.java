package models;

public class Users {

    private int id;
    private String action;
    private String id_refuge;
    private String nra_code;
    private String mail;
    private String telephone;
    private String password;
    private String confirm;

    public Users(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getId_refuge() {
        return id_refuge;
    }

    public void setId_refuge(String id_refuge) {
        this.id_refuge = id_refuge;
    }

    public String getNra_code() {
        return nra_code;
    }

    public void setNra_code(String nra_code) {
        this.nra_code = nra_code;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", id_refuge='" + id_refuge + '\'' +
                ", nra_code='" + nra_code + '\'' +
                ", mail='" + mail + '\'' +
                ", telephone=" + telephone +
                ", password='" + password + '\'' +
                ", confirmez='" + confirm + '\'' +
                '}';
    }
}
