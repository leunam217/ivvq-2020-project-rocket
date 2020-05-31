package com.teamrocket.projetdevop.ivvqproject.services;



import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.repositories.ShoppingCartRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserServiceIT {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingCartRepository cartRepository;

    @Autowired
    UserService userService;

    private User user;

    @BeforeEach
    void setup()
    {
        this.user = new User("bob@email.com",passwordEncoder.encode("secret"),"Bob","21345","Toulouse");

    }

    @Test
    public void testSavedProductHasEmail(){
        // given: un user non persisté user
        // when: user est persisté
        userService.save(user);
        // then: user a un email
        assertNotNull(user.getEmail());
    }

    @Test
    public void testFetchedUserIsNotNull() {
        // given: une user persisté user
        userService.save(user);
        // when: on appelle findOne avec l'id de ce user
        User fetched = userService.findOne(user.getEmail());
        // then: le résultat n'est pas null
        assertNotNull(fetched);
    }

    @Test
    public void testFetchedUserIsUnchangedForName() {
        // given: un user persisté user
        userService.save(user);
        // when: on appelle findOne avec l'id de ce user
        User fetched = userService.findOne(user.getEmail());
        // then: le user obtenue en retour a le bon id
        assertEquals(fetched.getEmail(), user.getEmail());
        // then : le user obtenue en retour a le bon nom
        assertEquals(fetched.getName(), user.getName());
    }

    @Test
    public void testUpdatedUtilisateurIsUpdated() {
        // given: un Utilisateur persisté util
        userService.save(user);

        User fetched = userService.findOne(user.getEmail());
        // when: le name est modifié au niveau "objet"
        fetched.setName("coucou");
        // when: l'objet util est mis à jour en base
        userService.update(fetched);
        // when: l'objet util est relu en base
        User fetchedUpdated = userService.findOne(user.getEmail());
        // then: le name a bien été mis à jour
        assertEquals(fetched.getName(), fetchedUpdated.getName());
    }

    @Test
    public void testFindUtilisateurWithUnexistingEmail() {
        // when:  findOne est appelé avec un email ne correspondant à aucun objet en base
        // then: null est retourné
        assertNull(userService.findOne("desgr@test.com"));
    }

    @Test
    public void testFetchedUtilisateurHasGoodId() {
        // given: un Utilisateur persisté user
        userService.save(user);
        // when: on appelle findOne avec l'email de cet Utilisateur
        User fetched = userService.findOne(user.getEmail());
        // then: l'Utilisateur obtenu en retour a le bon id
        assertEquals(user.getEmail(), fetched.getEmail());
    }

}
