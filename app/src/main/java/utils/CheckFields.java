package utils;

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



}
