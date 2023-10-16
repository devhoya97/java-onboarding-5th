package onboarding;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ApplicationTest {
    @Nested
    class Problem1Test {
        @Test
        void case1() {
            List<Integer> pobi = List.of(97, 98);
            List<Integer> crong = List.of(197, 198);
            int result = 0;
            assertThat(Problem1.solution(pobi, crong)).isEqualTo(result);
        }

        @Test
        void case2() {
            List<Integer> pobi = List.of(131, 132);
            List<Integer> crong = List.of(211, 212);
            int result = 1;
            assertThat(Problem1.solution(pobi, crong)).isEqualTo(result);
        }

        @Test
        void case3() {
            List<Integer> pobi = List.of(99, 102);
            List<Integer> crong = List.of(211, 212);
            int result = -1;
            assertThat(Problem1.solution(pobi, crong)).isEqualTo(result);
        }
    }

    @Nested
    class Problem2Test {
        @Test
        void case1() {
            String cryptogram = "browoanoommnaon";
            String result = "brown";
            assertThat(Problem2.solution(cryptogram)).isEqualTo(result);
        }

        @Test
        void case2() {
            String cryptogram = "zyelleyz";
            String result = "";
            assertThat(Problem2.solution(cryptogram)).isEqualTo(result);
        }
    }

    @Nested
    class Problem3Test {
        @Test
        void case1() {
            int number = 13;
            int result = 4;
            assertThat(Problem3.solution(number)).isEqualTo(result);
        }

        @Test
        void case2() {
            int number = 33;
            int result = 14;
            assertThat(Problem3.solution(number)).isEqualTo(result);
        }
    }

    @Nested
    class Problem4Test {
        @Test
        void case1() {
            String word = "I love you";
            String result = "R olev blf";
            assertThat(Problem4.solution(word)).isEqualTo(result);
        }
    }

    @Nested
    class Problem5Test {
        @Test
        void case1() {
            int money = 50_237;
            List<Integer> result = List.of(1, 0, 0, 0, 0, 2, 0, 3, 7);
            assertThat(Problem5.solution(money)).isEqualTo(result);
        }

        @Test
        void case2() {
            int money = 15_000;
            List<Integer> result = List.of(0, 1, 1, 0, 0, 0, 0, 0, 0);
            assertThat(Problem5.solution(money)).isEqualTo(result);
        }
    }

    @Nested
    class Problem6Test {
        @Test
        void case1() {
            List<List<String>> forms = List.of(
                    List.of("jm@email.com", "제이엠"),
                    List.of("jason@email.com", "제이슨"),
                    List.of("woniee@email.com", "워니"),
                    List.of("mj@email.com", "엠제이"),
                    List.of("nowm@email.com", "이제엠"),
                    List.of("aaa@email.com", "가나다라"),
                    List.of("bbb@email.com", "가나"),
                    List.of("ccc@email.com", "이제다라")
            );
            List<String> result = List.of("aaa@email.com", "bbb@email.com", "ccc@email.com", "jason@email.com", "jm@email.com", "mj@email.com", "nowm@email.com");
            assertThat(Problem6.solution(forms)).isEqualTo(result);
        }
        @Test
        void validate_테스트() {
            //given
            List<List<String>> forms = List.of(
                    List.of("jm@naver.com", "제이엠"),
                    List.of("jason@email.com", "제이슨"),
                    List.of("woniee@email.com", "워니"),
                    List.of("mj@email.com", "엠제이"),
                    List.of("nowm@email.com", "이제엠")
            );
            //when
            Throwable thrown = catchThrowable(() -> {
                Problem6.validateForms(forms);
            });

            //then
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void getLengthTwoNicknameSetOfACrew_테스트() {
            //given
            List<List<String>> forms = List.of(
                    List.of("jm@naver.com", "제이이이이이이엠"),
                    List.of("jason@email.com", "제이슨"),
                    List.of("woniee@email.com", "워니"),
                    List.of("mj@email.com", "엠제이"),
                    List.of("nowm@email.com", "이제엠")
            );
            String nickname = forms.get(0).get(1);
            //when
            Set<String> lengthTwoNicknameSet = Problem6.getLengthTwoNicknameSetOfACrew(nickname);
            //then
            assertThat(lengthTwoNicknameSet).containsExactly("제이", "이엠", "이이").hasSize(3);
        }
        @Test
        void addLengthTwoNicknameAndEmailMap_테스트() {
            //given
            List<List<String>> forms = List.of(
                    List.of("jm@naver.com", "제이엠"),
                    List.of("jason@email.com", "제이슨"),
                    List.of("woniee@email.com", "워니"),
                    List.of("mj@email.com", "엠제이"),
                    List.of("nowm@email.com", "이제엠")
            );
            Map<String, Set<String>> lengthTwoNicknameAndEmailMap = new HashMap<>();
            String email = forms.get(0).get(0);
            String nickname = forms.get(0).get(1);
            Set<String> lengthTwoNicknameSet = Problem6.getLengthTwoNicknameSetOfACrew(nickname);
            //when
            Problem6.addLengthTwoNicknameAndEmailMap(lengthTwoNicknameAndEmailMap, email, lengthTwoNicknameSet);
            email = forms.get(1).get(0);
            nickname = forms.get(1).get(1);
            lengthTwoNicknameSet = Problem6.getLengthTwoNicknameSetOfACrew(nickname);
            Problem6.addLengthTwoNicknameAndEmailMap(lengthTwoNicknameAndEmailMap, email, lengthTwoNicknameSet);
            //then
            System.out.println(lengthTwoNicknameAndEmailMap);
        }
        @Test
        void getLengthTwoNicknameAndEmailMap_테스트() {
            //given
            List<List<String>> forms = List.of(
                    List.of("jm@naver.com", "제이엠"),
                    List.of("jason@email.com", "제이슨"),
                    List.of("woniee@email.com", "워니"),
                    List.of("mj@email.com", "엠제이"),
                    List.of("nowm@email.com", "이제엠")
            );
            //when
            Map<String, Set<String>> lengthTwoNicknameAndEmailMap = Problem6.getLengthTwoNicknameAndEmailMap(forms);
            //then
            System.out.println(lengthTwoNicknameAndEmailMap);
        }
    }

    @Nested
    class Problem7Test {
        @Test
        void case1() {
            String user = "mrko";
            List<List<String>> friends = List.of(
                    List.of("donut", "andole"),
                    List.of("donut", "jun"),
                    List.of("donut", "mrko"),
                    List.of("shakevan", "andole"),
                    List.of("shakevan", "jun"),
                    List.of("shakevan", "mrko")
            );
            List<String> visitors = List.of("bedi", "bedi", "donut", "bedi", "shakevan");
            List<String> result = List.of("andole", "jun", "bedi");
            assertThat(Problem7.solution(user, friends, visitors)).isEqualTo(result);
        }
        @Test
        void addFriendshipMap_테스트() {
            //given
            Map<String, Set<String>> friendshipMap = new HashMap<>();
            List<String> friendship = Arrays.asList("donut", "andole");
            //when
            Problem7.addFriendshipMap(friendshipMap, friendship);
            //then
            assertThat(friendshipMap.get("donut")).containsExactly("andole");
            assertThat(friendshipMap.get("andole")).containsExactly("donut");
        }
        @Test
        void getFriendshipMap_테스트() {
            //given
            List<List<String>> friends = List.of(
                    List.of("donut", "andole"),
                    List.of("donut", "jun"),
                    List.of("donut", "mrko"),
                    List.of("shakevan", "andole"),
                    List.of("shakevan", "jun"),
                    List.of("shakevan", "mrko"),
                    List.of("mrko", "aaa"),
                    List.of("shakevan", "aaa"),
                    List.of("donut", "aaa"),
                    List.of("mrko", "aza"),
                    List.of("shakevan", "aza"),
                    List.of("donut", "aza")
            );
            //when
            Map<String, Set<String>> friendshipMap = Problem7.getFriendshipMap(friends);
            //then
            System.out.println(friendshipMap);
        }
        @Test
        void getRecommendOfUserMapWithFriends_테스트() {
            //given
            String user = "mrko";
            List<List<String>> friends = List.of(
                    List.of("donut", "andole"),
                    List.of("donut", "jun"),
                    List.of("donut", "mrko"),
                    List.of("shakevan", "andole"),
                    List.of("shakevan", "jun"),
                    List.of("shakevan", "mrko"),
                    List.of("shakevan", "aaa"),
                    List.of("donut", "aaa"),
                    List.of("shakevan", "aza"),
                    List.of("donut", "aza")
            );
            Map<String, Set<String>> friendshipMap = Problem7.getFriendshipMap(friends);
            //when
            Map<String, Integer> recommendMapOfUserWithFriends = Problem7.getRecommendMapOfUserWithFriends(user, friendshipMap);
            //then
            System.out.println(recommendMapOfUserWithFriends);
        }

        @Test
        void getRecommendMapOfUserWithVisitors_테스트() {
            //given
            String user = "mrko";
            List<String> visitors = List.of("bedi", "bedi", "donut", "bedi", "shakevan");
            List<List<String>> friends = List.of(
                    List.of("donut", "andole"),
                    List.of("donut", "jun"),
                    List.of("donut", "mrko"),
                    List.of("shakevan", "andole"),
                    List.of("shakevan", "jun"),
                    List.of("shakevan", "mrko")
            );
            Map<String, Set<String>> friendshipMap = Problem7.getFriendshipMap(friends);
            //when
            Map<String, Integer> recommendMapOfUserWithVisitors = Problem7.getRecommendMapOfUserWithVisitors(user, visitors, friendshipMap);
            //then
            System.out.println(recommendMapOfUserWithVisitors);
        }

        @Test
        void getRecommendMapOfUser_테스트() {
            //given
            String user = "mrko";
            List<String> visitors = List.of("bedi", "bedi", "donut", "bedi", "shakevan");
            List<List<String>> friends = List.of(
                    List.of("donut", "andole"),
                    List.of("donut", "jun"),
                    List.of("donut", "mrko"),
                    List.of("shakevan", "andole"),
                    List.of("shakevan", "jun"),
                    List.of("shakevan", "mrko")
            );
            //when
            Map<String, Integer> recommendMapOfUser = Problem7.getRecommendMapOfUser(user, friends, visitors);
            //then
            System.out.println(recommendMapOfUser);
        }

        @Test
        void getSortedRecommendListOfUser_테스트() {
            //given
            String user = "mrko";
            List<List<String>> friends = List.of(
                    List.of("donut", "andole"),
                    List.of("donut", "jun"),
                    List.of("donut", "mrko"),
                    List.of("shakevan", "andole"),
                    List.of("shakevan", "jun"),
                    List.of("shakevan", "mrko"),
//                    List.of("shakevan", "aaa"),
//                    List.of("donut", "aaa"),
                    List.of("shakevan", "aba"),
                    List.of("donut", "aba")
            );
            List<String> visitors = List.of("bedi", "bedi", "donut", "bedi", "shakevan", "mihno", "ayewon", "ayewon", "ayewon", "sole");
            List<String> result = List.of("andole", "jun", "bedi");
            //when
            List<String> answer = Problem7.solution(user, friends, visitors);
            //then
            System.out.println(answer);
        }
    }
}
