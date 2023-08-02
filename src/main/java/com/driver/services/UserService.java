package com.driver.services;


import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.model.WebSeries;
import com.driver.repository.UserRepository;
import com.driver.repository.WebSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WebSeriesRepository webSeriesRepository;


    public Integer addUser(User user){

        //Jut simply add the user to the Db and return the userId returned by the repository
//        if(userRepository.findById(user.getId()).isPresent()) return -1;
//        User newUser = new User();
//        newUser.setAge(user.getAge());
//        newUser.setSubscription(user.getSubscription());
//        newUser.setName(user.getName());
//        newUser.setMobNo(user.getMobNo());
        User savedUser = userRepository.save(user);
        System.out.println(savedUser);
//        System.out.println(newUser.getId()+" "+user.getId());
        return savedUser.getId();
    }

    public Integer getAvailableCountOfWebSeriesViewable(Integer userId){

        //Return the count of all webSeries that a user can watch based on his ageLimit and subscriptionType
        //Hint: Take out all the Webseries from the WebRepository

        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()) return 0;
        User user = userOptional.get();
        SubscriptionType sbt = user.getSubscription().getSubscriptionType();
        List<WebSeries> wbs = webSeriesRepository.findAll();
        Integer countViewable = 0;
        int userAge = user.getAge();
        for(WebSeries ws: wbs){
            if(userAge<ws.getAgeLimit()) continue;
            if(sbt==SubscriptionType.PRO && ws.getSubscriptionType()==SubscriptionType.ELITE) continue;
            if(sbt==SubscriptionType.BASIC && (ws.getSubscriptionType()==SubscriptionType.ELITE || ws.getSubscriptionType()==SubscriptionType.PRO)) continue;
            countViewable++;
        }
        return countViewable;
    }


}
