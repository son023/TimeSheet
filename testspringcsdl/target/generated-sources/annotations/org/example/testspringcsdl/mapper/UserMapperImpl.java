package org.example.testspringcsdl.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.testspringcsdl.dto.request.UserCreationRequest;
import org.example.testspringcsdl.dto.respone.UserResponse;
import org.example.testspringcsdl.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public void updateUser(User user, UserCreationRequest request) {
        if ( request == null ) {
            return;
        }

        user.setUserName( request.getUserName() );
        user.setPassWord( request.getPassWord() );
        user.setFullName( request.getFullName() );
        user.setEmail( request.getEmail() );
        if ( request.getStartDate() != null ) {
            user.setStartDate( LocalDate.parse( request.getStartDate() ) );
        }
        else {
            user.setStartDate( null );
        }
        user.setAllowedDay( request.getAllowedDay() );
        user.setSalary( request.getSalary() );
        if ( request.getSalaryAt() != null ) {
            user.setSalaryAt( LocalDate.parse( request.getSalaryAt() ) );
        }
        else {
            user.setSalaryAt( null );
        }
        user.setIsActive( request.getIsActive() );
        user.setSex( request.getSex() );
    }

    @Override
    public User toUser(UserCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userName( request.getUserName() );
        user.passWord( request.getPassWord() );
        user.fullName( request.getFullName() );
        user.email( request.getEmail() );
        if ( request.getStartDate() != null ) {
            user.startDate( LocalDate.parse( request.getStartDate() ) );
        }
        user.allowedDay( request.getAllowedDay() );
        user.salary( request.getSalary() );
        if ( request.getSalaryAt() != null ) {
            user.salaryAt( LocalDate.parse( request.getSalaryAt() ) );
        }
        user.isActive( request.getIsActive() );
        user.sex( request.getSex() );

        return user.build();
    }

    @Override
    public UserResponse userToResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setUserName( user.getUserName() );
        userResponse.setFullName( user.getFullName() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setPosition( user.getPosition() );
        userResponse.setBranch( user.getBranch() );
        userResponse.setWorkingTime( user.getWorkingTime() );
        userResponse.setRole( user.getRole() );
        userResponse.setType( user.getType() );
        userResponse.setLevel( user.getLevel() );
        userResponse.setStartDate( user.getStartDate() );
        userResponse.setAllowedDay( user.getAllowedDay() );
        userResponse.setSalary( user.getSalary() );
        userResponse.setSalaryAt( user.getSalaryAt() );
        userResponse.setIsActive( user.getIsActive() );
        userResponse.setSex( user.getSex() );

        return userResponse;
    }

    @Override
    public List<UserResponse> usersToResponse(List<User> user) {
        if ( user == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( user.size() );
        for ( User user1 : user ) {
            list.add( userToResponse( user1 ) );
        }

        return list;
    }
}
