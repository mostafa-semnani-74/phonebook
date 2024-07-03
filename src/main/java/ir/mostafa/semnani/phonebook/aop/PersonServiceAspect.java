package ir.mostafa.semnani.phonebook.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class PersonServiceAspect {
    @Before("execution(* ir.mostafa.semnani.phonebook.service.PersonService.save(..))")
    public void beforeSave(JoinPoint joinPoint) {
        System.out.println("before save join point : " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* ir.mostafa.semnani.phonebook.service.PersonService.save(..))")
    public void afterSave(JoinPoint joinPoint) {
        System.out.println("after save join point : " + Arrays.toString(joinPoint.getArgs()));
    }

}
