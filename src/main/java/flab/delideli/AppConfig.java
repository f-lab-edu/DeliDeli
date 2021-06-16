package flab.delideli;

import flab.delideli.repository.MemberRepository;
import flab.delideli.repository.MemoryMemberRepository;
import flab.delideli.service.MemberJoinService;
import flab.delideli.service.MemberJoinServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberJoinService memberService() {
        return new MemberJoinServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
