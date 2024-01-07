package com.mn.Service;

import com.mn.seoha.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class MyPetServiceTest {

    @Autowired
    MyPetService myPetService;

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;






}
