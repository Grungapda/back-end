package com.grungapda.backend.user.command.application.service;


import com.grungapda.backend.user.command.application.dto.update.UserCustomDTO;
import com.grungapda.backend.user.command.domain.aggregate.entity.User;
import com.grungapda.backend.user.command.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCustomService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(UserCustomDTO updatedUser){
        User existingEntity = userRepository.findUsersByUserNo(updatedUser.getUserNo()).orElse(null);

        if (existingEntity != null){
            existingEntity.setCharacterType(updatedUser.getCharacterType());
            existingEntity.setHexStringCloth(updatedUser.getHexStringCloth());
            existingEntity.setHexStringSkin(updatedUser.getHexStringSkin());
            existingEntity.setHexStringFace(updatedUser.getHexStringFace());
            existingEntity.setHexStringRibbon(updatedUser.getHexStringRibbon());
            existingEntity.setCrownOn(updatedUser.getIsCrownOn());
            existingEntity.setGlassOn(updatedUser.getIsGlassOn());
            existingEntity.setBagOn(updatedUser.getIsBagOn());
            existingEntity.setCapOn(updatedUser.getIsBagOn());

            return userRepository.save(existingEntity);
        }
        return null;
    }
}
