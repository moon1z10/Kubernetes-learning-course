package hodlene.k8s.hellojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {

    @Autowired
    MemberRepository memberRepository;

    @RequestMapping("/test")
    String test() {
        Member member = Member.builder()
                .id(1L)
                .name("스프링")
                .build();
        System.out.println(member.toString());
        memberRepository.save(member);

        return "test";
    }
}
