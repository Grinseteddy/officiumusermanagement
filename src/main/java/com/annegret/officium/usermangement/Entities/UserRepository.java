package com.annegret.officium.usermangement.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    public UserEntity findUserEntityById(String Id);
    public UserEntity findUserEntityByUsername(String userName);
    public UserEntity findUserEntityByEmailAddress(String emailAddress);

}
