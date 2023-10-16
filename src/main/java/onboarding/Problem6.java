package onboarding;

import java.util.*;

public class Problem6 {
    public static final int EMAIL_MIN_LENGTH = 11;
    public static final int EMAIL_MAX_LENGTH = 19;
    public static final int NICKNAME_MIN_LENGTH = 1;
    public static final int NICKNAME_MAX_LENGTH = 19;
    public static List<String> solution(List<List<String>> forms) {
        Set<String> answerSet = getAnswerSet(forms);
        List<String> answer = new ArrayList<>(answerSet);
        Collections.sort(answer);

        return answer;
    }
    public static Set<String> getAnswerSet(List<List<String>> forms) {
        Set<String> answerSet = new HashSet<>();
        Map<String, Set<String>> lengthTwoNicknameAndEmailMap = getLengthTwoNicknameAndEmailMap(forms);
        for (String lengthTwoNickname : lengthTwoNicknameAndEmailMap.keySet()) {
            Set<String> emailSet = lengthTwoNicknameAndEmailMap.get(lengthTwoNickname);
            if (emailSet.size() > 1) {
                answerSet.addAll(emailSet);
            }
        }
        return answerSet;
    }
    public static Map<String, Set<String>> getLengthTwoNicknameAndEmailMap(List<List<String>> forms) {
        Map<String, Set<String>> lengthTwoNicknameAndEmailMap = new HashMap<>();
        for (List<String> emailAndNickname : forms) {
            String email = emailAndNickname.get(0);
            String nickname = emailAndNickname.get(1);
            Set<String> lengthTwoNicknameSet = getLengthTwoNicknameSetOfACrew(nickname);
            addLengthTwoNicknameAndEmailMap(lengthTwoNicknameAndEmailMap, email, lengthTwoNicknameSet);
        }
        return lengthTwoNicknameAndEmailMap;
    }
    public static void addLengthTwoNicknameAndEmailMap(Map<String, Set<String>> lengthTwoNicknameAndEmailMap, String email, Set<String> lengthTwoNicknameSet) {
        for (String lengthTwoNickname : lengthTwoNicknameSet) {
            Set<String> lengthTwoNicknameAndEmailSet = lengthTwoNicknameAndEmailMap.getOrDefault(lengthTwoNickname, new HashSet<>());
            lengthTwoNicknameAndEmailSet.add(email);
            lengthTwoNicknameAndEmailMap.put(lengthTwoNickname, lengthTwoNicknameAndEmailSet);
        }
    }
    public static Set<String> getLengthTwoNicknameSetOfACrew(String nickname) {
        Set<String> lengthTwoNicknameSetOfACrew = new HashSet<>();
        for (int i=0; i<nickname.length()-1; i++) {
            lengthTwoNicknameSetOfACrew.add(nickname.substring(i, i+2));
        }
        return lengthTwoNicknameSetOfACrew;
    }
    public static void validateForms(List<List<String>> forms) {
        validateFormsLength(forms);
        for (List<String> emailAndNicknameOfACrew : forms) {
            String email = emailAndNicknameOfACrew.get(0);
            String nickname = emailAndNicknameOfACrew.get(1);
            validateEmail(email);
            validateNickname(nickname);
        }
    }
    public static void validateFormsLength(List<List<String>> forms) {
        if (forms.isEmpty() || forms.size() > 10000)
            throw new IllegalArgumentException("[ERROR] 크루가 없거나, 10000명 초과입니다.");
    }
    public static void validateEmail(String email) {
        validateStringLength(email, EMAIL_MIN_LENGTH, EMAIL_MAX_LENGTH);
        if (!email.matches("^[A-Za-z0-9+_.-]+@email.com$"))
            throw new IllegalArgumentException("[ERROR] 이메일이 형식에 부합하지 않습니다.");
    }
    public static void validateNickname(String nickname) {
        validateStringLength(nickname, NICKNAME_MIN_LENGTH, NICKNAME_MAX_LENGTH);
        if (!nickname.matches("^[가-힣]*$"))
            throw new IllegalArgumentException("[ERROR] 닉네임이 형식에 부합하지 않습니다.");
    }
    public static void validateStringLength(String str, int min, int max) {
        validateStringNullOrEmpty(str);
        if (str.length() < min || str.length() > max)
            throw new IllegalArgumentException("[ERROR] 입력 문자열의 길이가 제한조건에 부합하지 않습니다.");
    }
    public static void validateStringNullOrEmpty(String str) {
        if (str == null || str.trim().isEmpty())
            throw new IllegalArgumentException("[ERROR] 입력 중 null 또는 빈 문자열이 있습니다.");
    }
}
