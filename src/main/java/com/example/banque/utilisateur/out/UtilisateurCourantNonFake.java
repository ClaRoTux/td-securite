package com.example.banque.utilisateur.out;
import com.example.banque.utilisateur.Utilisateur;
import com.example.banque.utilisateur.UtilisateurRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UtilisateurCourantNonFake implements UtilisateurCourant{

    private final UtilisateurRepository utilisateurs;

    public UtilisateurCourantNonFake(UtilisateurRepository utilisateurs){
        this.utilisateurs = utilisateurs;
    }


    @Override
    public Optional<Utilisateur> get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            return utilisateurs.findByLogin(authentication.getName());
        }
        return Optional.empty();
    }

}
