package utils;

import android.util.Patterns;

import java.util.regex.Pattern;

import models.Users;

public class CheckFields {


    public static String nom_refuge_inscription(Users subs){
        String msg_nom = "";
        String nom = subs.getId_refuge();
        String ortho = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$";
        if(nom.isEmpty()){
            msg_nom = "Un nom doit être saisi.";
        }else if(!Pattern.matches(ortho, nom)){
            msg_nom = "Format de Nom incorrect";
        }
        return msg_nom;
    }

    public static String mail_refuge_inscription(Users subs){
        String msg_mail = "";
        String mail = subs.getMail();
        if(mail.isEmpty()){
            msg_mail = "Mail Requis.";
        }else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            msg_mail = "Format mail incorrect.";
        }
        return msg_mail;
    }

    public static String rna_refuge_inscription(Users subs){
        String msg_nom = "";
        String nom = subs.getNra_code();
        String ortho = "[W][0-9]{9}";
        if(nom.isEmpty()){
            msg_nom = "Un code doit être saisi.";
        }else if(!Pattern.matches(ortho, nom)){
            msg_nom = "Format incorrect";
        }
        return msg_nom;
    }

    public static String password_refuge_inscription(Users subs){
        String msg_password ="";
        String password = subs.getPassword();
        String regPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        if(password.isEmpty()){
            msg_password = "Mot de passe requis.";
        }else if (!Pattern.matches(regPassword, password)){
            msg_password = "Format incorrect.";
        }
        return msg_password;
    }

    public static String telephone_refuge_inscription(Users subs){
        String msg_password ="";
        String password = subs.getTelephone();
        String regPassword = "[0-9]{10}";
        if(password.isEmpty()){
            msg_password = "Telephone requis.";
        }else if (!Pattern.matches(regPassword, password)){
            msg_password = "telephone incorrect.";
        }
        return msg_password;
    }

    public static String confirm_refuge_inscription(Users subs){
        String msg_confirm ="";
        String password = subs.getPassword();
        if(password.isEmpty()){
            msg_confirm = "Confirmez le mot de passe.";
        }else if(!subs.getPassword().equals(subs.getConfirm())){
            msg_confirm = ("Les mots de passe ne correspondent pas.");
        }
        return msg_confirm;
    }
    public static String password_refuge_connexion(Users subs){
        String msg_password ="";
        String password = subs.getPassword();
        if(password.isEmpty()){
            msg_password = "Mot de passe requis.";
        }
        return msg_password;
    }

    public static String password_refuge_connexion(Users subs){
        String msg_password ="";
        String password = subs.getPassword();
        if(password.isEmpty()){
            msg_password = "Mot de passe requis.";
        }
        return msg_password;
    }

}


