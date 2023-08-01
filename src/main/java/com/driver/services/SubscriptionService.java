package com.driver.services;


import com.driver.EntryDto.SubscriptionEntryDto;
import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.repository.SubscriptionRepository;
import com.driver.repository.UserRepository;
import jdk.internal.loader.AbstractClassLoaderValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserRepository userRepository;

    public Integer buySubscription(SubscriptionEntryDto subscriptionEntryDto){

        //Save The subscription Object into the Db and return the total Amount that user has to pay
        Integer amount=null;
        Optional<User> optionalUser = userRepository.findById(subscriptionEntryDto.getUserId());
        if(!optionalUser.isPresent()) return null;
        User user = optionalUser.get();
        if(subscriptionEntryDto.getSubscriptionType()==SubscriptionType.BASIC){
            amount = 500 + subscriptionEntryDto.getNoOfScreensRequired()*200;
        }else if(subscriptionEntryDto.getSubscriptionType()==SubscriptionType.PRO){
            amount = 800 + subscriptionEntryDto.getNoOfScreensRequired()*250;
        }else if(subscriptionEntryDto.getSubscriptionType()==SubscriptionType.ELITE){
            amount = 1000 + subscriptionEntryDto.getNoOfScreensRequired()*350;
        }
        Subscription sb = new Subscription(subscriptionEntryDto.getSubscriptionType(),subscriptionEntryDto.getNoOfScreensRequired(),new Date(), amount);
        sb.setUser(user);
        subscriptionRepository.save(sb);
        return amount;
    }

    public Integer upgradeSubscription(Integer userId)throws Exception{

        //If you are already at an ElITE subscription : then throw Exception ("Already the best Subscription")
        //In all other cases just try to upgrade the subscription and tell the difference of price that user has to pay
        //update the subscription in the repository

        return null;
    }

    public Integer calculateTotalRevenueOfHotstar(){

        //We need to find out total Revenue of hotstar : from all the subscriptions combined
        //Hint is to use findAll function from the SubscriptionDb

        return null;
    }

}
